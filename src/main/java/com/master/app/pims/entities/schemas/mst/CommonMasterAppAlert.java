<<<<<<< HEAD
package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_alert", schema="mst")
public class CommonMasterAppAlert implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@Column(name = "app_alert_guid")
	private String appAlertGuid;

	@Transient
	private String appMaster;

	@Column(name = "app_alert_subject_en")
	private String appAlertSubjectEn;
	
	@Column(name = "app_alert_subject_hi")
	private String appAlertSubjectHi;
	

	@Column(name = "app_alert_content_en")
	private String appAlertContentEn;

	@Column(name = "priority")
	private BigDecimal priority;

	@Column(name = "redirect_url")
	private String redirectUrl;

//	@Transient
//    private MultipartFile pdfFileName;

//    @Transient
//    private MultipartFile imageFileName;

    @Column(name = "active_from_date", nullable = false)
	private Date activeFromDate;

	@Column(name = "active_till_date", nullable=false)
	private Date activeTillDate;

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

//	@Column(name="pdf_file_name")
//    private String pdfFileName1;

//	@Column(name="image_uri")
//    private String imageUri;

	

    @Transient
    private String  applicationMasterGuid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "application_master_guid",referencedColumnName = "application_master_guid")
	private ApplicationMaster applicationMaster;

	public CommonMasterAppAlert(String appAlertGuid) {
		super();
		this.appAlertGuid = appAlertGuid;
	}

	

}
=======
package com.master.app.pims.entities.schemas.mst;

import jakarta.persistence.Entity;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "app_alert", schema="mst")
public class CommonMasterAppAlert implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@Column(name = "app_alert_guid")
	private String appAlertGuid;

	@Transient
	private String appMaster;

	@Column(name = "app_alert_subject_en")
	private String appAlertSubjectEn;

	@Column(name = "app_alert_content_en")
	private String appAlertContentEn;

	@Column(name = "priority")
	private BigDecimal priority;

	@Column(name = "redirect_url")
	private String redirectUrl;

	@Transient
    private MultipartFile pdfFileName;

    @Transient
    private MultipartFile imageFileName;

    @Column(name = "active_from_date", nullable = false)
	private Date activeFromDate;

	@Column(name = "active_till_date", nullable=false)
	private Date activeTill;

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

	@Column(name="pdf_file_name")
    private String pdfFileName1;

	@Column(name="image_uri")
    private String imageUri;

	@Column(name = "app_alert_subject_hi")
	private String appAlertSubjectHi;

    @Transient
    private String  applicationMasterGuid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "application_master_guid",referencedColumnName = "application_master_guid")
	private ApplicationMaster applicationMaster;

	public String getAppAlertGuid() {
		return appAlertGuid;
	}

	public void setAppAlertGuid(String appAlertGuid) {
		this.appAlertGuid = appAlertGuid;
	}

	public String getAppMaster() {
		return appMaster;
	}

	public void setAppMaster(String appMaster) {
		this.appMaster = appMaster;
	}

	public String getAppAlertSubjectEn() {
		return appAlertSubjectEn;
	}

	public void setAppAlertSubjectEn(String appAlertSubjectEn) {
		this.appAlertSubjectEn = appAlertSubjectEn;
	}

	public String getAppAlertSubjectHi() {
		return appAlertSubjectHi;
	}

	public void setAppAlertSubjectHi(String appAlertSubjectHi) {
		this.appAlertSubjectHi = appAlertSubjectHi;
	}

	public String getAppAlertContentEn() {
		return appAlertContentEn;
	}

	public void setAppAlertContentEn(String appAlertContentEn) {
		this.appAlertContentEn = appAlertContentEn;
	}

	public BigDecimal getPriority() {
		return priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public MultipartFile getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(MultipartFile pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public String getPdfFileName1() {
		return pdfFileName1;
	}

	public void setPdfFileName1(String pdfFileName1) {
		this.pdfFileName1 = pdfFileName1;
	}

	public MultipartFile getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(MultipartFile imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Date getActiveFromDate() {
		return activeFromDate;
	}

	public void setActiveFromDate(Date activeFromDate) {
		this.activeFromDate = activeFromDate;
	}

	public Date getActiveTill() {
		return activeTill;
	}

	public void setActiveTill(Date activeTill) {
		this.activeTill = activeTill;
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

	public String getApplicationMasterGuid() {
		return applicationMasterGuid;
	}

	public void setApplicationMasterGuid(String applicationMasterGuid) {
		this.applicationMasterGuid = applicationMasterGuid;
	}

	public ApplicationMaster getApplicationMaster() {
		return applicationMaster;
	}

	public void setApplicationMaster(ApplicationMaster applicationMaster) {
		this.applicationMaster = applicationMaster;
	}



}
>>>>>>> 9a0cc0e10dfd931c953030e185e960ef58e9b2eb
