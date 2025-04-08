//package com.master.app.pims.entities.schemas.adm;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Optional;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.master.app.pims.entities.schemas.master.OrgPrimary;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Data
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "section_role_map", schema = "adm")
//public class SectionRoleMap implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Basic(optional = false)
//	@Column(name = "role_map_guid")
//	// @JsonProperty("roleMapGuid")
//	private String roleMapGuid;
//
//	@Transient
//	private String roleMaster;
//
//	@Column(name = "section_code")
//	private String sectionCode;
//
//
//	@Column(name = "is_active")
//	private Boolean isActive;
//
//	@Column(name = "created_by")
//	private String createdBy;
//
//	@Column(name = "created_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createdDate;
//
//	@Column(name = "created_ip_addr")
//	private String createdIpAddr;
//
//	@Column(name = "created_mac_addr")
//	private String createdMacAddr;
//
//	@Column(name = "created_remarks")
//	private String createdRemarks;
//
//	@Column(name = "created_uri")
//	private String createdUri;
//
//	@Column(name = "modified_by")
//	private String modifiedBy;
//
//	@Column(name = "modified_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date modifiedDate;
//
//	@Column(name = "modified_ip_addr")
//	private String modifiedIpAddr;
//
//	@Column(name = "modified_mac_addr")
//	private String modifiedMacAddr;
//
//	@Column(name = "modified_remarks")
//	private String modifiedRemarks;
//
//	@Column(name = "modified_uri")
//	private String modifiedUri;
//
//	@Transient
//	private String roleMasterGuid;
//
//	@JoinColumn(name = "role_guid", referencedColumnName = "role_master_guid")
//	@ManyToOne(optional = false)
//	private OrgPrimary rolesMaster;
//
//
//
//}
