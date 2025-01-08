package com.master.app.pims.entities.schemas.citizen;
import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;

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
@Table(name="header_ribbon", schema="citizen_app")
public class HeaderRibbon implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name="header_ribbon_guid",unique=true, nullable=false, length=36)
    private String headerRibbonGuid;
    
    @Transient
    private MultipartFile headerImage;
    
    @Column(name="image_binary", nullable=false)
    private byte[] imageBinary1;
    
    @Column(name="image_heading_en", nullable=false)
    private String imageHeadingEn;
    
    @Column(name="image_heading_hi", nullable=false)
    private String imageHeadingHi;
    
    @Column(name="image_heading_rl")
    private String imageHeadingRl;
    
    @Column(name="order_number", nullable=false)
    private Long orderNumber;

    @Column(name="header_image_url")
    private String headerImageUrl;

    @Transient
    private MultipartFile fileName;
    
    @Column(name="header_image_pdf")
    private String headerImagePdf1;

    @Column(name="is_active", nullable=false)
    private Boolean isActive;
    
    @Column(name="created_by", nullable=false)
    private String createdBy;
    
    @Column(name="created_date", nullable=false )
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name="created_ip_addr", nullable=false)
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

	public HeaderRibbon(String headerRibbonGuid) {
		super();
		this.headerRibbonGuid = headerRibbonGuid;
	}
    

}

