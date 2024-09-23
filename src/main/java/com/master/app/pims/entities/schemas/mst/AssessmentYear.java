/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

/**
 *
 * @author Sourbh
 */
@Entity
@Table(name = "assessment_year", schema = "mst")
//@XmlRootElement
//@NamedQueries({ @NamedQuery(name = "AssessmentYear.findAll", query = "SELECT g FROM AssessmentYear g") })
public class AssessmentYear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "assessment_year_guid")
	private String assessmentYearGuid;
	@Column(name = "assessment_year_code")
	private String assessmentYearCode;
	@Column(name = "assessment_year_description")
	private String assessmentYearDesc;
	@Basic(optional = false)
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;


	@Column(name = "general_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date generalStartDate;

	@Column(name = "general_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date generalEndDate;



	@Column(name = "start_year")
	private Integer startYear;
	@Column(name = "end_year")
	private Integer endYear;



	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private String createdByGuid;
	@Basic(optional = false)
	@Column(name = "created_date", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "created_ip_addr")
	private String createrIp;
	@Column(name = "created_mac_addr")
	private String createrMacId;
//	@Column(name = "created_remarks")
//	private String createrRemarks;

	@Column(name = "modified_by")
	private String modifiedByGuid;
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifierIp;
	@Column(name = "modified_mac_addr")
	private String modifierMacId;
//	@Column(name = "modified_remarks")
//	private String modiferRemarks;

	public AssessmentYear() {

	}

	public AssessmentYear(String assessmentYearGuid) {
		super();
		this.assessmentYearGuid = assessmentYearGuid;
	}

	public String getAssessmentYearGuid() {
		return assessmentYearGuid;
	}

	public void setAssessmentYearGuid(String assessmentYearGuid) {
		this.assessmentYearGuid = assessmentYearGuid;
	}

	public String getAssessmentYearCode() {
		return assessmentYearCode;
	}

	public void setAssessmentYearCode(String assessmentYearCode) {
		this.assessmentYearCode = assessmentYearCode;
	}






	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getGeneralStartDate() {
		return generalStartDate;
	}

	public void setGeneralStartDate(Date generalStartDate) {
		this.generalStartDate = generalStartDate;
	}

	public Date getGeneralEndDate() {
		return generalEndDate;
	}

	public void setGeneralEndDate(Date generalEndDate) {
		this.generalEndDate = generalEndDate;
	}


	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public String getAssessmentYearDesc() {
		return assessmentYearDesc;
	}

	public void setAssessmentYearDesc(String assessmentYearDesc) {
		this.assessmentYearDesc = assessmentYearDesc;
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

//	public String getCreaterRemarks() {
//		return createrRemarks;
//	}
//
//	public void setCreaterRemarks(String createrRemarks) {
//		this.createrRemarks = createrRemarks;
//	}

//	public String getModiferRemarks() {
//		return modiferRemarks;
//	}
//
//	public void setModiferRemarks(String modiferRemarks) {
//		this.modiferRemarks = modiferRemarks;
//	}

}
