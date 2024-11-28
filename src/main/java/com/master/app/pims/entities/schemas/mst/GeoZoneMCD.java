package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgWrapper;

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
@Table(name = "geo_zone", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "GeoZoneMCD.findAll", query = "SELECT g FROM GeoZoneMCD g")})
public class GeoZoneMCD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "zone_guid")
    private String zoneGuid;
    
    @Transient
    private String orgPrimary;

    @Transient
    private String orgWrapper;

    @Column(name = "zone_code")
    private String zoneCode;
    @Column(name = "zone_name_en")
    private String zoneNameEn;
    @Column(name = "zone_name_hi")
    private String zoneNameHi;
    @Column(name = "zone_name_rl")
    private String zoneNameRl;
    @Column(name = "zonal_address")
    private String zonalAddress;

    @Column(name = "zone_description")
    private String zoneDesc;
    @Column(name = "is_active")
    private Boolean isActive;

   
    @Column(name = "created_by")
    private String createdByGuid;
    @Basic(optional = false)
    @Column(name = "created_date", insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_ip_addr")
    private String createrIp;
    @Column(name = "created_mac_addr")
    private String createrMacId;
    @Column(name = "created_remarks")
    private String createrRemarks;
    @Column(name = "modified_by")
    private String modifiedByGuid;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "modified_ip_addr")
    private String modifierIp;
    @Column(name = "modified_mac_addr")
    private String modifierMacId;
    @Column(name = "modified_remarks")
    private String modiferRemarks;
    
	@Transient
	private String orgPrimaryGuid;

    @Transient
    private String wrapperGuid;
    
    @JoinColumn(name = "primary_org_guid", referencedColumnName = "org_primary_guid")
	@ManyToOne(optional = false)
	private OrgPrimary orgPrimaryMaster;

	@JoinColumn(name = "wrapper_guid", referencedColumnName = "wrapper_guid")
    @ManyToOne(optional = false)
    private OrgWrapper wrapperMaster;

	public GeoZoneMCD(String zoneGuid) {
		super();
		this.zoneGuid = zoneGuid;
	}

	

	


}
