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

@Table(name = "application_master", schema="mst")
public class ApplicationMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@Column(name = "application_master_guid")
	private String applicationMasterGuid;

	@Column(name = "application_master_code")
	private String applicationMasterCode;

	@Column(name = "application_master_name")
	private String applicationMasterName;

	@Column(name = "application_master_ip4")
	private String applicationMasterIp4;

	@Column(name = "application_master_url")
	private String applicationMasterUrl;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_ip_addr")
	private String createdIpAddr;

	@Column(name = "created_mac_addr")
	private String createdMacAddr;

	@Column(name = "created_remarks")
	private String createdRemarks;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

	@Column(name = "modified_remarks")
	private String modifiedRemarks;

	public ApplicationMaster() {
		super();
	}
	public ApplicationMaster(String applicationMasterGuid) {
		super();
		this.applicationMasterGuid = applicationMasterGuid;
	}

	public String getApplicationMasterGuid() {
		return applicationMasterGuid;
	}

	public void setApplicationMasterGuid(String applicationMasterGuid) {
		this.applicationMasterGuid = applicationMasterGuid;
	}

	public String getApplicationMasterCode() {
		return applicationMasterCode;
	}

	public void setApplicationMasterCode(String applicationMasterCode) {
		this.applicationMasterCode = applicationMasterCode;
	}

	public String getApplicationMasterName() {
		return applicationMasterName;
	}

	public void setApplicationMasterName(String applicationMasterName) {
		this.applicationMasterName = applicationMasterName;
	}

	public String getApplicationMasterIp4() {
		return applicationMasterIp4;
	}

	public void setApplicationMasterIp4(String applicationMasterIp4) {
		this.applicationMasterIp4 = applicationMasterIp4;
	}

	public String getApplicationMasterUrl() {
		return applicationMasterUrl;
	}

	public void setApplicationMasterUrl(String applicationMasterUrl) {
		this.applicationMasterUrl = applicationMasterUrl;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedIpAddr() {
		return createdIpAddr;
	}

	public void setCreatedIpAddr(String createdIpAddr) {
		this.createdIpAddr = createdIpAddr;
	}

	public String getCreatedMacAddr() {
		return createdMacAddr;
	}

	public void setCreatedMacAddr(String createdMacAddr) {
		this.createdMacAddr = createdMacAddr;
	}

	public String getCreatedRemarks() {
		return createdRemarks;
	}

	public void setCreatedRemarks(String createdRemarks) {
		this.createdRemarks = createdRemarks;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedIpAddr() {
		return modifiedIpAddr;
	}

	public void setModifiedIpAddr(String modifiedIpAddr) {
		this.modifiedIpAddr = modifiedIpAddr;
	}

	public String getModifiedMacAddr() {
		return modifiedMacAddr;
	}

	public void setModifiedMacAddr(String modifiedMacAddr) {
		this.modifiedMacAddr = modifiedMacAddr;
	}

	public String getModifiedRemarks() {
		return modifiedRemarks;
	}

	public void setModifiedRemarks(String modifiedRemarks) {
		this.modifiedRemarks = modifiedRemarks;
	}



}
