package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter
@Getter
@Entity
@Table(name = "charge_details", schema = "mst")
public class MstChargeDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "charge_details_guid")
	private String chargeDetailsGuid;

	@Column(name = "charge_details_code")
	private String chargeDetailsCode;

	@Column(name = "charge_details_name_en")
	private String chargeDetailsNameEn;

	@Column(name = "charge_details_name_hi")
	private String chargeDetailsNameHi;

	@Column(name = "charge_details_name_rl")
	private String chargeDetailsNameRl;

	@Column(name = "charge_details_description")
	private String chargeDetailsDesc;

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

	
}
