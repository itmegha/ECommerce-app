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

import com.example.demo.entities.Order1;
import com.example.demo.repos.OrderRepository;
import com.example.demo.services.OrderServiceai1;

@Service
public class OrderAISer implements OrderServiceai1{
	
	    @Value("${gemini.api.key}")
	    private String API_KEY;

	    @Autowired
	    private OrderRepository orderRepository;

	    public String getOrderResponse(String query) {

	        RestTemplate restTemplate = new RestTemplate();

	        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

	        // Extract order id (simple logic)
	        Long orderId = extractOrderId(query);

	        Order1 order = orderRepository.findById(orderId).orElse(null);

	        if (order == null) {
	            return "Order not found.";
	        }

	        String prompt = "You are a helpful ecommerce support assistant.\n" +
	                "Order Details:\n" +
	                "Order ID: " + order.getId() + "\n" +
	                "Status: " + order.getStatus() + "\n" +
	                "Location: " + order.getLocation() + "\n" +
	                "Delivery Date: " + order.getDeliveryDate() + "\n" +
	                "User Query: " + query +
	                "\nAnswer clearly.";

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
	            return "Error fetching AI response.";
	        }
	    }

	    private Long extractOrderId(String query) {
	        // simple extraction (you can improve later)
	        String digits = query.replaceAll("\\D+", "");
	        return digits.isEmpty() ? 1L : Long.parseLong(digits);
	    }
}
