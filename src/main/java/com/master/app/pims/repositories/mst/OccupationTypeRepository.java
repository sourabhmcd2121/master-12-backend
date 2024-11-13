package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.OccupationType;

public interface OccupationTypeRepository extends JpaRepository<OccupationType, String>{
	 @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OccupationType o WHERE o.occupationCode = :occupationCode AND o.occupationGuid != :occupationGuid")
	    boolean isExistOccupationCode(@Param("occupationCode") String occupationCode, @Param("occupationGuid") String occupationGuid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OccupationType o WHERE o.occupationNameEn = :occupationNameEn AND o.occupationGuid != :occupationGuid")
	    boolean isExistOccupationNameEn(@Param("occupationNameEn") String occupationNameEn, @Param("occupationGuid") String occupationGuid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OccupationType o WHERE o.occupationNameHi = :occupationNameHi AND o.occupationGuid != :occupationGuid")
	    boolean isExistOccupationNameHi(@Param("occupationNameHi") String occupationNameHi, @Param("occupationGuid") String occupationGuid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OccupationType o WHERE o.occupationNameRl = :occupationNameRl AND o.occupationGuid != :occupationGuid")
	    boolean isExistOccupationNameRl(@Param("occupationNameRl") String occupationNameRl, @Param("occupationGuid") String occupationGuid);
}
