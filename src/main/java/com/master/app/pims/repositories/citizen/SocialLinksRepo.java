package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.SocialLinks;

public interface SocialLinksRepo extends JpaRepository<SocialLinks, String>{
	 // Custom query to check if a social link with the given English subject exists
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SocialLinks s WHERE s.socialLinksSubjectEn = :socialLinksSubjectEn AND s.socialLinksGuid != :socialLinksGuid")
    boolean isExistSocialLinksSubjectEn(@Param("socialLinksSubjectEn") String socialLinksSubjectEn, @Param("socialLinksGuid") String socialLinksGuid);
    
    // Custom query to check if a social link with the given Hindi subject exists
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SocialLinks s WHERE s.socialLinksSubjectHi = :socialLinksSubjectHi AND s.socialLinksGuid != :socialLinksGuid")
    boolean isExistSocialLinksSubjectHi(@Param("socialLinksSubjectHi") String socialLinksSubjectHi, @Param("socialLinksGuid") String socialLinksGuid);
    
    // Custom query to check if a social link with the given RL subject exists
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SocialLinks s WHERE s.socialLinksSubjectRl = :socialLinksSubjectRl AND s.socialLinksGuid != :socialLinksGuid")
    boolean isExistSocialLinksSubjectRl(@Param("socialLinksSubjectRl") String socialLinksSubjectRl, @Param("socialLinksGuid") String socialLinksGuid);
}
