package com.master.app.pims.entities.schemas.property;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mst_floor", schema="property")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "PropertyFloor.findAll", query = "SELECT g FROM PropertyFloor g")})
public class PropertyFloor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "floor_guid")
    private String floorGuid;

    @Column(name = "floor_code")
    private String floorCode;

    @Column(name = "floor_name_en")
    private String floorNameEn;

    @Column(name = "floor_name_hi")
    private String floorNameHi;

    @Column(name = "floor_name_rl")
    private String floorNameRl;

    @Column(name = "floor_description")
    private String floorDesc;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdByGuid;

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
    private String modifiedByGuid;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;

    @Column(name = "modified_remarks")
    private String modifedRemarks;

	public PropertyFloor(String floorGuid) {
		super();
		this.floorGuid = floorGuid;
	}

   
}
