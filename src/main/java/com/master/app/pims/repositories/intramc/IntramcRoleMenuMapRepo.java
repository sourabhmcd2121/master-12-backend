package com.master.app.pims.repositories.intramc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;

public interface IntramcRoleMenuMapRepo extends JpaRepository<IntramcRoleMenuMap, String>{
    // Query for checking if a menu name exists (excluding the current record)
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM IntramcRoleMenuMap i WHERE i.roleCode = :roleCode AND i.refRoleMenuMapGuid != :refRoleMenuMapGuid")
    boolean isExistRoleCode(@Param("roleCode") String roleCode,@Param("refRoleMenuMapGuid") String refRoleMenuMapGuid);
}
