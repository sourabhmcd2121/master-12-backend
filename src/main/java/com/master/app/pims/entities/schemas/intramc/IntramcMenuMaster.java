package com.master.app.pims.entities.schemas.intramc;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_master", schema = "intramc")
public class IntramcMenuMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "menu_master_guid")
	private String menuMasterGuid;

	@Column(name = "menu_code")
	private String intraMenuCode;

	@Column(name = "menu_name_en")
	private String intraMenuNameEn;

	@Column(name = "menu_name_hi")
	private String intraMenuNameHi;

	@Column(name = "menu_description")
	private String intraMenuDesc;

	@Column(name = "menu_uri")
	private String intraMenuUri;

	@Column(name = "app_code")
	private String appCode;

	@Column(name = "from_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Column(name = "to_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	@Column(name = "is_record_active")
	private Boolean isRecordActive;

	@Column(name = "created_by_guid")
	private String createdByGuid;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "creater_ip")
	private String createrIp;

	@Column(name = "creater_mac_id")
	private String createrMacId;

	@Column(name = "creater_remarks")
	private String createdRemarks;

	@Column(name = "modified_by_guid")
	private String modifiedByGuid;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modifier_ip")
	private String modifierIp;

	@Column(name = "modifier_mac_id")
	private String modifierMacId;

	@Column(name = "modifer_remarks")
	private String modifiedRemarks;

	@Column(name = "is_verified")
	private Boolean isVerified;

	@Column(name = "is_modified")
	private Boolean isModified;

	@Column(name = "is_attested")
	private Boolean isAttested;

	public IntramcMenuMaster() {
		super();
	}
	public IntramcMenuMaster(String menuMasterGuid) {
		super();
		this.menuMasterGuid = menuMasterGuid;
	}

	public String getMenuMasterGuid() {
		return menuMasterGuid;
	}

	public void setMenuMasterGuid(String menuMasterGuid) {
		this.menuMasterGuid = menuMasterGuid;
	}

	public String getIntraMenuCode() {
		return intraMenuCode;
	}

	public void setIntraMenuCode(String intraMenuCode) {
		this.intraMenuCode = intraMenuCode;
	}

	public String getIntraMenuNameEn() {
		return intraMenuNameEn;
	}

	public void setIntraMenuNameEn(String intraMenuNameEn) {
		this.intraMenuNameEn = intraMenuNameEn;
	}

	public String getIntraMenuNameHi() {
		return intraMenuNameHi;
	}

	public void setIntraMenuNameHi(String intraMenuNameHi) {
		this.intraMenuNameHi = intraMenuNameHi;
	}

	public String getIntraMenuDesc() {
		return intraMenuDesc;
	}

	public void setIntraMenuDesc(String intraMenuDesc) {
		this.intraMenuDesc = intraMenuDesc;
	}

	public String getIntraMenuUri() {
		return intraMenuUri;
	}

	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public void setIntraMenuUri(String intraMenuUri) {
		this.intraMenuUri = intraMenuUri;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Boolean getIsRecordActive() {
		return isRecordActive;
	}

	public void setIsRecordActive(Boolean isRecordActive) {
		this.isRecordActive = isRecordActive;
	}

	public String getCreatedByGuid() {
		return createdByGuid;
	}

	public void setCreatedByGuid(String createdByGuid) {
		this.createdByGuid = createdByGuid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreaterIp() {
		return createrIp;
	}

	public void setCreaterIp(String createrIp) {
		this.createrIp = createrIp;
	}

	public String getCreaterMacId() {
		return createrMacId;
	}

	public void setCreaterMacId(String createrMacId) {
		this.createrMacId = createrMacId;
	}

	public String getModifiedByGuid() {
		return modifiedByGuid;
	}

	public void setModifiedByGuid(String modifiedByGuid) {
		this.modifiedByGuid = modifiedByGuid;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifierIp() {
		return modifierIp;
	}

	public void setModifierIp(String modifierIp) {
		this.modifierIp = modifierIp;
	}

	public String getModifierMacId() {
		return modifierMacId;
	}

	public void setModifierMacId(String modifierMacId) {
		this.modifierMacId = modifierMacId;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Boolean getIsModified() {
		return isModified;
	}

	public void setIsModified(Boolean isModified) {
		this.isModified = isModified;
	}

	public Boolean getIsAttested() {
		return isAttested;
	}

	public void setIsAttested(Boolean isAttested) {
		this.isAttested = isAttested;
	}
	public String getCreatedRemarks() {
		return createdRemarks;
	}
	public void setCreatedRemarks(String createdRemarks) {
		this.createdRemarks = createdRemarks;
	}
	public String getModifiedRemarks() {
		return modifiedRemarks;
	}
	public void setModifiedRemarks(String modifiedRemarks) {
		this.modifiedRemarks = modifiedRemarks;
	}


}
