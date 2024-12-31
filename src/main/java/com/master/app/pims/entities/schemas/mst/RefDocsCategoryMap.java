package com.master.app.pims.entities.schemas.mst;
import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgWrapper;

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
@Table(name = "ref_doc_category_map", schema = "mst")
public class RefDocsCategoryMap implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "docs_category_map_guid")
	private String docsCategoryMapGuid;

	@Transient
	private String assessmentYear;

	@Transient
	private String docsCategoryInfo;

	@Transient
	private String docsSubmissionInfo;

	@Transient
	private String requestSubmissionType;

	@Column(name = "is_extra_doc_info_required")
	private Boolean isExtraDocInfoRequired;

	@Column(name = "is_mandatory")
	private Boolean isMandatory;

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

	@Transient
	private String assessmentYearGuid;
	
	@Transient
	private String docsCategoryInfoGuid;
	
	@Transient
	private String docsSubmissionInfoGuid;
	
	@Transient
	private String requestSubmissionTypeGuid;


	@JoinColumn(name = "assessment_year_guid", referencedColumnName = "assessment_year_guid")
    @ManyToOne(optional = false)
    private AssessmentYear assessmentYearMaster;

	@JoinColumn(name = "docs_category_info_guid", referencedColumnName = "docs_category_info_guid")
    @ManyToOne(optional = false)
    private DocsCategoryInfo docsCategoryInfoMaster;

	@JoinColumn(name = "docs_submission_info_guid", referencedColumnName = "docs_submission_info_guid")
    @ManyToOne(optional = false)
    private DocsSubmissionInfo docsSubmissionInfoMaster;

	@JoinColumn(name = "request_submission_type_guid", referencedColumnName = "request_submission_type_guid")
	@ManyToOne(optional = false)
	private RequestSubmissionType requestSubmissionTypeMaster;

	public RefDocsCategoryMap(String docsCategoryMapGuid) {
		super();
		this.docsCategoryMapGuid = docsCategoryMapGuid;
	}
	
	
}
