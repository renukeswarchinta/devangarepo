package com.devangam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.Speeches;

public interface SpeecheRepository  extends JpaRepository<Speeches, Serializable>{

}
