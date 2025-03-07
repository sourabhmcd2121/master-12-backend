package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.TenderDetails;

public interface TenderDetailsRepo extends JpaRepository<TenderDetails, String>{

	    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TenderDetails t WHERE t.tenders = :tenders AND t.tenderGuid != :tenderGuid")
	    boolean isExistTenderDetailsTenders(@Param("tenders") String tenders, @Param("tenderGuid") String tenderGuid);

	    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TenderDetails t WHERE t.tRefNo = :tRefNo AND t.tenderGuid != :tenderGuid")
	    boolean isExistTenderDetailsTRefNo(@Param("tRefNo") String tRefNo, @Param("tenderGuid") String tenderGuid);

	    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TenderDetails t WHERE t.tTitle = :tTitle AND t.tenderGuid != :tenderGuid")
	    boolean isExistTenderDetailsTTitle(@Param("tTitle") String tTitle, @Param("tenderGuid") String tenderGuid);

	    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TenderDetails t WHERE t.tLocation = :tLocation AND t.tenderGuid != :tenderGuid")
	    boolean isExistTenderDetailsTLocation(@Param("tLocation") String tLocation, @Param("tenderGuid") String tenderGuid);

	    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TenderDetails t WHERE t.tInvitingOffAddress = :tInvitingOffAddress AND t.tenderGuid != :tenderGuid")
	    boolean isExistTenderDetailsTInvitingOffAddress(@Param("tInvitingOffAddress") String tInvitingOffAddress, @Param("tenderGuid") String tenderGuid);

}
