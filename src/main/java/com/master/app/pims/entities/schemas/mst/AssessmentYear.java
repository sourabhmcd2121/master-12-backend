/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

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
	public AssessmentYear(String assessmentYearGuid) {
		super();
		this.assessmentYearGuid = assessmentYearGuid;
	}

	

	

}
