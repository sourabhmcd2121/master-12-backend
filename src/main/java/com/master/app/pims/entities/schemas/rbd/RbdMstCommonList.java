package com.master.app.pims.entities.schemas.rbd;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mst_common_list", schema = "rbd")
public class RbdMstCommonList implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @Column(name = "common_list_guid")
    private String commonListGuid;
	
    @Transient
    private String assessmentYear;
    @Transient
    private String requestSubmissionType;

    @Column(name = "common_list_code")
    private String commonListCode;

    @Column(name = "common_list_name_en")
    private String commonListNameEn;

    @Column(name = "common_list_name_hi")
    private String commonListNameHi;

    @Column(name = "common_list_name_rl")
    private String commonListNameRl;

    @Column(name = "common_list_description")
    private String commonListDesc;

    @Column(name = "submitted_for_type")
    private String submittedForType;

    @Column(name = "common_list_require_for_type")
    private String commonListRequireType;

    @Column(name = "is_mandatory")
    private Boolean isMandatory;

    @Column(name = "is_active")
    private Boolean isActive;

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

    @Column(name = "created_remarks")
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

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "modified_uri")
    private String modifiedUri;

    @Transient
    private String  assessmentYearGuid;

    @Transient
    private String  requestSubmissionTypeGuid;

    @JoinColumn(name = "assessment_year_guid", referencedColumnName = "assessment_year_guid")
    @ManyToOne(optional = false)
    private AssessmentYear assessmentYearMaster;

    @JoinColumn(name = "request_submission_type_guid", referencedColumnName = "request_submission_type_guid")
    @ManyToOne(optional = false)
    private RequestSubmissionType requestSubmissionTypeMaster;

	public RbdMstCommonList(String commonListGuid) {
		super();
		this.commonListGuid = commonListGuid;
	}


}
