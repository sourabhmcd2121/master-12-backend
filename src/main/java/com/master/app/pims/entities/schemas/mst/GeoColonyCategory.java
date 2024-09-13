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


/**
 *
 * @author Sourbh
 */
@Data
@Setter
@Getter
@Entity
@Table(name = "geo_colony_category", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "GeoColonyCategory.findAll", query = "SELECT g FROM GeoColonyCategory g")})
public class GeoColonyCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "colony_category_guid")
    private String colonyCategoryGuid;
    @Column(name = "colony_category_code")
    private String colonyCategoryCode;
    @Column(name = "colony_category_name_en")
    private String colonyCategoryNameEn;
    @Column(name = "colony_category_name_hi")
    private String colonyCategoryNameHi;
    @Column(name = "colony_category_name_rl")
    private String colonyCategoryNameRl;
    @Column(name = "colony_category_description")
    private String colonyCategoryDesc;
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
    
	public GeoColonyCategory(String colonyCategoryGuid) {
		super();
		this.colonyCategoryGuid = colonyCategoryGuid;
	}

	public GeoColonyCategory() {
		super();
		// TODO Auto-generated constructor stub
	}




}
