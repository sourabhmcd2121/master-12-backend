package com.master.app.pims.models.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoDistrict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    boolean status;
    String message;
    private List<GeoCountryMaster> data;
    private List<DesignationAppointmentType> designation;
    private Integer totalDataCount;
    List<GeoDistrict> districtList;

    public List<GeoDistrict> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<GeoDistrict> districtList) {
        this.districtList = districtList;
    }

    public List<DesignationAppointmentType> getDesignation() {
        return designation;
    }

    public void setDesignation(List<DesignationAppointmentType> designation) {
        this.designation = designation;
    }

    public boolean isStatus() {
        return status;
    }

    public List<GeoCountryMaster> getData() {
        return data;
    }

    public void setData(List<GeoCountryMaster> data) {
        this.data = data;
    }

    public Integer getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(Integer totalDataCount) {
        this.totalDataCount = totalDataCount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
