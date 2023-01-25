package com.example.exercisenine.repository;

import com.example.exercisenine.dto.GroupDto;
import com.example.exercisenine.entity.MeterGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeterGroupRepository extends JpaRepository<MeterGroup, Long> {
    @Query("SELECT  new com.example.exercisenine.dto.GroupDto(mg.name, count(m.id)) from MeterGroup mg join Meter m on mg.id = m.meterGroup.id group by mg.name order by mg.name")
    List<GroupDto> findAllByName();
    MeterGroup findByName(String name);

    List<MeterGroup> findAll();

}
