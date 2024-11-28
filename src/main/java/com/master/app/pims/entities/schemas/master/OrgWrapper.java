package com.master.app.pims.entities.schemas.master;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "org_wrapper", schema="master")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "OrgWrapper.findAll", query = "SELECT o FROM OrgWrapper o")})
public class OrgWrapper implements Serializable {
    private static final long serialVersionUID = 1L;
   /* @Basic(optional = false)
    @Column(name = "wrapper_id")
    private long wrapperId;*/
    @Id
    @Basic(optional = false)
    @Column(name = "wrapper_guid")
    private String wrapperGuid;
    @Column(name = "wrapper_code")
    private String wraperCode;
    @Column(name = "wrapper_name_en")
    private String wraperNameEn;
    @Column(name = "wrapper_name_hi")
    private String wraperNameHi;
    @Column(name = "wrapper_name_rl")
    private String wraperNameRl;
    @Column(name = "is_record_active")
    private Boolean isRecordActive;
    @Column(name = "wrapper_description")
    private String wraperDescription;
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
	public OrgWrapper(String wrapperGuid) {
		super();
		this.wrapperGuid = wrapperGuid;
	}


 
}
