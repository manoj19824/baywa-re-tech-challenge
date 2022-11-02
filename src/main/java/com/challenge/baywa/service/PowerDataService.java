package com.challenge.baywa.service;

import java.util.List;

import com.challenge.baywa.dto.PowerDataRequest;
import com.challenge.baywa.dto.PowerDataResponse;
import com.challenge.baywa.dto.PowerDataUpdateRequest;

public interface PowerDataService {

	void storePowerData(List<PowerDataRequest> powerDataRequest);

	void updatePowerData(String id,PowerDataUpdateRequest updateRequest);

	List<PowerDataResponse> fetchAllPowerData();
	
	PowerDataResponse fetchPowerDataBasedOnId(String id);

}
