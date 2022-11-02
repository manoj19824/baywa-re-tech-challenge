package com.challenge.baywa.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.baywa.dto.PowerDataRequest;
import com.challenge.baywa.dto.PowerDataResponse;
import com.challenge.baywa.dto.PowerDataUpdateRequest;
import com.challenge.baywa.exception.NotFoundException;
import com.challenge.baywa.model.PowerDataEntity;
import com.challenge.baywa.repository.PowerDataRepository;

@Service
public class PowerDataServiceImpl implements PowerDataService {

	@Autowired
	private PowerDataRepository powerDataRepository;

	@Override
	public void storePowerData(List<PowerDataRequest> dataReqList) {
		powerDataRepository.saveAll(dataReqList.stream().map(this::converToEntity).collect(Collectors.toList()));

	}

	@Override
	public void updatePowerData(final String id, final PowerDataUpdateRequest powerDataRequest) {
		final Optional<PowerDataEntity> existingRecord = powerDataRepository.findById(id);
		if (existingRecord.isPresent()) {
			PowerDataEntity existingEntity = existingRecord.get();
			existingEntity.setPowerProduced(powerDataRequest.getPowerProduced());
			existingEntity.setPeriod(powerDataRequest.getPeriod());
			existingEntity.setWindPark(powerDataRequest.getWindPark());
			existingEntity.setTimestamp(powerDataRequest.getTimeStamp() != null ? Instant.parse(powerDataRequest.getTimeStamp()) : null);
			existingEntity.setUpdatedOn(Instant.now().truncatedTo(ChronoUnit.SECONDS));
			powerDataRepository.save(existingEntity);
		} else {
			throw new NotFoundException("Update UnSuccessful : Data not exists for the given id " + id);
		}

	}

	@Override
	public List<PowerDataResponse> fetchAllPowerData() {
		return powerDataRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	private PowerDataEntity converToEntity(final PowerDataRequest request) {
		PowerDataEntity entity = new PowerDataEntity();
		entity.setId(request.getId());
		entity.setPeriod(request.getPeriod());
		entity.setTimestamp(request.getTimestamp() != null ? Instant.parse(request.getTimestamp()) : null);
		entity.setCreatedOn(request.getCreatedOn() != null ? Instant.parse(request.getCreatedOn()) : null);
		entity.setUpdatedOn(request.getUpdatedOn() != null ? Instant.parse(request.getUpdatedOn()) : null);
		entity.setPowerProduced(request.getPowerProduced());
		entity.setWindPark(request.getWindPark());
		return entity;
	}

	private PowerDataResponse convertToResponse(final PowerDataEntity entity) {
		PowerDataResponse response = new PowerDataResponse();
		response.setId(entity.getId());
		response.setWindPark(entity.getWindPark());
		response.setPowerProduced(entity.getPowerProduced());
		response.setPeriod(entity.getPeriod());
		response.setTimeStamp(entity.getTimestamp() != null ? entity.getTimestamp().toString() : null);
		response.setCreatedOn(entity.getTimestamp() != null ? entity.getCreatedOn().toString() : null);
		response.setUpdatedOn(entity.getTimestamp() != null ? entity.getUpdatedOn().toString() : null);
		return response;
	}

	@Override
	public PowerDataResponse fetchPowerDataBasedOnId(String id) {
		final Optional<PowerDataEntity> maybeEntity = powerDataRepository.findById(id);
		if (maybeEntity.isPresent()) {
			return convertToResponse(maybeEntity.get());
		} else {
			throw new NotFoundException("Data not exists for the given id " + id);
		}

	}

}
