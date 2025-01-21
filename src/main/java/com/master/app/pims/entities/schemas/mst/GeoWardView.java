package com.master.app.pims.entities.schemas.mst;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "geo_ward_view", schema = "mst")
@Data
public class GeoWardView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ward_guid", nullable = false)
    private String wardGuid;

    @Column(name = "org_primary_name_en")
    private String primaryOrg;

    @Column(name = "zone_name_en")
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

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "org_primary_guid")
    private String primaryOrgGuid;

    @Column(name = "zone_guid")
    private String zoneGuid;

    @Column(name = "created_by")
    private String createdByGuid;

    @Column(name = "created_date", insertable = false, updatable = false)
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

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "ward_no")
    private String wardNo;
}
