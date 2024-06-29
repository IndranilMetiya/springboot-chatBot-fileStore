package com.chatBot.indraChat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chatBot.indraChat.constants.AppConstants;
import com.chatBot.indraChat.response.ApiResponse;
import com.chatBot.indraChat.response.RequestBody;

@Service
public class AiBotService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String API_URL_TEMPLATE = AppConstants.API_URL;
	
	public String callApi(String prompt) {
		
		String apiUrl = String.format(API_URL_TEMPLATE, AppConstants.GEMINI_KEY);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("Content-Type", "application/json");
		
		
		 RequestBody.Part part = new RequestBody.Part(prompt);
	        RequestBody.Content content = new RequestBody.Content(List.of(part));
	        RequestBody requestBody = new RequestBody(List.of(content));
	        
	        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody, headers);
	        
	        ResponseEntity<ApiResponse> response = restTemplate.exchange(
	                apiUrl,
	                HttpMethod.POST,
	                requestEntity,
	                ApiResponse.class
	        );
	        
	        ApiResponse apiResponse = response.getBody();
	        if (apiResponse != null && !apiResponse.getCandidates().isEmpty()) {
	            ApiResponse.Candidate candidate = apiResponse.getCandidates().get(0); // Assuming the first candidate
	            if (candidate.getContent() != null && !candidate.getContent().getParts().isEmpty()) {
	                ApiResponse.Part partObject = candidate.getContent().getParts().get(0); // Assuming first part
	                return partObject.getText();
	            }
	        }

	        return "No Data Can Found in chatBot"; 
		
	}

}
