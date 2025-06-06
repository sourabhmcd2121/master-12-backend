package com.master.app.pims.entities.schemas.property;
import java.io.Serializable;
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
@Table(name="mst_rebate", schema="property")
public class PropertyMasterRebate implements Serializable{

	private static final long serialVersionUID = 1L;

	protected static final String PK = "rebateGuid";

	@Id
	@Basic(optional = false)
	@Column(name="rebate_guid")
	private String rebateGuid;
	
	@Column(name="rebate_code")
	private String rebateCode;
	
	@Column(name="rebate_name_en")
	private String rebateNameEn;
	
	@Column(name="rebate_name_hi")
	private String rebateNameHi;
	
	@Column(name="rebate_name_rl")
	private String rebateNameRl;
	
	@Column(name="rebate_description")
	private String rebateDescription;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name="created_ip_addr")
	private String createdIpAddr;
	
	@Column(name="created_mac_addr")
	private String createdMacAddr;
	
	@Column(name="created_remarks")
	private String createdRemarks;
	
	@Column(name="created_uri")
	private String createdUri;
	
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
	
	@Column(name="modified_uri")
	private String modifiedUri;
	
	@Column(name="is_active")
	private Boolean isActive;

	public PropertyMasterRebate(String rebateGuid) {
		super();
		this.rebateGuid = rebateGuid;
	}

	
}
