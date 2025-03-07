package com.master.app.pims.entities.schemas.citizenmaster;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "tender_details", schema = "citizen_app")
public class TenderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "tender_guid")
    @JsonProperty("tenderGuid")
    private String tenderGuid;

    @Transient
    @JsonProperty("orgPrimary")
    private String orgPrimary;

    @Column(name = "tenders")
    @JsonProperty("tenders")
    private String tenders;

    @Column(name = "t_ref_no")
    @JsonProperty("tRefNo")
    private String tRefNo;

    @Column(name = "t_title")
    @JsonProperty("TTitle")
    private String tTitle;

    @Column(name = "t_location")
    @JsonProperty("TLocation")
    private String tLocation;

    @Column(name = "t_inviting_off_address")
    @JsonProperty("TInvitingOffAddress")
    private String tInvitingOffAddress;

    @Column(name = "t_pub_date")
    @JsonProperty("TPubDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tPubDate;

    @Column(name = "t_return_url")
    @JsonProperty("TReturnUrl")
    private String tReturnUrl;

    @Column(name = "is_active")
    @JsonProperty("isActive")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "created_ip_addr")
    private String createdIpAddr;

    @Column(name = "created_mac_addr")
    private String createdMacAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "created_uri")
    private String createdUri;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "modified_uri")
    private String modifiedUri;

    @Transient
    @JsonProperty("orgPrimaryGuid")
    private String orgPrimaryGuid;

    @JoinColumn(name = "primary_org_guid", referencedColumnName = "org_primary_guid")
    @ManyToOne(optional = false)
    private OrgPrimary orgPrimaryMaster;
	
	

	public TenderDetails(String tenderGuid) {
		super();
		this.tenderGuid = tenderGuid;
	}

	
}
