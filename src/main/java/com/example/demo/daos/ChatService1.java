package com.example.demo.daos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Product;
import com.example.demo.repos.Productrepo;

@Service
public class ChatService1 {

	 @Value("${gemini.api.key}")
	   private String API_KEY;

	   @Autowired
	   private Productrepo prepo;

	   public String getAIResponse(String userQuery) {

	       RestTemplate restTemplate = new RestTemplate();

	       String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

	       // Fetch products
	       List<Product> products = prepo.findAll();

	       // Create prompt
	       String prompt = "You are an ecommerce assistant.\n" +
	               "Here are products:\n" + products.toString() +
	               "\nUser query: " + userQuery +
	               "\nSuggest best products with name and price.";

	       HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);

	       String body = "{\n" +
	               "  \"contents\": [\n" +
	               "    {\n" +
	               "      \"parts\": [\n" +
	               "        {\"text\": \"" + prompt + "\"}\n" +
	               "      ]\n" +
	               "    }\n" +
	               "  ]\n" +
	               "}";

	       HttpEntity<String> entity = new HttpEntity<>(body, headers);

	       try {
	           ResponseEntity<Map> response =
	                   restTemplate.postForEntity(url, entity, Map.class);

	           List candidates = (List) response.getBody().get("candidates");
	           Map first = (Map) candidates.get(0);
	           Map content = (Map) first.get("content");
	           List parts = (List) content.get("parts");
	           Map textPart = (Map) parts.get(0);

	           return textPart.get("text").toString();

	       } catch (Exception e) {
	           return "Error: Unable to fetch AI response";
	       }
	   }
		

}
