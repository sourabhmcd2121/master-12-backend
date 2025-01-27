package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.master.OrgPrimary;

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
@Table(name = "trade_classfication", schema = "mst")
public class CommonMasterTradeClassification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "trade_classfication_guid")
	private String tradeClassficationGuid;

	@Transient
	private String orgPrimary;

	@Column(name = "trade_classfication_code")
	private String tradeClassficationCode;

	@Column(name = "trade_classfication_name_en")
	private String tradeClassficationNameEn;

	@Column(name = "trade_classfication_name_hi")
	private String tradeClassficationNameHi;

	@Column(name = "trade_classfication_name_rl")
	private String tradeClassficationNameRl;

	@Column(name = "trade_classfication_description")
	private String tradeClassficationDesc;

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

	@Column(name = "creator_remarks")
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

	@Column(name = "modifier_remarks")
	private String modifiedRemarks;

	@Column(name = "modified_uri")
	private String modifiedUri;

	@Transient
	private String orgPrimaryGuid;

	@JoinColumn(name = "primary_org_guid", referencedColumnName = "org_primary_guid")
	@ManyToOne(optional = false)
	private OrgPrimary orgPrimaryMaster;

	public CommonMasterTradeClassification(String tradeClassficationGuid) {
		super();
		this.tradeClassficationGuid = tradeClassficationGuid;
	}

}
