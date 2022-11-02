package com.challenge.baywa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.challenge.baywa.model.PowerDataEntity;

@DataJpaTest
@ComponentScan(basePackages = "com.challenge.baywa")
@ExtendWith(SpringExtension.class)
public class PowerDataRepositoryTest {

	@Autowired
	private PowerDataRepository powerDataRepository;

	@BeforeEach
	public void insertData() {
		powerDataRepository.deleteAllInBatch();
		powerDataRepository.saveAll(createPowerDataEntityList());
	}

	@Test
	public void testFindById() {
		final Optional<PowerDataEntity> optionalEntity = powerDataRepository.findById("1234abcd");
		assertTrue(optionalEntity.isPresent());
		assertEquals("1234abcd", optionalEntity.get().getId());
		assertEquals("30m", optionalEntity.get().getPeriod());
		assertEquals(10.2, optionalEntity.get().getPowerProduced());
		assertEquals(Instant.parse("2020-05-17T00:00:00Z"), optionalEntity.get().getTimestamp());
	}

	@Test
	public void testFindAll() {
		final List<PowerDataEntity> dataList = powerDataRepository.findAll();
		assertTrue(dataList.size() > 0);
		assertEquals(2, dataList.size());
	}

	private List<PowerDataEntity> createPowerDataEntityList() {

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

		return dataList;
	}

}
