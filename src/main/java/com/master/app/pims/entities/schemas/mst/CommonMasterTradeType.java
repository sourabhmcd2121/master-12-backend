package com.master.app.pims.entities.schemas.mst;

import java.io.Serializable;
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
@Table(name = "trade_type", schema = "mst")
public class CommonMasterTradeType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "trade_type_guid")
	private String tradeTypeGuid;

	@Transient
	private String tradeClassification;

	@Column(name = "trade_type_name_en")
	private String tradeTypeNameEn;

	@Column(name = "trade_type_name_hi")
	private String tradeTypeNameHi;

	@Column(name = "trade_type_name_rl")
	private String tradeTypeNameRl;

	@Column(name = "licence_period")
	private Integer licencePeriod;

	@Column(name = "trade_type_description")
	private String tradeTypeDesc;

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
	private String tradeClassficationGuid;

	@JoinColumn(name = "trade_classfication_guid", referencedColumnName = "trade_classfication_guid")
	@ManyToOne(optional = false)
	private CommonMasterTradeClassification tradeClassficationMaster;

	public CommonMasterTradeType(String tradeTypeGuid) {
		super();
		this.tradeTypeGuid = tradeTypeGuid;
	}
	
	

}
