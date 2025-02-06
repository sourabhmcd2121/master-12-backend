package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;

public interface FooterRibbonRepo extends JpaRepository<FooterRibbon, String>{
	  // Custom Query for checking existence of FooterRibbon by imageHeadingEn and footerRibbonGuid
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterRibbon f WHERE f.imageHeadingEn = :imageHeadingEn AND f.footerRibbonGuid != :footerRibbonGuid")
    boolean isExistFooterRibbonImageHeadingNameEn(@Param("imageHeadingEn") String imageHeadingEn, @Param("footerRibbonGuid") String footerRibbonGuid);

    // Custom Query for checking existence of FooterRibbon by imageHeadingHi and footerRibbonGuid
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterRibbon f WHERE f.imageHeadingHi = :imageHeadingHi AND f.footerRibbonGuid != :footerRibbonGuid")
    boolean isExistFooterRibbonImageHeadingNameHi(@Param("imageHeadingHi") String imageHeadingHi, @Param("footerRibbonGuid") String footerRibbonGuid);

    // Custom Query for checking existence of FooterRibbon by imageHeadingRl and footerRibbonGuid
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterRibbon f WHERE f.imageHeadingRl = :imageHeadingRl AND f.footerRibbonGuid != :footerRibbonGuid")
    boolean isExistFooterRibbonImageHeadingNameRl(@Param("imageHeadingRl") String imageHeadingRl, @Param("footerRibbonGuid") String footerRibbonGuid);

    // Custom Query for checking existence of FooterRibbon by orderNumber and footerRibbonGuid
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterRibbon f WHERE f.orderNumber = :orderNumber AND f.footerRibbonGuid != :footerRibbonGuid")
    boolean isExistFooterRibbonOrderNumber(@Param("orderNumber") Long orderNumber, @Param("footerRibbonGuid") String footerRibbonGuid);
}
