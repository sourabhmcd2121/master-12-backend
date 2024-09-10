package com.master.app.pims.repositories.master;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GeoStateMasterRepository extends JpaRepository<GeoStateMaster, String> {
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateCode = :stateCode AND s.stateMasterGuid <> :stateMasterGuid")
	boolean isExistStateCode(@Param("stateCode") String stateCode, @Param("stateMasterGuid") String stateMasterGuid);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateNameEn = :#{#state.stateNameEn} AND s.stateMasterGuid <> :#{#state.stateMasterGuid}")
	    boolean isExistStateNameEn(@Param("stateNameEn") GeoStateMaster stateNameEn);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateNameHi = :#{#state.stateNameHi} AND s.stateMasterGuid <> :#{#state.stateMasterGuid}")
	    boolean isExistStateNameHi(@Param("stateNameHi") GeoStateMaster stateNameHi);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateNameRl = :#{#state.stateNameRl} AND s.stateMasterGuid <> :#{#state.stateMasterGuid}")
	    boolean isExistStateNameRl(@Param("stateNameRl") GeoStateMaster stateNameRl);
  
}
