package com.master.app.pims.entities.schemas.mst;




import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Setter
@Getter
@Entity
@Table(name = "geo_country", schema = "mst")
public class GeoCountryMst implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "country_mst_guid")
	private String countryMstGuid;
	
	@Transient
	private String masterCountry;
	
	@Column(name = "country_mobile_code")
	private String countryMobileCode;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "country_name_en")
	private String countryNameEn;
	
	@Column(name = "country_name_hi")
	private String countryNameHi;
	
	@Column(name = "country_name_rl")
	private String countryNameRl;
	
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
	private String createrRemarks;
	
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
	private String modiferRemarks;
	
	@Transient
	private String countryMasterGuid;
	
	@JoinColumn(name = "country_master_guid", referencedColumnName = "country_master_guid")
    @ManyToOne(optional = false)
    private GeoCountryMaster masterGeoCountryMaster;
}
