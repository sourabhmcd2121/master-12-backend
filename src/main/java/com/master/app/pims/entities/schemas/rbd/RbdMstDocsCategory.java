package com.master.app.pims.entities.schemas.rbd;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;

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
@Table(name = "mst_docs_category", schema = "rbd")
public class RbdMstDocsCategory implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "mst_docs_category_guid")
	private String mstDocsCategoryGuid;

	@Column(name = "docs_category_code")
	private String docsCategoryCode;

	@Column(name = "docs_category_name_en")
	private String docsCategoryNameEn;

	@Column(name = "docs_category_name_hi")
	private String docsCategoryNameHi;

	@Column(name = "docs_category_name_rl")
	private String docsCategoryNameRl;

	@Column(name = "mst_docs_category_description")
	private String mstDocsCategoryDesc;

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

	public RbdMstDocsCategory(String mstDocsCategoryGuid) {
		super();
		this.mstDocsCategoryGuid = mstDocsCategoryGuid;
	}




}
