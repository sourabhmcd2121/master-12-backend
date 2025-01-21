package com.master.app.pims.entities.schemas.mst;

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
@Table(name = "geo_colony", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "GeoColony.findAll", query = "SELECT g FROM GeoColony g")})
public class GeoColonyMCD implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "colony_guid")
    private String colonyGuid;
    
    @Transient
    private String ward;
    
    @Column(name = "colony_code")
    private String colonyCode;
    
    @Column(name = "colony_name_en")
    private String colonyNameEn;
    
    @Column(name = "colony_name_hi")
    private String colonyNameHi;
    
    @Column(name = "colony_name_rl")
    private String colonyNameRl;
    
    @Column(name = "colony_description")
    private String colonyDesc;

    @Column(name = "colony_type_other")
	private String colonyTypeOther;

	@Column(name = "colony_type_rural_urban")
	private String colonyTypeRuralUrban;

	@Column(name = "new_colony_code")
	private String newColonyCode;
	
//	@Column(name = "long_lat_info")
//	private String longLatInfo;
	
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
    private String modifedRemarks;
    
    @Column(name = "modified_uri")
    private String modifedUri;
    
    @Transient
    private String wardGuid;
    
    @JoinColumn(name = "ward_guid", referencedColumnName = "ward_guid")
    @ManyToOne(optional = false)
    private GeoWardMCD wardMaster;

	public GeoColonyMCD(String colonyGuid) {
		super();
		this.colonyGuid = colonyGuid;
	}
    
    
}