package com.master.app.pims.repositories.intramc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;

public interface IntramcMenuMasterRepo extends JpaRepository<IntramcMenuMaster, String> {
	  // Query for checking if a menu code exists (excluding the current record)
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM IntramcMenuMaster i WHERE i.intraMenuCode = :intraMenuCode AND i.menuMasterGuid != :menuMasterGuid")
    boolean isExistIntraMenuMasterCode(@Param("intraMenuCode") String intraMenuCode, @Param("menuMasterGuid") String menuMasterGuid);

    // Query for checking if a menu name exists (excluding the current record)
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM IntramcMenuMaster i WHERE i.intraMenuNameEn = :intraMenuNameEn AND i.menuMasterGuid != :menuMasterGuid")
    boolean isExistIntraMenuMasterNameEn(@Param("intraMenuNameEn") String intraMenuNameEn,@Param("menuMasterGuid") String menuMasterGuid);
}
