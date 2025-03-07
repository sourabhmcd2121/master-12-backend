package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.TenderDetails;
import com.master.app.pims.entities.schemas.citizenmaster.WebInfoManager;

public interface WebInfoManagerRepo extends JpaRepository<WebInfoManager, String>{
	  @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WebInfoManager w WHERE w.officerName = :officerName AND w.infoManagerGuid != :infoManagerGuid")
	    boolean isExistWebInfoManagerOfficerName(@Param("officerName") String officerName, @Param("infoManagerGuid") String infoManagerGuid);

	    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WebInfoManager w WHERE w.telNumber = :telNumber AND w.infoManagerGuid != :infoManagerGuid")
	    boolean isExistWebInfoManagerTelNumber(@Param("telNumber") String telNumber, @Param("infoManagerGuid") String infoManagerGuid);

	    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM WebInfoManager w WHERE w.emailId = :emailId AND w.infoManagerGuid != :infoManagerGuid")
	    boolean isExistWebInfoManagerEmailId(@Param("emailId") String emailId, @Param("infoManagerGuid") String infoManagerGuid);
}
