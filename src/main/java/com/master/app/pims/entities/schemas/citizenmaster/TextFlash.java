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
@Table(name = "text_flash", schema = "citizen_app")
public class TextFlash implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "text_flash_guid", unique = true, nullable = false, length = 36)
	private String textFlashGuid;

	@Column(name = "text_flash_subject_en", nullable = false)
	private String textFlashSubjectEn;

	@Column(name = "text_flash_subject_hi", nullable = false)
	private String textFlashSubjectHi;

	@Column(name = "text_flash_subject_rl")
	private String textFlashSubjectRl;

	@Column(name = "text_flash_url")
	private String textFlashUrl;

	@Column(name = "text_flash_content_html_en")
	private String textFlashContentHtmlEn;

	@Column(name = "text_flash_content_html_hi")
	private String textFlashContentHtmlHi;

	@Column(name = "text_flash_content_html_rl")
	private String textFlashContentHtmlRl;

	@Transient
	private MultipartFile fileName;

	@Column(name = "pdf_file_name")
	private String pdfFileName1;

	@Column(name = "active_from_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date activeFromDate;

	@Column(name = "active_till", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date activeTill;

	@Column(name = "is_active", nullable = false, length = 1)
	private Boolean isActive;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

	@Column(name = "modified_remarks")
	private String modifiedRemarks;

}
