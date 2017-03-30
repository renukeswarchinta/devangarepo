package com.devangam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devangam.entity.GalleryEntity;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Long>{

}
