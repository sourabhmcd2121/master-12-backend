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
 * @author Sourbh
 */
@Entity
@Table(name = "associated_charges_info", schema="mst")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AssociatedChargesInfo.findAll", query = "SELECT g FROM AssociatedChargesInfo g")})
public class AssociatedChargesInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "associated_charges_info_guid")
    private String associatedChargesInfoGuid;
    @Column(name = "charge_code")
    private String chargeCode;
    @Column(name = "charge_name_en")
    private String chargeNameEn;
    @Column(name = "charge_name_hi")
    private String chargeNameHi;
    @Column(name = "charge_name_rl")
    private String chargeNameRl;
    @Column(name = "charge_info_description")
    private String chargeInfoDesc;
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

    public AssociatedChargesInfo(){
    	super();
    }
	public AssociatedChargesInfo(String associatedChargesInfoGuid) {
		super();
		this.associatedChargesInfoGuid = associatedChargesInfoGuid;
	}
	public String getAssociatedChargesInfoGuid() {
		return associatedChargesInfoGuid;
	}
	public void setAssociatedChargesInfoGuid(String associatedChargesInfoGuid) {
		this.associatedChargesInfoGuid = associatedChargesInfoGuid;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getChargeNameEn() {
		return chargeNameEn;
	}
	public void setChargeNameEn(String chargeNameEn) {
		this.chargeNameEn = chargeNameEn;
	}
	public String getChargeNameHi() {
		return chargeNameHi;
	}
	public void setChargeNameHi(String chargeNameHi) {
		this.chargeNameHi = chargeNameHi;
	}
	public String getChargeNameRl() {
		return chargeNameRl;
	}
	public void setChargeNameRl(String chargeNameRl) {
		this.chargeNameRl = chargeNameRl;
	}
	public String getChargeInfoDesc() {
		return chargeInfoDesc;
	}
	public void setChargeInfoDesc(String chargeInfoDesc) {
		this.chargeInfoDesc = chargeInfoDesc;
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
	public String getCreaterRemarks() {
		return createrRemarks;
	}
	public void setCreaterRemarks(String createrRemarks) {
		this.createrRemarks = createrRemarks;
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
	public String getModiferRemarks() {
		return modiferRemarks;
	}
	public void setModiferRemarks(String modiferRemarks) {
		this.modiferRemarks = modiferRemarks;
	}


}
