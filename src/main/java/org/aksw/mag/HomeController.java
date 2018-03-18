package org.aksw.mag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.aksw.mag.fox.Fox;
import org.aksw.mag.model.FrontendContent;
import org.aksw.mag.model.MagEntity;
import org.aksw.mag.model.NamedEntity;
import org.aksw.mag.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	Gson gson = new Gson();

	Fox fox;

	public HomeController() {
		fox = new Fox();
		try {
			DetectorFactory.loadProfile(HomeController.class.getClassLoader().getResource("profiles").getFile());
		} catch (LangDetectException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	/**
	 * {"text":"University of
	 * Leipzig","entities":["University//0//10","Leipzig//14//21"]}
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws RestClientException
	 * @throws LangDetectException
	 */
	@RequestMapping(value = "/mag", method = RequestMethod.POST)
	@ResponseBody
	public Result magEndpoint(Locale locale, Model model, @RequestBody FrontendContent frontendContent)
			throws LangDetectException {
		logger.info("from Frontend to agdistis {}.", frontendContent);
		Result result = new Result();
		// FrontendContent fromJson = gson.fromJson(text,
		// FrontendContent.class);
		String detectedLanguage = detectLanguage(frontendContent.getText());
		logger.debug("language: {}", detectedLanguage);
		String textToSend = extracted(frontendContent);
		String knowledgeBase = frontendContent.getKnowledgeBase();
		//System.out.println(knowledgeBase);
		String url = "http://akswnc9.informatik.uni-leipzig.de:";
		
//		if (detectedLanguage.equals("en")) {
//			url += "8113/AGDISTIS";
//		} else 
			
			if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("en")) {
			url += "8113/AGDISTIS";
			// url = "http://localhost:8080/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("de")) {
			url += "8114/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("es")) {
			url += "8115/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("fr")) {
			url += "8116/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("it")) {
			url += "8117/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("ja")) {
			url += "8118/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("nl")) {
			url += "8119/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("pt")) {
			url = "http://localhost:8090/AGDISTIS";
		} else if (knowledgeBase.equals("wikidata") && detectedLanguage.equals("en") || detectedLanguage.equals("tr")) {
			url = "http://localhost:8080/AGDISTIS";
		} else if (knowledgeBase.equals("dbpedia") && detectedLanguage.equals("zh-cn")
				|| detectedLanguage.equals("zh-tw") || detectedLanguage.equals("ko")) {
			url = "http://139.18.2.164:8080/AGDISTIS_ZH";
			// url += "AGDISTIS_ZH";
		} else {
			result.setDetectedLanguage(detectedLanguage + " not supported");
			return result;
		}

		textToSend = "text='" + textToSend + "'&type=agdistis";
		logger.info("to Agdistis: {}", textToSend);
		MagEntity[] magResult = sendRequest(textToSend, url);
		for (MagEntity n : magResult) {
			n.setEnd(n.getStart() + n.getOffset());
		}
		result.setDetectedLanguage(detectedLanguage);
		result.setNamedEntities(Lists.newArrayList(magResult));
		return result;
	}

	/**
	 * {"text":"University of
	 * Leipzig","entities":["University//0//10","Leipzig//14//21"]}
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws RestClientException
	 * @throws LangDetectException
	 */
	@RequestMapping(value = "/foxit", method = RequestMethod.POST)
	@ResponseBody
	public FrontendContent foxEndpoint(Locale locale, Model model, @RequestBody FrontendContent frontendContent)
			throws LangDetectException {
		logger.info("from frontend to fox {}.", frontendContent);
		String text = frontendContent.getText();
		text = text.replaceAll("\\[", "").replace("\\]", "");
		frontendContent.setText(text);

		Result result = new Result();
		// FrontendContent fromJson = gson.fromJson(text,
		// FrontendContent.class);
		String detectedLanguage = detectLanguage(frontendContent.getText());
		logger.debug("language: {}", detectedLanguage);
		if (detectedLanguage.equals("en")) {
			List<NamedEntity> namedEntities = fox.getEntities(text);
			NamedEntity[] entities = namedEntities.toArray(new NamedEntity[namedEntities.size()]);
			Arrays.sort(entities);
			StringBuilder t = new StringBuilder(text);
			logger.debug("{} entities to annotate", entities.length);
			for (int i = entities.length - 1; i >= 0; i--) {
				NamedEntity n = entities[i];
				logger.debug("{} start {} end {}", new Object[] { n.getNamedEntity(), n.getStart(), n.getEnd() });
				t = t.replace(n.getStart()[0], n.getEnd()[0], "[" + n.getNamedEntity() + "]");
			}
			frontendContent.setText(t.toString());
			logger.debug("annotated: {}", frontendContent.getText());
		} else {
			result.setDetectedLanguage(detectedLanguage + " not supported");
		}
		return frontendContent;
	}

	private String extracted(FrontendContent frontendContent) {
		String result = "";

		String annotatedText = addEntityAnnotation(frontendContent);
		logger.debug("annotated Text: {}", annotatedText);
		try {
			result = URLEncoder.encode(annotatedText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.debug("encoded {}", result);
		return result;
	}

	private String addEntityAnnotation(FrontendContent frontendContent) {
		String text = frontendContent.getText();
		text = text.replaceAll("\\[", "<entity>");
		text = text.replaceAll("\\]", "</entity>");
		return text;
	}

	private MagEntity[] sendRequest(String text, String u) {
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));

		HttpEntity<String> entity = new HttpEntity<String>(text, headers);
		ResponseEntity<String> postForO = rest.postForEntity(u, entity, String.class);
		logger.info("from Agdistis: {}", postForO.getBody());
		return gson.fromJson(postForO.getBody(), MagEntity[].class);
	}

	private String detectLanguage(String text) throws LangDetectException {
		Detector detector = DetectorFactory.create();
		detector.append(text);
		return detector.detect();
	}
}
