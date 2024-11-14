package com.master.app.pims.repositories.mst;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;

public interface SmsEmailTemplateRepository extends JpaRepository<SmsEmailTemplate,String> {
	
	 @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SmsEmailTemplate s WHERE s.smsemailTemplateCode = :smsemailTemplateCode AND s.smsemailTemplateGuid != :smsemailTemplateGuid")
	    boolean isExistMstSmsEmailTemplateCode(@Param("smsemailTemplateCode") String smsemailTemplateCode,
	                                           @Param("smsemailTemplateGuid") String smsemailTemplateGuid);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SmsEmailTemplate s WHERE s.emailServiceBodyEn = :emailServiceSubjectEn AND s.smsemailTemplateGuid != :smsemailTemplateGuid")
	    boolean isExistMstSmsEmailTemplateEmailServiceSubjectEn(@Param("emailServiceSubjectEn") String emailServiceSubjectEn,
	                                                            @Param("smsemailTemplateGuid") String smsemailTemplateGuid);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SmsEmailTemplate s WHERE s.emailServiceBodyEn = :emailServiceBodyEn AND s.smsemailTemplateGuid != :smsemailTemplateGuid")
	    boolean isExistMstSmsEmailTemplateEmailServiceBodyEn(@Param("emailServiceBodyEn") String emailServiceBodyEn,
	                                                         @Param("smsemailTemplateGuid") String smsemailTemplateGuid);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SmsEmailTemplate s WHERE s.smsServiceBodyEn = :smsServiceBodyEn AND s.smsemailTemplateGuid != :smsemailTemplateGuid")
	    boolean isExistMstSmsEmailTemplateSmsServiceBodyEn(@Param("smsServiceBodyEn") String smsServiceBodyEn,
	                                                       @Param("smsemailTemplateGuid") String smsemailTemplateGuid);

	    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SmsEmailTemplate s WHERE s.gimsServiceBodyEn = :gimsServiceBodyEn AND s.smsemailTemplateGuid != :smsemailTemplateGuid")
	    boolean isExistMstSmsEmailTemplateGimsServiceBodyEn(@Param("gimsServiceBodyEn") String gimsServiceBodyEn,
	                                                        @Param("smsemailTemplateGuid") String smsemailTemplateGuid);


	

}

    
   