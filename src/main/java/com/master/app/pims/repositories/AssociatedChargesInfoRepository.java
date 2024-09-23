package com.master.app.pims.repositories;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;

@Repository
public interface AssociatedChargesInfoRepository extends JpaRepository<AssociatedChargesInfo, String> {

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssociatedChargesInfo a WHERE a.chargeCode = :associatedChargesInfoCode AND a.associatedChargesInfoGuid != :associatedChargesInfoGuid")
	boolean isExistAssociatedChargesInfoCode(@Param("associatedChargesInfoCode") String associatedChargesInfoCode, @Param("associatedChargesInfoGuid") String associatedChargesInfoGuid) throws SQLException;

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssociatedChargesInfo a WHERE a.chargeNameEn = :associatedChargesInfoNameEn AND a.associatedChargesInfoGuid != :associatedChargesInfoGuid")
	boolean isExistAssociatedChargesInfoNameEn(@Param("associatedChargesInfoNameEn") String associatedChargesInfoNameEn, @Param("associatedChargesInfoGuid") String associatedChargesInfoGuid) throws SQLException;

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssociatedChargesInfo a WHERE a.chargeNameHi = :associatedChargesInfoNameHi AND a.associatedChargesInfoGuid != :associatedChargesInfoGuid")
	boolean isExistAssociatedChargesInfoNameHi(@Param("associatedChargesInfoNameHi") String associatedChargesInfoNameHi, @Param("associatedChargesInfoGuid") String associatedChargesInfoGuid) throws SQLException;

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssociatedChargesInfo a WHERE a.chargeNameRl = :associatedChargesInfoNameRl AND a.associatedChargesInfoGuid != :associatedChargesInfoGuid")
	boolean isExistAssociatedChargesInfoNameRl(@Param("associatedChargesInfoNameRl") String associatedChargesInfoNameRl, @Param("associatedChargesInfoGuid") String associatedChargesInfoGuid) throws SQLException;


}

