package com.master.app.pims.entities.schemas.rbd;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.hospital.HospitalInfo;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.PersRelation;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
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
@Table(name = "fee_relexation_list", schema = "rbd")
public class RbdFeeRelaxationList implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "list_guid")
	private String listGuid;

	@Transient
	private String hospitalInfo;

	@Transient
	private String requestSubmissionType;

	@Column(name = "list_code")
	private String listCode;

	@Column(name = "list_name_en")
	private String listNameEn;

	@Column(name = "list_description")
	private String listDesc;

	@Column(name = "type_of_hospital")
	private String typeOfHospital;

	@Column(name = "submitted_for_type")
	private String submittedForType;

	@Column(name = "list_require_for_type")
	private String listRequireForType;

	@Column(name = "relaxed_amount")
	private BigDecimal relaxedAmount;

	@Column(name = "fromdate", nullable = false )
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Column(name = "todate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

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

	@Transient
	private String hospitalInfoGuid;

	@Transient
	private String requestSubmissionTypeGuid;

	@JoinColumn(name = "request_submission_type_guid", referencedColumnName = "request_submission_type_guid")
    @ManyToOne(optional = false)
    private RequestSubmissionType requestSubmissionTypeMaster;
	
	@JoinColumn(name = "hospital_info_guid", referencedColumnName = "hospital_info_guid")
    @ManyToOne(optional = false)
    private HospitalInfo hospitalInfoMaster;

	public RbdFeeRelaxationList(String listGuid) {
		super();
		this.listGuid = listGuid;
	}

	
	

}
