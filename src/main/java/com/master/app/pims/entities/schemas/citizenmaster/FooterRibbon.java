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
@Table(name = "footer_ribbon", schema = "citizen_app")
public class FooterRibbon implements Serializable {
	private static final long serialVersionUID = 1L;

	protected static final String PK = "footerRibbonGuid";

	@Id
	@Basic(optional = false)
	@Column(name = "footer_ribbon_guid", unique = true, nullable = false, length = 36)
	private String footerRibbonGuid;

	@Transient
	private String userImage1Base64;
	
	@Column(name = "image_binary", nullable=false)
	private byte[] imageBinary1;
	
	@Column(name = "image_heading_en", nullable=false)
	private String imageHeadingEn;
	
	@Column(name = "image_heading_hi", nullable=false)
	private String imageHeadingHi;
	
	@Column(name = "image_heading_rl")
	private String imageHeadingRl;

	@Column(name = "footer_image_url", nullable=false)
	private String footerImageUrl;
	
	@Column(name = "footer_content", nullable=false)
	private String footerContent;

	@Column(name = "order_number", nullable=false)
	private Long orderNumber;
	
	@Column(name = "is_active", nullable=false)
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

	public FooterRibbon(String footerRibbonGuid) {
		super();
		this.footerRibbonGuid = footerRibbonGuid;
	}
	
	


}
