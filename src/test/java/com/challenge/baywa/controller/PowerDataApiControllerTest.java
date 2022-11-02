package com.challenge.baywa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.challenge.baywa.dto.PowerDataRequest;
import com.challenge.baywa.dto.PowerDataResponse;
import com.challenge.baywa.dto.PowerDataUpdateRequest;
import com.challenge.baywa.dto.ResponseMessage;
import com.challenge.baywa.service.PowerDataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PowerDataApiControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PowerDataService dataService;

	@BeforeEach
	public void uploadData() throws Exception {
		final TypeReference<List<PowerDataRequest>> typeReference = new TypeReference<List<PowerDataRequest>>() {
		};
		final InputStream inputStream = TypeReference.class.getResourceAsStream("/json/insert_data.json");
		try {
			final List<PowerDataRequest> powerDataList = objectMapper.readValue(inputStream, typeReference);
			dataService.storePowerData(powerDataList);
			System.out.println("Data Saved!");
		} catch (final Exception e) {
			System.out.println("Unable to save power data: " + e.getMessage());
		}
	}

	@Test
	public void fetchAllPowerData() throws Exception {
		final String endPoint = new URL("http://localhost:" + port + "/api/power-data/").toString();
		ResponseEntity<List<PowerDataResponse>> responseEntity = 
				  restTemplate.exchange(
				    endPoint,
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<PowerDataResponse>>() {}
				  );
		List<PowerDataResponse> dataList = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(!dataList.isEmpty());
	}

	@Test
	public void fetchPowerDataBasedOnId() throws Exception {
		final String endPoint = new URL(
				"http://localhost:" + port + "/api/power-data/20e1cb09-5403-4bed-86f4-19da202f2dbe").toString();
		ResponseEntity<String> response = restTemplate.getForEntity(endPoint, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void fetchPowerDataBasedOnIdNotFound() throws Exception {
		final String endPoint = new URL(
				"http://localhost:" + port + "/api/power-data/20e1cb09-5403-4bed-86f4-19da202f2dbe1").toString();
		ResponseEntity<String> response = restTemplate.getForEntity(endPoint, String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	public void updatePowerData() throws Exception {
		final String endPoint = new URL(
				"http://localhost:" + port + "/api/power-data/20e1cb09-5403-4bed-86f4-19da202f2dbe").toString();
		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		PowerDataUpdateRequest updateRequest = new PowerDataUpdateRequest();
		updateRequest.setPeriod("90m");
		updateRequest.setPowerProduced(200);
		// build the request
		HttpEntity<PowerDataUpdateRequest> request = new HttpEntity<>(updateRequest, headers);
		ResponseEntity<ResponseMessage> response = restTemplate.postForEntity(endPoint, request, ResponseMessage.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
