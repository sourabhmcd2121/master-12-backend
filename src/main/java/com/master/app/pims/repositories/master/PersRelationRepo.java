package com.master.app.pims.repositories.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.master.PersRelation;

public interface PersRelationRepo extends JpaRepository<PersRelation, String>{
	 @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PersRelation p WHERE p.relationCode = :code AND p.persRelationGuid != :guid")
	    boolean isExistRelationCode(@Param("code") String code, @Param("guid") String guid);

	    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PersRelation p WHERE p.relationName = :nameEn AND p.persRelationGuid != :guid")
	    boolean isExistRelationName(@Param("nameEn") String nameEn, @Param("guid") String guid);
}
