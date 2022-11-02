package com.challenge.baywa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.baywa.model.PowerDataEntity;

@Repository
public interface PowerDataRepository extends JpaRepository<PowerDataEntity, String> {

}
