
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
@Table(name = "submitted_request_stage", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "SubmittedRequestStage.findAll", query = "SELECT g FROM SubmittedRequestStage g")})
public class SubmittedRequestStage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "submitted_request_stage_guid")
    private String submittedRequestStageGuid;
    @Column(name = "submitted_request_stage_code")
    private String submittedRequestStageCode;
    @Column(name = "submitted_request_stage_name_en")
    private String submittedRequestStageNameEn;
    @Column(name = "submitted_request_stage_name_hi")
    private String submittedRequestStageNameHi;
    @Column(name = "submitted_request_stage_name_rl")
    private String submittedRequestStageNameRl;
    @Column(name = "submitted_request_stage_description")
    private String submittedRequestStageDesc;
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
