package com.master.app.pims.entities.schemas.master;
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
@Table(name = "org_basic_info", schema="org")

public class OrgUnit implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "org_unit_basic_info_guid")
	private String orgUnitBasicInfoGuid;

	@Column(name = "org_primary_guid")
	private String orgPrimaryGuid;

	@Column(name = "org_unit_name_en")
	private String orgUnitNameEn;

	@Column(name = "org_unit_name_hi")
	private String orgUnitNameHi;

	@Column(name = "org_unit_name_rl")
	private String orgUnitNameRl;

	@Column(name = "org_unit_code")
	private String orgUnitCode;

	@Column(name = "parent_org_guid")
	private String parentOrgGuid;

	@Column(name = "parent_relation_type_guid")
	private String parentRelationTypeGuid;

	@Column(name = "entity_type_guid")
	private String entityTypeGuid;

	@Column(name = "has_sanctioned_post")
	private Boolean hasSanctionedPost;

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
	private String createdIp;

	@Column(name = "creater_mac_id")
	private String createdMacId;

	@Column(name = "creater_remarks")
	private String createdRemarks;

	@Column(name = "verified_by_guid")
	private String verifiedByGuid;

	@Column(name = "verified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifiedDate;

	@Column(name = "is_verified")
	private Boolean is_verified;

	@Column(name = "verifier_ip")
	private String verifierIp;

	@Column(name = "verifier_mac_id")
	private String verifierMacId;

	@Column(name = "verfier_remarks")
	private String verfierRemarks;

	@Column(name = "modified_by_guid")
	private String modifiedByGuid;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "is_modified")
	private Boolean is_modified;

	@Column(name = "modifier_ip")
	private String modifiedIp;

	@Column(name = "modifier_mac_id")
	private String modifierMacId;

	@Column(name = "modifer_remarks")
	private String modifiedRemarks;

	@Column(name = "attested_by_guid")
	private String attestedByGuid;

	@Column(name = "attested_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date attestedDate;

	@Column(name = "is_attested")
	private Boolean isAttested;

	@Column(name = "attester_ip")
	private String attesterIp;

	@Column(name = "attester_mac_id")
	private String attesterMacId;

	@Column(name = "attester_remarks")
	private String attesterRemarks;

	@Column(name = "created_uri")
	private String createdUri;

	@Column(name = "verifier_uri")
	private String verifierUri;

	@Column(name = "modifier_uri")
	private String modifierUri;

	@Column(name = "attester_uri")
	private String attesterUri;

	@Column(name = "supporting_uri")
	private String supportingUri;

	@Column(name = "wrapper_guid")
	private String wrapperGuid;

	@Column(name = "ddo_guid")
	private String ddoGuid;

	@Column(name = "is_root_org_unit")
	private Boolean isRootOrgUnit;

	public OrgUnit(String orgUnitBasicInfoGuid) {
		super();
		this.orgUnitBasicInfoGuid = orgUnitBasicInfoGuid;
	}



}
