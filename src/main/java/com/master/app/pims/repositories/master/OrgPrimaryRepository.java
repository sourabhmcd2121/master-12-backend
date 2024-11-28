package com.master.app.pims.repositories.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.master.OrgPrimary;

public interface OrgPrimaryRepository extends JpaRepository<OrgPrimary, String> {
	
	 @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgPrimary o WHERE o.orgPrimaryCode = :code AND o.orgPrimaryGuid != :guid")
	    boolean isExistOrgPrimaryCode(@Param("code") String code, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgPrimary o WHERE o.orgPrimaryNameEn = :nameEn AND o.orgPrimaryGuid != :guid")
	    boolean isExistOrgPrimaryNameEn(@Param("nameEn") String nameEn, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgPrimary o WHERE o.orgPrimaryNameHi = :nameHi AND o.orgPrimaryGuid != :guid")
	    boolean isExistOrgPrimaryNameHi(@Param("nameHi") String nameHi, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgPrimary o WHERE o.orgPrimaryNameRl = :nameRl AND o.orgPrimaryGuid != :guid")
	    boolean isExistOrgPrimaryNameRl(@Param("nameRl") String nameRl, @Param("guid") String guid);

}
