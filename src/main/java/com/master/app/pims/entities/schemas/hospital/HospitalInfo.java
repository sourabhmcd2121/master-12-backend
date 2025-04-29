package com.master.app.pims.entities.schemas.hospital;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.PersRelation;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
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
@Table(name = "mst_hospital_info", schema="hospital")
public class HospitalInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@Column(name = "hospital_info_guid")
	private String hospitalInfoGuid;

	@Column(name = "hospital_name")
	private String hospitalName;

	@Column(name = "hospital_registration_number")
	private String hospitalRegistrationNumber;

	@Column(name = "hospital_dghs_registration_date")
	private Date hospitalDghsRegistrationDate;

	@Column(name = "hospital_dghs_registration_number")
	private String hospitalDghsRegistrationNumber;

	@Column(name = "type_of_hospital")
	private String typeOfHospital;

	@Column(name = "hospital_bed")
	private Long hospitalBed;

	@Column(name = "hospital_anually_birth")
	private Long hospitalAnuallyBirth;

	@Column(name = "hospital_anually_death")
	private Long hospitalAnuallyDeath;

	@Column(name = "hospital_have_computers")
	private Boolean hospitalHaveComputers;

	@Column(name = "hospital_country")
	private String hospitalCountry;

	@Column(name = "hospital_state")
	private String hospitalState;

	@Column(name = "hospital_district")
	private String hospitalDistrict;

	@Transient
	private String hospitalPrimaryOrg;

	@Transient
	private String hospitalZone;

	@Column(name = "hospital_ward")
	private String hospitalWard;

	@Column(name = "hospital_colony")
	private String hospitalColony;

	@Column(name = "hospital_city")
	private String hospitalCity;

	@Column(name = "hospital_address")
	private String hospitalAddress;

	@Column(name = "hospital_pincode")
	private Long hospitalPincode;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_blocked")
	private Boolean isBlocked;

	@Column(name = "is_verified")
	private Boolean isVerified;

	@Column(name = "is_used")
	private Boolean isUsed;

	@Column(name = "hospital_lat_address")
	private BigDecimal hospitalLatAddress;

	@Column(name = "hospital_longt_address")
	private BigDecimal hospitalLongtAddress;



//	@Column(name = "hospital_user_firstname")
//	private String hospitalUserFirstname;
//
//	@Column(name = "hospital_user_middlename")
//	private String hospitalUserMiddlename;
//
//	@Column(name = "hospital_user_lastname")
//	private String hospitalUserLastname;

	@Column(name = "hospital_user_telphone")
	private Long hospitalUserTelphone;

	@Column(name = "hospital_user_emailid")
	private String hospitalUserEmailid;

	@Column(name = "login_id")
	private String loginId;

	@Column(name = "pwd")
	private String password;

	@Column(name = "salt")
	private String salt;

//	@Column(name = "hospital_admn_firstname")
//	private String hospitalAdmnFirstname;
//
//	@Column(name = "hospital_admn_middlename")
//	private String hospitalAdmnMiddlename;
//
//	@Column(name = "hospital_admn_lastname")
//	private String hospitalAdmnLastname;
//
//	@Column(name = "hospital_admn_telphone")
//	private Long hospitalAdmnTelphone;
//
//	@Column(name = "hospital_admn_emailid")
//	private String hospitalAdmnEmailid;
//
//	@Column(name = "hospital_doc_type_selected")
//	private String hospitalDocTypeSelected;

//	@Column(name = "blocked_date_time", insertable=false,updatable=false)
//    @Temporal(TemporalType.TIMESTAMP)
//	private Date blockedDateTime;
//
//	@Column(name = "blocked_reason")
//	private String blockedReason;
//
//	@Column(name = "active_date_time", insertable=false,updatable=false)
//    @Temporal(TemporalType.TIMESTAMP)
//	private Date activeDateTime;
//
//	@Column(name = "active_fromdate_time", insertable=false,updatable=false)
//    @Temporal(TemporalType.TIMESTAMP)
//	private Date activeFromDateTime;
//
//	@Column(name = "active_todate_time", insertable=false,updatable=false)
//    @Temporal(TemporalType.TIMESTAMP)
//	private Date activeToDateTime;
//
//	@Column(name = "active_reason")
//	private String activeReason;

//	@Column(name = "verify_date_time", insertable=false,updatable=false)
//    @Temporal(TemporalType.TIMESTAMP)
//	private Date verifyDateTime;
//
//	@Column(name = "verify_remarks")
//	private String verifyRemarks;

//	@Type(type = "jsonb")
//	@Column(columnDefinition = "jsonb", name = "verify_data_json")
//	private String verifyDataJson;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date", insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "created_ip_addr")
	private String createdIpAddr;

	@Column(name = "created_mac_addr")
	private String createdMacAddr;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date", insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

//	@Column(name = "hospital_form_data_xml")
//	private String hospitalFormDataXml;

//	@Type(type = "jsonb")
//	@Column(columnDefinition = "jsonb", name = "hospital_form_data_json")
//	private String hospitalFormDataJson;

	@Column(name = "applicable_primary_org_guid")
	private String primaryOrgGuid;

	@Column(name = "applicable_wrapper_org_guid")
	private String wrapperOrgGuid;

//	@Column(name = "parent_hospital_info_guid")
//	private String parentHospitalInfoGuid;


	@Column(name = "hospital_zone")
	private String hospitalZone1;

	@Transient
	private String countryMasterGuid;

	@Transient
	private String stateMasterGuid;

	@Transient
	private String districtMasterGuid;


	@Transient
	private String zoneGuid;

	@Transient
	private String wardGuid;

	@Transient
	private String colonyGuid;

	public HospitalInfo(String hospitalInfoGuid) {
		super();
		this.hospitalInfoGuid = hospitalInfoGuid;
	}

	
}
