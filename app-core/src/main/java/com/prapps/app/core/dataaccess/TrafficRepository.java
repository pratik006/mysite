package com.prapps.app.core.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.core.persistence.TrafficEntity;

public interface TrafficRepository extends JpaRepository<TrafficEntity, Long> {
    
}
