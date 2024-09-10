/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;


/**
 *
 * @author hp
 */
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


	public GeoColonyCategory() {
		super();
	}
	public GeoColonyCategory(String colonyCategoryGuid) {
		super();
		this.colonyCategoryGuid = colonyCategoryGuid;
	}
	public String getColonyCategoryGuid() {
		return colonyCategoryGuid;
	}
	public void setColonyCategoryGuid(String colonyCategoryGuid) {
		this.colonyCategoryGuid = colonyCategoryGuid;
	}
	public String getColonyCategoryCode() {
		return colonyCategoryCode;
	}
	public void setColonyCategoryCode(String colonyCategoryCode) {
		this.colonyCategoryCode = colonyCategoryCode;
	}
	public String getColonyCategoryNameEn() {
		return colonyCategoryNameEn;
	}
	public void setColonyCategoryNameEn(String colonyCategoryNameEn) {
		this.colonyCategoryNameEn = colonyCategoryNameEn;
	}
	public String getColonyCategoryNameHi() {
		return colonyCategoryNameHi;
	}
	public void setColonyCategoryNameHi(String colonyCategoryNameHi) {
		this.colonyCategoryNameHi = colonyCategoryNameHi;
	}
	public String getColonyCategoryNameRl() {
		return colonyCategoryNameRl;
	}
	public void setColonyCategoryNameRl(String colonyCategoryNameRl) {
		this.colonyCategoryNameRl = colonyCategoryNameRl;
	}
	public String getColonyCategoryDesc() {
		return colonyCategoryDesc;
	}
	public void setColonyCategoryDesc(String colonyCategoryDesc) {
		this.colonyCategoryDesc = colonyCategoryDesc;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedByGuid() {
		return createdByGuid;
	}
	public void setCreatedByGuid(String createdByGuid) {
		this.createdByGuid = createdByGuid;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreaterIp() {
		return createrIp;
	}
	public void setCreaterIp(String createrIp) {
		this.createrIp = createrIp;
	}
	public String getCreaterMacId() {
		return createrMacId;
	}
	public void setCreaterMacId(String createrMacId) {
		this.createrMacId = createrMacId;
	}
	public String getModifiedByGuid() {
		return modifiedByGuid;
	}
	public void setModifiedByGuid(String modifiedByGuid) {
		this.modifiedByGuid = modifiedByGuid;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifierIp() {
		return modifierIp;
	}
	public void setModifierIp(String modifierIp) {
		this.modifierIp = modifierIp;
	}
	public String getModifierMacId() {
		return modifierMacId;
	}
	public void setModifierMacId(String modifierMacId) {
		this.modifierMacId = modifierMacId;
	}
	public String getCreaterRemarks() {
		return createrRemarks;
	}
	public void setCreaterRemarks(String createrRemarks) {
		this.createrRemarks = createrRemarks;
	}
	public String getModiferRemarks() {
		return modiferRemarks;
	}
	public void setModiferRemarks(String modiferRemarks) {
		this.modiferRemarks = modiferRemarks;
	}

}
