package com.master.app.pims.repositories;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
@Repository
public interface ApplicationMasterRepository extends JpaRepository<ApplicationMaster, String> {
	

	    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ApplicationMaster a " +
	           "WHERE a.applicationMasterCode = :applicationMasterCode " +
	           "AND a.applicationMasterGuid <> :applicationMasterGuid")
	    boolean isExistApplicationMasterCode(@Param("applicationMasterCode") String applicationMasterCode, 
	                                         @Param("applicationMasterGuid") String applicationMasterGuid) throws SQLException;

	    
	    
	    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ApplicationMaster a " +
	           "WHERE a.applicationMasterName = :applicationMasterName " +
	           "AND a.applicationMasterGuid <> :applicationMasterGuid")
	    boolean isExistApplicationMasterName(@Param("applicationMasterName") String applicationMasterName, 
	                                         @Param("applicationMasterGuid") String applicationMasterGuid) throws SQLException;

	    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ApplicationMaster a " +
	           "WHERE a.applicationMasterIp4 = :applicationMasterIp4 " +
	           "AND a.applicationMasterGuid <> :applicationMasterGuid")
	    boolean isExistApplicationMasterIp4(@Param("applicationMasterIp4") String applicationMasterIp4, 
	                                        @Param("applicationMasterGuid") String applicationMasterGuid) throws SQLException;

	    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM ApplicationMaster a " +
	           "WHERE a.applicationMasterUrl = :applicationMasterUrl " +
	           "AND a.applicationMasterGuid <> :applicationMasterGuid")
	    boolean isExistApplicationMasterUrl(@Param("applicationMasterUrl") String applicationMasterUrl, 
	                                        @Param("applicationMasterGuid") String applicationMasterGuid) throws SQLException;
	}
