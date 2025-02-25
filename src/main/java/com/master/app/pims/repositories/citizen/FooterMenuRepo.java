package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.FooterMenu;


public interface FooterMenuRepo extends JpaRepository<FooterMenu, String>{
	 // Check if footer menu name in English exists
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterMenu f WHERE f.footerMenuNameEn = :footerMenuNameEn AND f.footerMenuGuid != :footerMenuGuid")
    boolean isExistFooterMenuNameEn(@Param("footerMenuNameEn") String footerMenuNameEn, @Param("footerMenuGuid") String footerMenuGuid);

    // Check if footer menu name in Hindi exists
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterMenu f WHERE f.footerMenuNameHi = :footerMenuNameHi AND f.footerMenuGuid != :footerMenuGuid")
    boolean isExistFooterMenuNameHi(@Param("footerMenuNameHi") String footerMenuNameHi, @Param("footerMenuGuid") String footerMenuGuid);

    // Check if footer menu name in Rajasthani exists
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterMenu f WHERE f.footerMenuNameRl = :footerMenuNameRl AND f.footerMenuGuid != :footerMenuGuid")
    boolean isExistFooterMenuNameRl(@Param("footerMenuNameRl") String footerMenuNameRl, @Param("footerMenuGuid") String footerMenuGuid);

    // Check if footer menu content in English exists
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterMenu f WHERE f.footerMenuContentHtmlEn = :footerMenuContentHtmlEn AND f.footerMenuGuid != :footerMenuGuid")
    boolean isExistFooterMenuContentHtmlEn(@Param("footerMenuContentHtmlEn") String footerMenuContentHtmlEn, @Param("footerMenuGuid") String footerMenuGuid);

    // Check if footer menu order number exists
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM FooterMenu f WHERE f.orderNumber = :orderNumber AND f.footerMenuGuid != :footerMenuGuid")
    boolean isExistFooterMenuOrderNumber(@Param("orderNumber") Long orderNumber, @Param("footerMenuGuid") String footerMenuGuid);
}
