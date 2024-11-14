package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "smsemail_template", schema = "mst")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class SmsEmailTemplate implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "smsemail_template_guid")
	private String smsemailTemplateGuid;

	@Column(name = "smsemail_template_name")
	private String smsemailTemplateName;

	@Column(name = "smsemail_linked_header")
	private String smsemailLinkedHeader;

	@Column(name = "smsemail_entity_id ")
	private String smsemailEntityId;

	@Column(name = "smsemail_template_code")
	private String smsemailTemplateCode;

//	@Column(name = "email_service_subject_en")
//	private String emailServiceSubjectEn;

//	@Column(name = "email_service_subject_hi")
//	private String emailServiceSubjectHi;
//
//	@Column(name = "email_service_subject_rl")
//	private String emailServiceSubjectRl;

	@Column(name = "email_service_body_en")
	private String emailServiceBodyEn;

//	@Column(name = "email_service_body_hi")
//	private String emailServiceBodyHi;
//
//	@Column(name = "email_service_body_Rl")
//	private String emailServiceBodyRl;

//	@Type(type = "jsonb")
//	@Column(columnDefinition = "jsonb", name = "email_attachment_details")
//	private String emailAttachmentDetails;

//	@Column(name = "email_attachment_path")
//	private String emailAttachmentPath;

	@Column(name = "sms_service_body_en")
	private String smsServiceBodyEn;

//	@Column(name = "sms_service_body_hi")
//	private String smsServiceBodyHi;
//
//	@Column(name = "sms_service_body_rl")
//	private String smsServiceBodyRl;

	@Column(name = "gims_service_body_en")
	private String gimsServiceBodyEn;

//	@Column(name = "gims_service_body_hi")
//	private String gimsServiceBodyHi;
//
//	@Column(name = "gims_service_body_rl")
//	private String gimsServiceBodyRl;

//	@Column(name = "keywords_variables_used")
//	private String keywordsVariablesUsed;

//	@Column(name = "smsemail_template_description")
//	private String smsemailTemplateDesc;

	@Column(name = "is_mail")
	private Boolean isMail;

	@Column(name = "is_sms")
	private Boolean isSms;

	@Column(name = "is_gims")
	private Boolean isGims;

	@Column(name = "is_pre_formatted")
	private Boolean isPreFormatted;

	@Column(name = "is_encoded")
	private Boolean isEncoded;

	@Column(name = "is_broadcast")
	private Boolean isBroadcast;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_email_attachment")
	private Boolean isEmailAttachment;

	@Column(name = "created_by")
    private String createdBy;

    @Basic(optional = false)
    @Column(name = "created_date", insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "created_ip_addr")
    private String createdIpAddr;

    @Column(name = "created_mac_addr")
    private String createdMacAddr;

    @Column(name = "creator_remarks")
    private String createdRemarks;

    @Column(name = "created_uri")
    private String createdUri;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;

    @Column(name = "modifier_remarks")
    private String modifiedRemarks;

    @Column(name = "modified_uri")
    private String modifiedUri;

}
