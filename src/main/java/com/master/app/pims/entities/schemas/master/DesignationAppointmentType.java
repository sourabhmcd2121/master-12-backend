/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.app.pims.entities.schemas.master;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
/**
 *
 * @author Sourbh
 */
@Entity
@Table(name = "pers_designation_appointment_type", schema="master")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "DesignationAppointmentType.findAll", query = "SELECT d FROM DesignationAppointmentType d")})
public class DesignationAppointmentType implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Basic(optional = false)
    @Column(name = "designation_appointment_type_id")
    private long designationAppointmentTypeId;*/
    @Id
    @Basic(optional = false)
    @Column(name = "designation_appointment_type_guid")
    private String designationAppointmentTypeGuid;
    @Column(name = "designation_appointment_type_code")
    private String designationAppointmentTypeCode;
    @Column(name = "designation_appointment_type_name")
    private String designationAppointmentTypeName;
    @Column(name = "description")
    private String description;
    @Column(name = "is_record_active")
    private Boolean isRecordActive;
    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;

    @Column(name = "created_by_guid")
    private String createdByGuid;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "creater_ip")
    private String createrIp;
    @Column(name = "creater_mac_id")
    private String createrMacId;
    @Column(name = "creater_remarks")
    private String createrRemarks;
    @Column(name = "verified_by_guid")
    private String verifiedByGuid;
    @Column(name = "verified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifiedDate;
    @Basic(optional = false)
    @Column(name = "is_verified")
    private boolean isVerified;
    @Column(name = "verifier_ip")
    private String verifierIp;
    @Column(name = "verifier_mac_id")
    private String verifierMacId;
    @Column(name = "verfier_remarks")
    private String verfierRemarks;
    @Column(name = "modified_by_guid")
    private String modifiedByGuid;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Basic(optional = false)
    @Column(name = "is_modified")
    private boolean isModified;
    @Column(name = "modifier_ip")
    private String modifierIp;
    @Column(name = "modifier_mac_id")
    private String modifierMacId;
    @Column(name = "modifer_remarks")
    private String modiferRemarks;
    @Column(name = "attested_by_guid")
    private String attestedByGuid;
    @Column(name = "attested_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date attestedDate;
    @Basic(optional = false)
    @Column(name = "is_attested")
    private boolean isAttested;
    @Column(name = "attester_ip")
    private String attesterIp;
    @Column(name = "attester_mac_id")
    private String attesterMacId;
    @Column(name = "attester_remarks")
    private String attesterRemarks;
    @Column(name = "created_uri")
    private String createdUri;
    @Column(name = "verifier_uri")
    private String verifierUri;
    @Column(name = "modifier_uri")
    private String modifierUri;
    @Column(name = "attester_uri")
    private String attesterUri;
    @Column(name = "supporting_uri")
    private String supportingUri;


    public DesignationAppointmentType() {
    }

    public DesignationAppointmentType(String designationAppointmentTypeGuid) {
        this.designationAppointmentTypeGuid = designationAppointmentTypeGuid;
    }

    public DesignationAppointmentType(String designationAppointmentTypeGuid, long designationAppointmentTypeId, Date fromDate, Date createdDate, boolean isVerified, boolean isModified, boolean isAttested) {
        this.designationAppointmentTypeGuid = designationAppointmentTypeGuid;
        //this.designationAppointmentTypeId = designationAppointmentTypeId;
        this.fromDate = fromDate;
        this.createdDate = createdDate;
        this.isVerified = isVerified;
        this.isModified = isModified;
        this.isAttested = isAttested;
    }

   /* public long getDesignationAppointmentTypeId() {
        return designationAppointmentTypeId;
    }

    public void setDesignationAppointmentTypeId(long designationAppointmentTypeId) {
        this.designationAppointmentTypeId = designationAppointmentTypeId;
    }*/

    public String getDesignationAppointmentTypeGuid() {
        return designationAppointmentTypeGuid;
    }

    public void setDesignationAppointmentTypeGuid(String designationAppointmentTypeGuid) {
        this.designationAppointmentTypeGuid = designationAppointmentTypeGuid;
    }

    public String getDesignationAppointmentTypeCode() {
        return designationAppointmentTypeCode;
    }

    public void setDesignationAppointmentTypeCode(String designationAppointmentTypeCode) {
        this.designationAppointmentTypeCode = designationAppointmentTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Boolean getIsRecordActive() {
        return isRecordActive;
    }

    public void setIsRecordActive(Boolean isRecordActive) {
        this.isRecordActive = isRecordActive;
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

    public String getVerifiedByGuid() {
        return verifiedByGuid;
    }

    public void setVerifiedByGuid(String verifiedByGuid) {
        this.verifiedByGuid = verifiedByGuid;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerifierIp() {
        return verifierIp;
    }

    public void setVerifierIp(String verifierIp) {
        this.verifierIp = verifierIp;
    }

    public String getVerifierMacId() {
        return verifierMacId;
    }

    public void setVerifierMacId(String verifierMacId) {
        this.verifierMacId = verifierMacId;
    }

    public String getVerfierRemarks() {
        return verfierRemarks;
    }

    public void setVerfierRemarks(String verfierRemarks) {
        this.verfierRemarks = verfierRemarks;
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

    public boolean getIsModified() {
        return isModified;
    }

    public void setIsModified(boolean isModified) {
        this.isModified = isModified;
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

    public String getAttestedByGuid() {
        return attestedByGuid;
    }

    public void setAttestedByGuid(String attestedByGuid) {
        this.attestedByGuid = attestedByGuid;
    }

    public Date getAttestedDate() {
        return attestedDate;
    }

    public void setAttestedDate(Date attestedDate) {
        this.attestedDate = attestedDate;
    }

    public boolean getIsAttested() {
        return isAttested;
    }

    public void setIsAttested(boolean isAttested) {
        this.isAttested = isAttested;
    }

    public String getAttesterIp() {
        return attesterIp;
    }

    public void setAttesterIp(String attesterIp) {
        this.attesterIp = attesterIp;
    }

    public String getAttesterMacId() {
        return attesterMacId;
    }

    public void setAttesterMacId(String attesterMacId) {
        this.attesterMacId = attesterMacId;
    }

    public String getAttesterRemarks() {
        return attesterRemarks;
    }

    public void setAttesterRemarks(String attesterRemarks) {
        this.attesterRemarks = attesterRemarks;
    }

    public String getCreatedUri() {
        return createdUri;
    }

    public void setCreatedUri(String createdUri) {
        this.createdUri = createdUri;
    }

    public String getVerifierUri() {
        return verifierUri;
    }

    public void setVerifierUri(String verifierUri) {
        this.verifierUri = verifierUri;
    }

    public String getModifierUri() {
        return modifierUri;
    }

    public void setModifierUri(String modifierUri) {
        this.modifierUri = modifierUri;
    }

    public String getAttesterUri() {
        return attesterUri;
    }

    public void setAttesterUri(String attesterUri) {
        this.attesterUri = attesterUri;
    }

    public String getSupportingUri() {
        return supportingUri;
    }

    public void setSupportingUri(String supportingUri) {
        this.supportingUri = supportingUri;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (designationAppointmentTypeGuid != null ? designationAppointmentTypeGuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesignationAppointmentType)) {
            return false;
        }
        DesignationAppointmentType other = (DesignationAppointmentType) object;
        if ((this.designationAppointmentTypeGuid == null && other.designationAppointmentTypeGuid != null) || (this.designationAppointmentTypeGuid != null && !this.designationAppointmentTypeGuid.equals(other.designationAppointmentTypeGuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DesignationAppointmentType[ designationAppointmentTypeGuid=" + designationAppointmentTypeGuid + " ]";
    }

	public String getDesignationAppointmentTypeName() {
		return designationAppointmentTypeName;
	}

	public void setDesignationAppointmentTypeName(String designationAppointmentTypeName) {
		this.designationAppointmentTypeName = designationAppointmentTypeName;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	public void setAttested(boolean isAttested) {
		this.isAttested = isAttested;
	}

}
