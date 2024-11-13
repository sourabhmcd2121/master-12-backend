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
@Table(name = "education_level", schema = "mst")
public class EducationLevel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "education_level_guid")
	private String educationLevelGuid;

	@Column(name = "education_level_code")
	private String educationLevelCode;

	@Column(name = "education_level_name_en")
	private String educationLevelNameEn;

	@Column(name = "education_level_name_hi")
	private String educationLevelNameHi;

	@Column(name = "education_level_name_rl")
	private String educationLevelNameRl;

	@Column(name = "education_level_description")
	private String educationLevelDesc;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_ip_addr")
	private String createdIpAddr;

	@Column(name = "creater_mac_id")
	private String createrMacId;

	@Column(name = "created_mac_addr")
	private String createdMacAddr;

	@Column(name = "created_remarks")
	private String createdRemarks;

	@Column(name = "created_uri")
	private String createdUri;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "is_modified")
	private Boolean isModified;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

	@Column(name = "modifer_remarks")
	private String modiferRemarks;

	@Column(name = "modified_uri")
	private String modifiedUri;

	@Column(name = "qualification_level_guid")
	private String qualificationLevelGuid;


}
