package com.master.app.pims.repositories.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.master.OrgWrapper;

public interface OrgWrapperRepository extends JpaRepository<OrgWrapper, String>{
	 @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgWrapper o WHERE o.wraperCode = :code AND o.wrapperGuid != :guid")
	    boolean isExistOrgWrapperCode(@Param("code") String code, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgWrapper o WHERE o.wraperNameEn = :nameEn AND o.wrapperGuid != :guid")
	    boolean isExistOrgWrapperNameEn(@Param("nameEn") String nameEn, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgWrapper o WHERE o.wraperNameHi = :nameHi AND o.wrapperGuid != :guid")
	    boolean isExistOrgWrapperNameHi(@Param("nameHi") String nameHi, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrgWrapper o WHERE o.wraperNameRl = :nameRl AND o.wrapperGuid != :guid")
	    boolean isExistOrgWrapperNameRl(@Param("nameRl") String nameRl, @Param("guid") String guid);

}
