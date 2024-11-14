package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;


public interface ReligiousPlacesRepository extends JpaRepository<ReligiousPlaces,String> {
	
	   @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ReligiousPlaces r WHERE r.religiousPlacesCode = :religiousPlacesCode AND r.religiousPlacesGuid != :religiousPlacesGuid")
	    boolean isExistReligiousPlacesCode(@Param("religiousPlacesCode") String religiousPlacesCode, @Param("religiousPlacesGuid") String religiousPlacesGuid);

	    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ReligiousPlaces r WHERE r.religiousPlacesNameEn = :religiousPlacesNameEn AND r.religiousPlacesGuid != :religiousPlacesGuid")
	    boolean isExistReligiousPlacesNameEn(@Param("religiousPlacesNameEn") String religiousPlacesNameEn, @Param("religiousPlacesGuid") String religiousPlacesGuid);
	    
}
