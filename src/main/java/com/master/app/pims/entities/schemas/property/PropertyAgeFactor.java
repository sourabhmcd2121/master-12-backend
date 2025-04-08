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
@Table(name = "mst_age_factor", schema = "property")
//@XmlRootElement
//@NamedQueries({ @NamedQuery(name = "PropertyAgeFactor.findAll", query = "SELECT g FROM PropertyAgeFactor g") })
public class PropertyAgeFactor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "age_factor_guid")
	private String ageFactorGuid;
	
	@Column(name = "age_factor_code")
	private String ageFactorCode;
	
	@Basic(optional = false)
	@Column(name = "age_factor_period_from")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ageFactorPeriodFrom;
	
	@Column(name = "age_factor_period_to")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ageFactorPeriodTo;
	
	@Column(name = "age_factor_description")
	private String ageFactorDesc;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private String createdByGuid;
	
	@Basic(optional = false)
	@Column(name = "created_date", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_ip_addr")
	private String createrIp;
	
	@Column(name = "created_mac_addr")
	private String createrMacId;
	
	@Column(name = "created_remarks")
	private String createrRemarks;

	@Column(name = "modified_by")
	private String modifiedByGuid;
	
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifierIp;
	
	@Column(name = "modified_mac_addr")
	private String modifierMacId;
	
	@Column(name = "modified_remarks")
	private String modiferRemarks;

	public PropertyAgeFactor(String ageFactorGuid) {
		super();
		this.ageFactorGuid = ageFactorGuid;
	}
	


}
