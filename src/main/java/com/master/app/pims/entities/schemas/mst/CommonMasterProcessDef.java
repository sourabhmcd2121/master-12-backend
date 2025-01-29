package com.master.app.pims.entities.schemas.mst;
import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.OrgPrimary;

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
@Table(name = "process_def", schema="mst")
public class CommonMasterProcessDef implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@Column(name = "process_def_guid")
	private String processDefGuid;

	@Transient
	private String appMaster;

	@Transient
	private String orgPrimary;

	@Transient
	private String zone;

	@Column(name = "process_def_code")
	private String processDefCode;

	@Column(name = "process_def_name")
	private String processDefName;

	@Column(name = "prefill_data_query")
	private String prefillDataQuery;

	@Column(name = "have_prefill_data")
	private Boolean havePrefillData;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date", insertable=false,updatable=false)
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
	private String applicationMasterGuid;
	
	@Transient
	private String orgPrimaryGuid;
	
	@Transient
	private String zoneGuid;

	@JoinColumn(name = "application_master_guid", referencedColumnName = "application_master_guid")
    @ManyToOne(optional = false)
    private ApplicationMaster applicationMaster;
	
	@JoinColumn(name = "primary_org_guid", referencedColumnName = "org_primary_guid")
	@ManyToOne(optional = false)
	private OrgPrimary orgPrimaryMaster;
	
	@JoinColumn(name = "zone_guid", referencedColumnName = "zone_guid")
	@ManyToOne(optional = false)
	private GeoZoneMCD zoneMaster;

	public CommonMasterProcessDef(String processDefGuid) {
		super();
		this.processDefGuid = processDefGuid;
	}

	
}
