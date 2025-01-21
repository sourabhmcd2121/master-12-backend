package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;

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
@Table(name = "geo_ward", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "GeoWardMCD.findAll", query = "SELECT g FROM GeoWardMCD g")})
public class GeoWardMCD implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ward_guid")
    private String wardGuid;
    
//    @Transient
//    private String primaryOrg;
    
    @Transient
    private String zone;
    
    @Column(name = "ward_code")
    private String wardCode;
    
    @Column(name = "ward_name_en")
    private String wardNameEn;
    
    @Column(name = "ward_name_hi")
    private String wardNameHi;
    
    @Column(name = "ward_name_rl")
    private String wardNameRl;
    
    @Column(name = "ward_description")
    private String wardDesc;
    
    @Column(name = "area_code")
    private String areaCode;
    
    @Column(name = "ward_no")
    private String wardNo;
    
//    @Column(name = "lat_long_info")
//    private String latLongInfo;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
//    @Transient
//    private String primaryOrgGuid;
   
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
    
    @Transient
    private String zoneGuid;
    

    @JoinColumn(name = "zone_guid", referencedColumnName = "zone_guid")
    @ManyToOne(optional = false)
    private GeoZoneMCD zoneMaster;


	public GeoWardMCD(String wardGuid) {
		super();
		this.wardGuid = wardGuid;
	}

   


    
}
