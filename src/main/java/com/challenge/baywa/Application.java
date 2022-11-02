package com.challenge.baywa;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.challenge.baywa.dto.PowerDataRequest;
import com.challenge.baywa.service.PowerDataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(PowerDataService dataService) {
		return args -> {
			TypeReference<List<PowerDataRequest>> typeReference = new TypeReference<List<PowerDataRequest>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/insert_data.json");
			try {
				List<PowerDataRequest> powerDataList = objectMapper.readValue(inputStream, typeReference);
				dataService.storePowerData(powerDataList);
				System.out.println("Data Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save power data: " + e.getMessage());
			}
		};
	}
}
