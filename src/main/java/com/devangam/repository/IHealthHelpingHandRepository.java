package com.devangam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.Patients;

public interface IHealthHelpingHandRepository extends JpaRepository<Patients, Serializable>{

}
