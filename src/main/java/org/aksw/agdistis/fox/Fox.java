package org.aksw.agdistis.fox;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.aksw.agdistis.model.NamedEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFReader;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class Fox extends ASpotter {
    private static Logger log = LoggerFactory.getLogger(Fox.class);
    private String requestURL = "http://139.18.2.164:4444/api";
    private String outputFormat = "N3";
    private String taskType = "NER";
    private String inputType = "text";

    private String doTASK(String inputText) throws MalformedURLException, IOException, ProtocolException {
        String urlParameters = "type=" + inputType;
        urlParameters += "&task=" + taskType;
        urlParameters += "&output=" + outputFormat;
        urlParameters += "&input=" + URLEncoder.encode(inputText, "UTF-8");
        return requestPOST(urlParameters, requestURL);
    }

    @Override
    public List<NamedEntity> getEntities(String question) {
        List<NamedEntity> result = Lists.newLinkedList();
        try {
            String foxJSONOutput = doTASK(question);

            JSONParser parser = new JSONParser();
            JSONObject jsonArray = (JSONObject) parser.parse(foxJSONOutput);
            String output = URLDecoder.decode((String) ((JSONObject) jsonArray).get("output"), "UTF-8");
            log.info("OUTPUT: {}", output);
            String baseURI = "http://dbpedia.org";
            Model model = ModelFactory.createDefaultModel();
            RDFReader r = model.getReader("N3");
            r.read(model, new StringReader(output), baseURI);
            ResIterator iter = model.listSubjects();
            while (iter.hasNext()) {
                Resource next = iter.next();
                StmtIterator statementIter = next.listProperties();
                NamedEntity ent = new NamedEntity();
                log.info("ANNOT");
                while (statementIter.hasNext()) {
                    Statement statement = statementIter.next();
                    String predicateURI = statement.getPredicate().getURI();
                    RDFNode object = statement.getObject();
                    String subject = statement.getSubject().getURI();
                    log.info("{} -> ({}) -> {}", new Object[] { subject, predicateURI, object });

                    if (predicateURI.equals("http://www.w3.org/2000/10/annotation-ns#body")) {
                        ent.setNamedEntity(object.asLiteral().getString());
                    } else if (predicateURI.equals("http://ns.aksw.org/scms/means")) {
                        String uri = object.asResource().getURI();
                        String encode = uri.replaceAll(",", "%2C");
                        ent.setDisambiguatedURL(encode);
                    } else if (predicateURI.equals("http://ns.aksw.org/scms/endIndex")) {
                        ent.setEnd(object.asLiteral().getInt());
                    } else if (predicateURI.equals("http://ns.aksw.org/scms/beginIndex")) {
                        ent.setStart(object.asLiteral().getInt());
                    }
                }
                ent.setOffset(ent.getEnd() - ent.getStart());
                result.add(ent);
            }
        } catch (IOException | ParseException e) {
            log.error("Could not call FOX for NER/NED", e);
        }
        return result;
    }

    public static void main(String args[]) {
        ASpotter fox = new Fox();
        List<NamedEntity> entities = fox.getEntities("University of Leipzig is in Leipzig near MDR tower");
        for (NamedEntity namedEntity : entities) {
            log.info("{}", namedEntity.toString());
        }
    }
}