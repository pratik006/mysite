package com.prapps.app.trainapp.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapps.app.trainapp.persistence.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long>{

}
