package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;

public interface GeoColonyCategoryRepository extends JpaRepository<GeoColonyCategory, String> {

}
