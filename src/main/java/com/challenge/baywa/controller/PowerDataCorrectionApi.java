package com.challenge.baywa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.baywa.dto.PowerDataRequest;
import com.challenge.baywa.dto.PowerDataResponse;
import com.challenge.baywa.dto.PowerDataUpdateRequest;
import com.challenge.baywa.dto.ResponseMessage;
import com.challenge.baywa.service.PowerDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PowerData Correction Api#s", description = "This is used for correcting the power data and displaying it")
@RestController
@RequestMapping("/api/power-data")
public class PowerDataCorrectionApi {

	@Autowired
	private PowerDataService powerDataService;

	@Operation(summary = "List all available power data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get all data Successful", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PowerDataResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@GetMapping(value = "/")
	public ResponseEntity<List<PowerDataResponse>> fetchAllPowerData() {
		try {
			final List<PowerDataResponse> responseList = powerDataService.fetchAllPowerData();
			return new ResponseEntity<List<PowerDataResponse>>(responseList, HttpStatus.OK);
		} catch (final Exception exception) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Create a power data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Creation Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PowerDataRequest.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@PostMapping(value = "/")
	public ResponseEntity<ResponseMessage> createPowerData(
			@RequestBody @Valid List<PowerDataRequest> powerDataRequests) {
		try {
			powerDataService.storePowerData(powerDataRequests);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Power Data created successfully"));
		} catch (final Exception exception) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseMessage(exception.getMessage()));
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get power data based on id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get data Successful", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	public ResponseEntity<PowerDataResponse> getPowerData(
			@Parameter(description = "Id", required = true, style = ParameterStyle.FORM, explode = Explode.FALSE, schema = @Schema(implementation = String.class)) @PathVariable String id) {
		try {
			return new ResponseEntity<>(powerDataService.fetchPowerDataBasedOnId(id), HttpStatus.OK);
		} catch (final Exception exception) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Update the current power data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update data Successful", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@PostMapping("/{id}")
	public ResponseEntity<ResponseMessage> updatePowerData(@RequestBody @Valid PowerDataUpdateRequest updateRequest,
			@Parameter(description = "Id", required = true, style = ParameterStyle.FORM, explode = Explode.FALSE, schema = @Schema(implementation = String.class)) @PathVariable String id) {
		try {
			powerDataService.updatePowerData(id, updateRequest);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Power Data updated successfully"));
		} catch (final Exception exception) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseMessage(exception.getMessage()));
		}
	}
}
