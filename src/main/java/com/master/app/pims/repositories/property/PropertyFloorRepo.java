package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.property.PropertyFloor;

public interface PropertyFloorRepo extends JpaRepository<PropertyFloor, String>{
	 @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyFloor p WHERE p.floorCode = :floorCode AND p.floorGuid <> :floorGuid")
	    boolean isExistFloorCode(@Param("floorCode") String floorCode, @Param("floorGuid") String floorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyFloor p WHERE p.floorNameEn = :floorNameEn AND p.floorGuid <> :floorGuid")
	    boolean isExistFloorNameEn(@Param("floorNameEn") String floorNameEn, @Param("floorGuid") String floorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyFloor p WHERE p.floorNameHi = :floorNameHi AND p.floorGuid <> :floorGuid")
	    boolean isExistFloorNameHi(@Param("floorNameHi") String floorNameHi, @Param("floorGuid") String floorGuid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyFloor p WHERE p.floorNameRl = :floorNameRl AND p.floorGuid <> :floorGuid")
	    boolean isExistFloorNameRl(@Param("floorNameRl") String floorNameRl, @Param("floorGuid") String floorGuid);
}
