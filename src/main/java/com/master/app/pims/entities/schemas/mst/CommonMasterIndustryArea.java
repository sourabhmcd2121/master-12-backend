package com.master.app.pims.entities.schemas.mst;
import java.io.Serializable;
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
@Table(name = "geo_industryarea", schema = "mst")
public class CommonMasterIndustryArea implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "industry_guid")
	private String industryGuid;

	@Transient
	private String zone;

	@Transient
	private String orgUnitName;

	@Column(name = "industry_code")
	private String industryCode;

	@Column(name = "industry_name_en")
	private String industryNameEn;

	@Column(name = "industry_name_hi")
	private String industryNameHi;

	@Column(name = "industry_name_rl")
	private String industryNameRl;

	@Column(name = "industry_description")
	private String industryDesc;

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
	private String zoneGuid;

	@Transient
	private String orgUnitBasicInfoGuid;

	@JoinColumn(name = "zone_guid", referencedColumnName = "zone_guid")
	@ManyToOne(optional = false)
	private GeoZoneMCD zoneMaster;

	@JoinColumn(name = "org_unit_basic_info_guid", referencedColumnName = "org_unit_basic_info_guid")
	@ManyToOne(optional = false)
	private OrgUnit orgUnitBasicInfoMaster;

	public CommonMasterIndustryArea(String industryGuid) {
		super();
		this.industryGuid = industryGuid;
	}
	
	

	
}
