package com.master.app.pims.login;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "user_detail", schema="adm")

public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /*@Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
   	@SequenceGenerator(name="sequence", sequenceName="adm.user_sequence", allocationSize=1)
    @Column(name = "user_id")
    private Long userId;*/
    @Id
    @Basic(optional = false)
    @Column(name = "user_guid")
    private String userGuid;
    
    @Column(name = "login_id")
    private String loginId;
    
    @Column(name = "pwd")
    private String pwd;
    
   /* @Column(name = "user_name")
    private String userName;
    
    @Column(name = "gender")
    private Character gender;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name = "email_id")
    private String emailId;
    
    @Column(name = "mobile_number")
    private BigInteger mobileNumber;*/
    
    @Column(name = "emp_basic_info_guid")
    private String employeeGuid;
    
    @Column(name = "ldap_unique_id")
    private String ldapUniqueId;
    
   /* @Column(name = "citizen_id")
    private String citizenId;*/
    
    @Column(name = "user_ip_address")
    private String userIpAddress;
    
    @Column(name = "is_user_ip_restrict")
    private Boolean isUserIpRestrict;
    
    @Basic(optional = false)
    @Column(name = "is_blocked")
    private boolean isBlocked;
    
    @Column(name = "user_mac_address")
    private String userMacAddress;
    
    @Basic(optional = false)
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
    
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name = "creater_ip")
    private String createrIp;
    
    @Column(name = "creater_mac_id")
    private String createrMacId;
    
    @Column(name = "creater_remarks")
    private String createrRemarks;
    
    @Column(name = "verified_by_guid")
    private String verifiedByGuid;
    
    @Column(name = "verified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifiedDate;
    
    @Basic(optional = false)
    @Column(name = "is_verified")
    private boolean isVerified;
    
    @Column(name = "verifier_ip")
    private String verifierIp;
    
    @Column(name = "verifier_mac_id")
    private String verifierMacId;
    
    @Column(name = "verfier_remarks")
    private String verfierRemarks;
    
    @Column(name = "modified_by_guid")
    private String modifiedByGuid;
    
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Basic(optional = false)
    @Column(name = "is_modified")
    private boolean isModified;
    
    @Column(name = "modifier_ip")
    private String modifierIp;
    
    @Column(name = "modifier_mac_id")
    private String modifierMacId;
    
    @Column(name = "modifer_remarks")
    private String modiferRemarks;
    
    @Column(name = "attested_by_guid")
    private String attestedByGuid;
    
    @Column(name = "attested_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date attestedDate;
    
    @Basic(optional = false)
    @Column(name = "is_attested")
    private boolean isAttested;
    
    @Column(name = "attester_ip")
    private String attesterIp;
    
    @Column(name = "attester_mac_id")
    private String attesterMacId;
    
    @Column(name = "attester_remarks")
    private String attesterRemarks;
    
    @Column(name = "created_uri")
    private String createdUri;
    
    @Column(name = "verifier_uri")
    private String verifierUri;
    
    @Column(name = "modifier_uri")
    private String modifierUri;
    
    @Column(name = "attester_uri")
    private String attesterUri;
    
    @Column(name = "supporting_uri")
    private String supportingUri;
    
    @Column(name="registration_request")
    private Boolean registrationRequest;
    
    @Column(name="salt")
    private String salt;
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<UserRole> roles = new HashSet<>();
    

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetail")
    private List<UserRoleDetail> userRoleDetailList;*/
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "userGuid")
    private List<UserLoginTrail> userLoginTrailList;*/

   
}
