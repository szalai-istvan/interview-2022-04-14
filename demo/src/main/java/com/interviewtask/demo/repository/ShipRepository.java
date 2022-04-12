package com.interviewtask.demo.repository;

import com.interviewtask.demo.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
