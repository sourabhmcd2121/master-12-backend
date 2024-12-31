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
@Table(name = "docs_category_info", schema = "mst")
public class DocsCategoryInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "docs_category_info_guid")
	private String docsCategoryInfoGuid;

	@Column(name = "docs_category_code")
	private String docsCategoryCode;

	@Column(name = "docs_category_name_en")
	private String docsCategoryNameEn;

	@Column(name = "docs_category_name_hi")
	private String docsCategoryNameHi;

	@Column(name = "docs_category_name_rl")
	private String docsCategoryNameRl;

	@Column(name = "docs_category_info_description")
	private String docsCategoryInfoDesc;

	@Column(name = "allowed_ext_type")
	private String allowedExtType;

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

	public DocsCategoryInfo(String docsCategoryInfoGuid) {
		super();
		this.docsCategoryInfoGuid = docsCategoryInfoGuid;
	}

	
}
