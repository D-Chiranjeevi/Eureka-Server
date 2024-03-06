package com.sppl.connectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sppl.exceptions.TechnicalException;
import com.sppl.util.Constants;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Component
@AllArgsConstructor
@NoArgsConstructor
public class OSBHttpConnector {
	private static final String HEADER_ACCEPT = "Accept";
	private static final String HEADER_X_SOURCE = "X-SOURCE";
	
	private String OSBHOST;

	

	public <T, R> ResponseEntity<T> sendPostRequest(RestTemplate restTemplate, R requestBody, String path,
			Class<T> responseClass) throws TechnicalException {
		String url = OSBHOST + path;

// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.add(HEADER_ACCEPT, "application/xml");
		headers.add(HEADER_X_SOURCE,Constants.DEFAULT_X_SOURCE );

		HttpEntity<R> entity = new HttpEntity<R>(requestBody, headers);
		return sendPostRequest(restTemplate, url, entity, responseClass);
	}

	private <T, R> ResponseEntity<T> sendPostRequest(RestTemplate restTemplate, String url, HttpEntity<R> entity,
			Class<T> responseClass) throws TechnicalException {
		try {
			ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, entity, responseClass);
			return response;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
}
