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
@Table(name = "flash_image", schema = "citizen_app")
public class FlashImage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "flash_image_guid")
	private String flashImageGuid;

	@Transient
	private String userImage1Base64;

	@Column(name = "image_binary")
	private byte[] imageBinary1;


	@Column(name = "image_heading_en")
	private String imageHeadingEn;
	
	@Column(name = "image_heading_hi")
	private String imageHeadingHi;
	
	@Column(name = "image_heading_rl")
	private String imageHeadingRl;
	
	@Column(name = "image_url")
	private String imageUrl;
	

	@Column(name = "order_number")
	private Long orderNumber;
	
	@Column(name = "is_active")
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

	public FlashImage(String flashImageGuid) {
		super();
		this.flashImageGuid = flashImageGuid;
	}
	
	
	


}
