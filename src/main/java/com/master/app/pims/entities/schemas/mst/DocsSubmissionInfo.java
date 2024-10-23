/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "docs_submission_info", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "DocsSubmissionInfo.findAll", query = "SELECT g FROM DocsSubmissionInfo g")})
public class DocsSubmissionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @Column(name = "docs_submission_info_guid")
    private String docsSubmissionInfoGuid;

    @Column(name = "docs_code")
    private String docsCode;

    @Column(name = "docs_name_en")
    private String docsNameEn;

    @Column(name = "docs_name_hi")
    private String docsNameHi;

    @Column(name = "docs_name_rl")
    private String docsNameRl;

    @Column(name = "docs_info_description")
    private String docsInfoDesc;

//    @Column(name = "docs_submission_info_serialno")
//    private Long docsSerialno;

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

    @Column(name = "creator_remarks")
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

    @Column(name = "modifier_remarks")
    private String modiferRemarks;


	
}
