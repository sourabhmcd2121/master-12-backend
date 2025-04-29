package com.master.app.pims.entities.schemas.rbd;
import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ref_death_docs_map", schema = "rbd")
public class RbdRefDeathDocsMap implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "death_docs_map_guid")
	private String deathDocsMapGuid;

	@Transient
	private String assessmentYear;

	@Transient
	private String rbdDocsCategory;

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
	private String mstDocsCategoryGuid;

	@Transient
	private String requestSubmissionTypeGuid;
	
	@Transient
	private String docsSubmissionInfoGuid;

	@JoinColumn(name = "assessment_year_guid", referencedColumnName = "assessment_year_guid")
    @ManyToOne(optional = false)
    private AssessmentYear assessmentYearMaster;

	@JoinColumn(name = "mst_docs_category_guid", referencedColumnName = "mst_docs_category_guid")
    @ManyToOne(optional = false)
    private RbdMstDocsCategory rbdDocsCategoryMaster;

	@JoinColumn(name = "request_submission_type_guid", referencedColumnName = "request_submission_type_guid")
    @ManyToOne(optional = false)
    private RequestSubmissionType requestSubmissionTypeMaster;
	
	@JoinColumn(name = "docs_submission_info_guid", referencedColumnName = "docs_submission_info_guid")
    @ManyToOne(optional = false)
    private DocsSubmissionInfo docsSubmissionInfoMaster;

	public RbdRefDeathDocsMap(String deathDocsMapGuid) {
		super();
		this.deathDocsMapGuid = deathDocsMapGuid;
	}
	
	

	
}
