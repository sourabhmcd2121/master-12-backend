package com.master.app.pims.entities.schemas.mst;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.OrgUnit;

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
@Table(name = "ref_sla", schema = "mst")
public class MstRefSla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "sla_guid")
	private String slaGuid;

	@Transient
	private String appMaster;

	@Transient
	private String processStatus;

	@Transient
	private String requestSubmissionType;

	@Column(name = "fe_stage_lavel")
	private String feStageLavel;

	@Column(name = "fe_status_code")
	private String feStatusCode;

	@Column(name = "next_action_due_in_days")
	private BigInteger nextActionDueInDays;

	@Column(name = "next_action_due")
	private String nextActionDue;



	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date", insertable = false, updatable = false)
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
	private String processStatusGuid;

	@Transient
	private String requestSubmissionTypeGuid;

	@Transient
	private String  applicationMasterGuid;
	
	

	@ManyToOne(optional = false)
	@JoinColumn(name = "application_master_guid",referencedColumnName = "application_master_guid")
	private ApplicationMaster applicationMaster;

	@JoinColumn(name = "process_status_guid", referencedColumnName = "process_status_guid")
	@ManyToOne(optional = false)
	private CommonMasterProcessStatus processStatusMaster;

	@JoinColumn(name = "request_submission_type_guid", referencedColumnName = "request_submission_type_guid")
	@ManyToOne(optional = false)
	private RequestSubmissionType requestSubmissionTypeMaster;

	public MstRefSla(String slaGuid) {
		super();
		this.slaGuid = slaGuid;
	}

	

}
