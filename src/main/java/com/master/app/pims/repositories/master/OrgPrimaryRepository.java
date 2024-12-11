package com.master.app.pims.repositories.master;

import java.util.HashMap;
import java.util.List;

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

	    
	    // Fetch all records ordered by name
	    List<OrgPrimary> findAllByOrderByOrgPrimaryNameEn();

	    // Fetch active records with GUID and Name
	    @Query("SELECT o.orgPrimaryGuid, o.orgPrimaryNameEn " +
	           "FROM OrgPrimary o " +
	           "WHERE o.isRecordActive = true " +
	           "ORDER BY o.orgPrimaryNameEn")
	    List<Object[]> getOrgPrimaryListRaw();

	    // Transform raw results into a HashMap
	    default HashMap<String, String> getOrgPrimaryList() {
	        List<Object[]> results = getOrgPrimaryListRaw();
	        HashMap<String, String> map = new HashMap<>();
	        for (Object[] result : results) {
	            map.put((String) result[0], (String) result[1]);
	        }
	        return map;
	    }
	    
}
