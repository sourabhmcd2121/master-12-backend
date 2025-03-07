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
@Table(name = "officer_image_comment", schema = "citizen_app")
public class OfficerImageComment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "officer_image_comment_guid", unique = true, nullable = false, length = 36)
	private String officerImageCommentGuid;
	
	@Transient
	private  String userImage1Base64;
	
	@Column(name = "image_binary")
	private byte[] imageBinary1;
	
	@Column(name = "image_heading_en", nullable = false)
	private String imageHeadingEn;
	
	@Column(name = "image_heading_hi", nullable=false)
	private String imageHeadingHi;
	
	@Column(name = "image_heading_rl")
	private String imageHeadingRl;
	
	@Column(name = "officer_name_en", nullable = false)
	private String officerNameEn;
	
	@Column(name = "officer_name_hi", nullable=false)
	private String officerNameHi;
	
	@Column(name = "officer_name_rl")
	private String officerNameRl;
	
	@Column(name = "officer_desig_en", nullable = false)
	private String officerDesigEn;
	
	@Column(name = "officer_desig_hi", nullable=false)
	private String officerDesigHi;
	
	@Column(name = "officer_desig_rl")
	private String officerDesigRl;
	
	@Column(name = "officer_comment_en", nullable = false)
	private String officerCommentEn;
	
	@Column(name = "officer_comment_hi", nullable=false)
	private String officerCommentHi;
	
	@Column(name = "officer_comment_rl")
	private String officerCommentRl;
	
	@Column(name = "other_html_content")
	private String otherHtmlContent;

	@Column(name = "portal_uri")
	private String portalUri;
	
	@Column(name = "facebook_uri")
	private String facebookUri;
	
	@Column(name = "twitter_uri")
	private String twitterUri;

	@Column(name = "priority", nullable = false, precision = 19)
	private Long priority;
	
	@Column(name = "is_active", nullable = false, length = 1)
	private Boolean isActive;
	
	@Column(name = "created_by", nullable=false)
	private String createdBy;
	
	@Column(name = "created_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_ip_addr", nullable=false)
	private String createdIpAddr;
	
	@Column(name = "created_mac_addr")
	private String createdMacAddr;
	
	@Column(name = "created_remarks")
	private String createdRemarks;
	
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

	public OfficerImageComment(String officerImageCommentGuid) {
		super();
		this.officerImageCommentGuid = officerImageCommentGuid;
	}

	
}
