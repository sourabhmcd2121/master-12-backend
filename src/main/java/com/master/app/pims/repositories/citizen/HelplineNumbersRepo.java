package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.HelplineNumbers;


public interface HelplineNumbersRepo extends JpaRepository<HelplineNumbers, String> {
	    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HelplineNumbers h WHERE h.helplineNumbersNameEn = :helplineNumbersNameEn AND h.helplineNumbersGuid != :helplineNumbersGuid")
	    boolean isExistHelplineNumbersNameEn(@Param("helplineNumbersNameEn") String helplineNumbersNameEn, @Param("helplineNumbersGuid") String helplineNumbersGuid);

	    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HelplineNumbers h WHERE h.helplineNumbersNameHi = :helplineNumbersNameHi AND h.helplineNumbersGuid != :helplineNumbersGuid")
	    boolean isExistHelplineNumbersNameHi(@Param("helplineNumbersNameHi") String helplineNumbersNameHi, @Param("helplineNumbersGuid") String helplineNumbersGuid);

	    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HelplineNumbers h WHERE h.helplineNumbersNameRl = :helplineNumbersNameRl AND h.helplineNumbersGuid != :helplineNumbersGuid")
	    boolean isExistHelplineNumbersNameRl(@Param("helplineNumbersNameRl") String helplineNumbersNameRl, @Param("helplineNumbersGuid") String helplineNumbersGuid);

}
