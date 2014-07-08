package org.aksw.agdistis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.aksw.agdistis.model.Entity;
import org.aksw.agdistis.model.FrontendContent;
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
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    Gson gson = new Gson();

    public HomeController() {
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
     * {"text":"University of Leipzig","entities":["University//0//10","Leipzig//14//21"]}
     * 
     * @throws IOException
     * @throws URISyntaxException
     * @throws RestClientException
     * @throws LangDetectException
     */
    @RequestMapping(value = "/agdistis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String agdistisEndpoint(Locale locale, Model model, @RequestBody String text) throws LangDetectException {
        logger.info("TEXT {}.", text);
        FrontendContent fromJson = gson.fromJson(text, FrontendContent.class);
        String detectedLanguage = detectLanguage(fromJson.getText());
        logger.debug("language: {}", detectedLanguage);
        String textToSend = extracted(fromJson);
        String url = "http://139.18.2.164:8080/";
        if (detectedLanguage.equals("de")) {
            url += "AGDISTIS_DE";
        } else if (detectedLanguage.equals("en")) {
            url += "AGDISTIS";
        } else if (detectedLanguage.equals("zh-cn") || detectedLanguage.equals("zh-tw")
                || detectedLanguage.equals("ko")) {
            url += "AGDISTIS_ZH";
        } else {
            return "{\"nosup\": \"" + detectedLanguage + "\"}";
        }

        text = "text='" + textToSend + "'&type=agdistis";
        logger.info("send: {}", text);
        ResponseEntity<String> agdistisResultEntity = sendRequest(text, url);
        String agdistisResult = agdistisResultEntity.getBody();
        StringBuilder result = new StringBuilder();
        result.append("{\"detectedlanguage\":\"");
        result.append(detectedLanguage);
        result.append("\", \"namedEntities\":");
        result.append(agdistisResult);
        result.append("}");
        logger.info("{}", result.toString());
        return result.toString();
    }

    private String extracted(FrontendContent frontendContent) {
        String result = "";

        String annotatedText = addEntityAnnotation(frontendContent);
        logger.info("annotated Text: {}", annotatedText);
        try {
            result = URLEncoder.encode(annotatedText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("encoded {}", result);
        return result;
    }

    private String addEntityAnnotation(FrontendContent frontendContent) {
        String text = frontendContent.getText();
        text = text.replaceAll("\\[", "<entity>");
        text = text.replaceAll("\\]", "</entity>");
        return text;
    }

    private ResponseEntity<String> sendRequest(String text, String u) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));

        HttpEntity<String> entity = new HttpEntity<String>(text, headers);
        ResponseEntity<String> postForO = rest.postForEntity(u, entity, String.class);
        return postForO;
    }

    private String detectLanguage(String text) throws LangDetectException {
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.detect();
    }
}
