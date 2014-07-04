package org.aksw.agdistis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.aksw.agdistis.model.AgdistisModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    /**
     * Simply selects the home view to render by returning its name.
     * 
     * @throws IOException
     * @throws URISyntaxException
     * @throws RestClientException
     */
    @RequestMapping(value = "/agdistisen", method = RequestMethod.POST)
    @ResponseBody
    public String agdistisENEndpoint(Locale locale, Model model, @RequestBody String text) throws IOException,
            RestClientException, URISyntaxException {
        logger.info("TEXT {}.", text);
        URL u = null;
        u = new URL("http://139.18.2.164:8080/AGDISTIS");
        text = "text=" + text + "&type=agdistis";
        logger.info("send: {}", text);
        ResponseEntity<String> postForO = sendRequest(text, u);
        return "{\"result\":" + postForO.getBody() + "}";
    }

    /**
     * Simply selects the home view to render by returning its name.
     * 
     * @throws IOException
     * @throws URISyntaxException
     * @throws RestClientException
     */
    @RequestMapping(value = "/agdistisde", method = RequestMethod.POST)
    @ResponseBody
    public String agdistisDEEndpoint(Locale locale, Model model, @RequestBody String text) throws IOException,
            RestClientException, URISyntaxException {
        logger.info("TEXT {}.", text);
        URL u = null;
        u = new URL("http://139.18.2.164:8080/AGDISTIS_DE");
        text = "text=" + text + "&type=agdistis";
        ResponseEntity<String> postForO = sendRequest(text, u);
        return postForO.getBody();
    }

    private ResponseEntity<String> sendRequest(String text, URL u) throws URISyntaxException,
            UnsupportedEncodingException {
        RestTemplate rest = new RestTemplate();
        // text = URLEncoder.encode(text, "UTF-8");
        logger.info("encoded: {}", text);
        ResponseEntity<String> postForO = rest.postForEntity(u.toURI(), text, String.class);
        logger.info("headers: {}", postForO.getHeaders());
        logger.info("statusCode: {}", postForO.getStatusCode());
        logger.info("Result: {}", postForO.getBody());
        return postForO;
    }
}
