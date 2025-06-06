package com.master.app.pims.entities.schemas.property;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "mst_manual_receipt_series", schema = "property")
public class ManualReceiptSeries implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "receipt_series_guid")
	private String receiptSeriesGuid;

	@Column(name = "receipt_series_code")
	private String receiptSeriesCode;

	@Column(name = "receipt_series_from")
	private BigDecimal receiptSeriesFrom;

	@Column(name = "receipt_series_to")
	private BigDecimal receiptSeriesTo;

	@Column(name = "issue_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueDate;

	@Column(name = "receipt_series_description")
	private String receiptSeriesDesc;

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

	public ManualReceiptSeries(String receiptSeriesGuid) {
		super();
		this.receiptSeriesGuid = receiptSeriesGuid;
	}

	
}
