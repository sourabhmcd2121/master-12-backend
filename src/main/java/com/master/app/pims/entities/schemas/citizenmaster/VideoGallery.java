
package com.master.app.pims.entities.schemas.citizenmaster;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_gallery", schema = "citizen_app")
public class VideoGallery implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "video_gallery_guid", unique = true, nullable = false, length = 36)
	private String videoGalleryGuid;


	@Column(name = "video_file")
	private String videoFile;

	@Column(name = "video_gallery_name_en")
	private String videoGalleryNameEn;

	@Column(name = "video_gallery_name_hi")
	private String videoGalleryNameHi;

	@Column(name = "video_gallery_name_rl")
	private String videoGalleryNameRl;

	@Column(name = "video_gallery_url")
	private String videoGalleryUrl;

	@Column(name = "video_heading_en")
	private String videoHeadingEn;

	@Column(name = "video_heading_hi")
	private String videoHeadingHi;

	@Column(name = "video_heading_rl")
	private String videoHeadingRl;

	@Column(name = "order_number")
	private long orderNumber;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "created_ip_addr", nullable = false)
	private String createdIpAddr;

	@Column(name = "created_mac_addr")
	private String createdMacAddr;

	@Column(name = "created_remarks")
	private String createdRemarks;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

	@Column(name = "modified_remarks")
	private String modifiedRemarks;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	public VideoGallery(String videoGalleryGuid) {
		super();
		this.videoGalleryGuid = videoGalleryGuid;
	}


}
