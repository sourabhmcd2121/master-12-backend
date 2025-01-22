package com.master.app.pims.entities.schemas.mst;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "app_alert", schema="mst")
public class CommonMasterAppAlert implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@Column(name = "app_alert_guid")
	private String appAlertGuid;

	@Transient
	private String appMaster;

	@Column(name = "app_alert_subject_en")
	private String appAlertSubjectEn;
	
	@Column(name = "app_alert_subject_hi")
	private String appAlertSubjectHi;
	

	@Column(name = "app_alert_content_en")
	private String appAlertContentEn;

	@Column(name = "priority")
	private BigDecimal priority;

	@Column(name = "redirect_url")
	private String redirectUrl;

//	@Transient
//    private MultipartFile pdfFileName;

//    @Transient
//    private MultipartFile imageFileName;

    @Column(name = "active_from_date", nullable = false)
	private Date activeFromDate;

	@Column(name = "active_till_date", nullable=false)
	private Date activeTillDate;

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

//	@Column(name="pdf_file_name")
//    private String pdfFileName1;

//	@Column(name="image_uri")
//    private String imageUri;

	

    @Transient
    private String  applicationMasterGuid;

	@ManyToOne(optional = false)
	@JoinColumn(name = "application_master_guid",referencedColumnName = "application_master_guid")
	private ApplicationMaster applicationMaster;

	public CommonMasterAppAlert(String appAlertGuid) {
		super();
		this.appAlertGuid = appAlertGuid;
	}

	

}
