package com.master.app.pims.entities.schemas.mst;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
@Entity
@Table(name = "unit_area", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "UnitArea.findAll", query = "SELECT g FROM UnitArea g")})
public class UnitArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "unit_area_guid")
    private String unitAreaGuid;
    @Column(name = "unit_area_code")
    private String unitAreaCode;
    @Column(name = "unit_area_name_en")
    private String unitAreaNameEn;
    @Column(name = "unit_area_name_hi")
    private String unitAreaNameHi;
    @Column(name = "unit_area_name_rl")
    private String unitAreaNameRl;
    @Column(name = "unit_area_description")
    private String unitAreaDesc;
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


  
}
