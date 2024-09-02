package com.master.app.pims.models.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DesignationAppointmentTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String designationAppointmentTypeGuid;
    private String designationAppointmentTypeCode;
    private String designationAppointmentTypeName;
    private String description;
    private Boolean isRecordActive;
    private Date fromDate;
    private Date toDate;
    private String createdByGuid;
    private Date createdDate;
    private String createrIp;
    private String createrMacId;
    private String createrRemarks;
    private String verifiedByGuid;
    private Date verifiedDate;
    private boolean isVerified;
    private String verifierIp;
    private String verifierMacId;
    private String verfierRemarks;
    private String modifiedByGuid;
    private Date modifiedDate;
    private boolean isModified;
    private String modifierIp;
    private String modifierMacId;
    private String modiferRemarks;
    private String attestedByGuid;
    private Date attestedDate;
    private boolean isAttested;
    private String attesterIp;
    private String attesterMacId;
    private String attesterRemarks;
    private String createdUri;
    private String verifierUri;
    private String modifierUri;
    private String attesterUri;
    private String supportingUri;
}
