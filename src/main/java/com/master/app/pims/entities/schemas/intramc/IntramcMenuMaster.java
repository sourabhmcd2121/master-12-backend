package com.master.app.pims.entities.schemas.intramc;

import java.io.Serializable;
import java.util.Date;

import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;

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
@Table(name = "menu_master", schema = "intramc")
public class IntramcMenuMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "menu_master_guid")
	private String menuMasterGuid;

	@Column(name = "menu_code")
	private String intraMenuCode;

	@Column(name = "menu_name_en")
	private String intraMenuNameEn;

	@Column(name = "menu_name_hi")
	private String intraMenuNameHi;

	@Column(name = "menu_description")
	private String intraMenuDesc;

	@Column(name = "menu_uri")
	private String intraMenuUri;

	@Column(name = "app_code")
	private String appCode;

	@Column(name = "from_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Column(name = "to_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	@Column(name = "is_record_active")
	private Boolean isRecordActive;

	@Column(name = "created_by_guid")
	private String createdByGuid;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "creater_ip")
	private String createrIp;

	@Column(name = "creater_mac_id")
	private String createrMacId;

	@Column(name = "creater_remarks")
	private String createdRemarks;

	@Column(name = "modified_by_guid")
	private String modifiedByGuid;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modifier_ip")
	private String modifierIp;

	@Column(name = "modifier_mac_id")
	private String modifierMacId;

	@Column(name = "modifer_remarks")
	private String modifiedRemarks;

	@Column(name = "is_verified")
	private Boolean isVerified;

	@Column(name = "is_modified")
	private Boolean isModified;

	@Column(name = "is_attested")
	private Boolean isAttested;

	public IntramcMenuMaster(String menuMasterGuid) {
		super();
		this.menuMasterGuid = menuMasterGuid;
	}

	

}
