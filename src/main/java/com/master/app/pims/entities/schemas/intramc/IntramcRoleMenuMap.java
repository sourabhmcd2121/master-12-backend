package com.master.app.pims.entities.schemas.intramc;

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
@Table(name = "ref_role_menu_map", schema = "intramc")
public class IntramcRoleMenuMap implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
	@Column(name = "ref_role_menu_map_guid")
	private String refRoleMenuMapGuid;

	@Transient
	private String menuMaster;

	@Column(name = "role_code")
	private String roleCode;

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
	private String menuMasterGuid;

	@JoinColumn(name = "menu_master_guid", referencedColumnName = "menu_master_guid")
	@ManyToOne(optional = false)
	private IntramcMenuMaster intramcMenuMasterMaster;

	public IntramcRoleMenuMap(String refRoleMenuMapGuid) {
		super();
		this.refRoleMenuMapGuid = refRoleMenuMapGuid;
	}

	
}
