package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.OfficerImageComment;

public interface OfficerImageCommentRepo extends JpaRepository<OfficerImageComment, String>{
	// Check if an image heading exists in English
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.imageHeadingEn = :imageHeadingEn AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentImageHeadingEn(@Param("imageHeadingEn") String imageHeadingEn, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an image heading exists in Hindi
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.imageHeadingHi = :imageHeadingHi AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentImageHeadingHi(@Param("imageHeadingHi") String imageHeadingHi, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an image heading exists in Rajasthani (RL)
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.imageHeadingRl = :imageHeadingRl AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentImageHeadingRl(@Param("imageHeadingRl") String imageHeadingRl, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's comment exists in English
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerCommentEn = :officerCommentEn AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerCommentEn(@Param("officerCommentEn") String officerCommentEn, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's comment exists in Hindi
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerCommentHi = :officerCommentHi AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerCommentHi(@Param("officerCommentHi") String officerCommentHi, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's comment exists in Rajasthani (RL)
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerCommentRl = :officerCommentRl AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerCommentRl(@Param("officerCommentRl") String officerCommentRl, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's designation exists in English
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerDesigEn = :officerDesigEn AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerDesignEn(@Param("officerDesigEn") String officerDesigEn, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's designation exists in Hindi
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerDesigHi = :officerDesigHi AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerDesignHi(@Param("officerDesigHi") String officerDesigHi, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's designation exists in Rajasthani (RL)
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerDesigRl = :officerDesigRl AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerDesignRl(@Param("officerDesigRl") String officerDesigRl, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's name exists in English
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerNameEn = :officerNameEn AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerNameEn(@Param("officerNameEn") String officerNameEn, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's name exists in Hindi
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerNameHi = :officerNameHi AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerNameHi(@Param("officerNameHi") String officerNameHi, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's name exists in Rajasthani (RL)
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.officerNameRl = :officerNameRl AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentOfficerNameRl(@Param("officerNameRl") String officerNameRl, @Param("officerImageCommentGuid") String officerImageCommentGuid);

	// Check if an officer's priority exists
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfficerImageComment o WHERE o.priority = :priority AND o.officerImageCommentGuid != :officerImageCommentGuid")
	boolean isExistOfficerImageCommentPriority(@Param("priority") Long priority, @Param("officerImageCommentGuid") String officerImageCommentGuid);

}
