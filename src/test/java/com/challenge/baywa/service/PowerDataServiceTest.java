package com.challenge.baywa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.baywa.dto.PowerDataUpdateRequest;
import com.challenge.baywa.exception.NotFoundException;
import com.challenge.baywa.model.PowerDataEntity;
import com.challenge.baywa.repository.PowerDataRepository;

@ExtendWith(MockitoExtension.class)
public class PowerDataServiceTest {

	@Mock
	private PowerDataRepository powerDataRepository;

	@InjectMocks
	private PowerDataService powerDataService = new PowerDataServiceImpl();

	@Test
	public void testUpdatePowerData() throws IOException {
		PowerDataService dataService = mock(PowerDataService.class);
		doNothing().when(dataService).updatePowerData(isA(String.class), isA(PowerDataUpdateRequest.class));
		final PowerDataUpdateRequest request = new PowerDataUpdateRequest();
		dataService.updatePowerData("12345", request);
		verify(dataService, times(1)).updatePowerData("12345", request);
	}

	@Test
	public void testFetchAllPowerData() throws IOException {
		Mockito.when(powerDataRepository.findAll()).thenReturn(getPowerDataList());
		var result = powerDataService.fetchAllPowerData();
		assertNotNull(result);
		assertEquals(3, result.size());
	}

	@Test
	public void testFetchPowerDataById() throws IOException {
		Mockito.when(powerDataRepository.findById("1234abcd1")).thenReturn(Optional.of(getPowerDataList().get(1)));
		var result = powerDataService.fetchPowerDataBasedOnId("1234abcd1");
		assertNotNull(result);
		assertEquals("60m", result.getPeriod());
		assertEquals("testwind1", result.getWindPark());
	}

	@Test
	public void testFetchPowerDataByIdNotFound() throws IOException {
		Mockito.when(powerDataRepository.findById("1234ab2cd1")).thenReturn(Optional.empty());
		Exception exception = assertThrows(NotFoundException.class, () -> {
			powerDataService.fetchPowerDataBasedOnId("1234ab2cd1");
		});
		String expectedMessage = "Data not exists for the given id 1234ab2cd1";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	private List<PowerDataEntity> getPowerDataList() {

		List<PowerDataEntity> dataList = new ArrayList<PowerDataEntity>();

		PowerDataEntity entity = new PowerDataEntity();
		entity.setId("1234abcd");
		entity.setPeriod("30m");
		entity.setPowerProduced(10.2);
		entity.setWindPark("testwind");
		entity.setTimestamp(Instant.parse("2020-05-17T00:00:00Z"));
		entity.setCreatedOn(Instant.now().truncatedTo(ChronoUnit.SECONDS));
		entity.setUpdatedOn(Instant.parse("2020-06-17T00:00:00Z"));
		dataList.add(entity);

		PowerDataEntity entity1 = new PowerDataEntity();
		entity1.setId("1234abcd1");
		entity1.setPeriod("60m");
		entity1.setPowerProduced(10.2);
		entity1.setWindPark("testwind1");
		entity1.setTimestamp(Instant.parse("2020-01-17T00:00:00Z"));
		entity1.setCreatedOn(Instant.now().truncatedTo(ChronoUnit.SECONDS));
		entity1.setUpdatedOn(Instant.parse("2020-03-17T00:00:00Z"));
		dataList.add(entity1);

		PowerDataEntity entity2 = new PowerDataEntity();
		entity2.setId("1234abcd2");
		entity2.setPeriod("90m");
		entity2.setPowerProduced(10.2);
		entity2.setWindPark("testwind2");
		entity2.setTimestamp(Instant.parse("2020-06-17T00:00:00Z"));
		entity2.setCreatedOn(Instant.now().truncatedTo(ChronoUnit.SECONDS));
		entity2.setUpdatedOn(Instant.parse("2020-07-17T00:00:00Z"));
		dataList.add(entity2);

		return dataList;
	}

}
