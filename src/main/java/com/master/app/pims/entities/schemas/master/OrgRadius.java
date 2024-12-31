package com.master.app.pims.entities.schemas.master;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "org_radius", schema="attendance")
public class OrgRadius implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "org_radius_guid")
	private String orgRadiusGuid;

	@Transient
	private String orgUnitName;

	@Column(name = "in_radius")
	private BigDecimal inRadius;

	@Column(name = "out_radius")
	private BigDecimal outRadius;

	@Column(name = "is_record_active")
	private Boolean isRecordActive;

	@Column(name = "is_verified")
	private Boolean isVerified;

	@Column(name = "is_modified")
	private Boolean isModified;

	@Column(name = "is_attested")
	private Boolean isAttested;

	@Column(name = "created_by_guid")
	private String createdByGuid;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "creater_ip")
	private String createdIp;

	@Column(name = "creater_mac_id")
	private String createdMacId;

	@Column(name = "creater_remarks")
	private String createdRemarks;

//	@Column(name = "verified_by_guid")
//	private String verifiedByGuid;
//
//	@Column(name = "verified_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date verifiedDate;
//
//	@Column(name = "verifier_ip")
//	private String verifierIp;
//
//	@Column(name = "verifier_mac_id")
//	private String verifierMacId;
//
//	@Column(name = "verfier_remarks")
//	private String verfierRemarks;

	@Column(name = "modified_by_guid")
	private String modifiedByGuid;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modifier_ip")
	private String modifiedIp;

	@Column(name = "modifier_mac_id")
	private String modifierMacId;

	@Column(name = "modifer_remarks")
	private String modifiedRemarks;

//	@Column(name = "attested_by_guid")
//	private String attestedByGuid;
//
//	@Column(name = "attested_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date attestedDate;
//
//	@Column(name = "attester_ip")
//	private String attesterIp;
//
//	@Column(name = "attester_mac_id")
//	private String attesterMacId;
//
//	@Column(name = "attester_remarks")
//	private String attesterRemarks;

//	@Column(name = "created_uri")
//	private String createdUri;
//
//	@Column(name = "verifier_uri")
//	private String verifierUri;
//
//	@Column(name = "modifier_uri")
//	private String modifierUri;
//
//	@Column(name = "attester_uri")
//	private String attesterUri;
//
//	@Column(name = "supporting_uri")
//	private String supportingUri;

	@Transient
	private String orgUnitBasicInfoGuid;

	@JoinColumn(name = "org_unit_basic_info_guid", referencedColumnName = "org_unit_basic_info_guid")
	@ManyToOne(optional = false)
	private OrgUnit orgUnitBasicInfoMaster;

	public OrgRadius(String orgRadiusGuid) {
		super();
		this.orgRadiusGuid = orgRadiusGuid;
	}

	
}
