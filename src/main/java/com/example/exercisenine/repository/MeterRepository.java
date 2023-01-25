package com.example.exercisenine.repository;

import com.example.exercisenine.entity.Meter;
import com.example.exercisenine.entity.MeterGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeterRepository extends JpaRepository<Meter,Long> {

    List<Meter> findAllByMeterGroup(MeterGroup meterGroup);

}
