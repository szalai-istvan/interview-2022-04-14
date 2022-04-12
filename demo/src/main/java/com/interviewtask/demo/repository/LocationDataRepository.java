package com.interviewtask.demo.repository;

import com.interviewtask.demo.entity.LocationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDataRepository extends JpaRepository<LocationData, Long> {

}
