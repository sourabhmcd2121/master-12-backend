package com.master.app.pims.entities.schemas.citizenmaster;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="social_links", schema="citizen_app")
public class SocialLinks implements Serializable {

	private static final long serialVersionUID = 1L;

    protected static final String PK = "socialLinksGuid";


    @Id
    @Basic(optional = false)
    @Column(name="social_links_guid")
    private String socialLinksGuid;
    
    @Column(name="social_links_subject_en")
    private String socialLinksSubjectEn;
    
    @Column(name="social_links_subject_hi")
    private String socialLinksSubjectHi;
    
    @Column(name="social_links_subject_rl")
    private String socialLinksSubjectRl;
    
    @Column(name="social_links_url")
    private String socialLinksUrl;
    
    @Transient
    private  String userImage1Base64;
    
    @Column(name="link_logo_img")
    private byte[] linkLogoImg1;
    
    @Column(name="is_active")
    private Boolean isActive;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name="created_ip_addr")
    private String createdIpAddr;
    
    @Column(name="created_mac_addr")
    private String createdMacAddr;
    
    @Column(name="created_remarks")
    private String createdRemarks;
    
    @Column(name="modified_by")
    private String modifiedBy;
    
    @Column(name="modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name="modified_ip_addr")
    private String modifiedIpAddr;
    
    @Column(name="modified_mac_addr")
    private String modifiedMacAddr;
    
    @Column(name="modified_remarks")
    private String modifiedRemarks;

	public SocialLinks(String socialLinksGuid) {
		super();
		this.socialLinksGuid = socialLinksGuid;
	}
    

}
