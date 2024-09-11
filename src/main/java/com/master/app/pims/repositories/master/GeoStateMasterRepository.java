package com.master.app.pims.repositories.master;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeoStateMasterRepository extends JpaRepository<GeoStateMaster, String> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateCode = :stateCode AND s.stateMasterGuid <> :stateMasterGuid")
    boolean isExistStateCode(@Param("stateCode") String stateCode, @Param("stateMasterGuid") String stateMasterGuid);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateNameEn = :stateNameEn AND s.stateMasterGuid <> :stateMasterGuid")
    boolean isExistStateNameEn(@Param("stateNameEn") String stateNameEn, @Param("stateMasterGuid") String stateMasterGuid);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateNameHi = :stateNameHi AND s.stateMasterGuid <> :stateMasterGuid")
    boolean isExistStateNameHi(@Param("stateNameHi") String stateNameHi, @Param("stateMasterGuid") String stateMasterGuid);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM GeoStateMaster s WHERE s.stateNameRl = :stateNameRl AND s.stateMasterGuid <> :stateMasterGuid")
    boolean isExistStateNameRl(@Param("stateNameRl") String stateNameRl, @Param("stateMasterGuid") String stateMasterGuid);


}
