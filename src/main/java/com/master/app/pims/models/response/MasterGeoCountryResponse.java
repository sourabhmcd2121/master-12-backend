package com.master.app.pims.models.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.models.common.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "masterGeoCountryList","countryNameList"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterGeoCountryResponse extends BaseResponse {
    @JsonIgnore
    private List<GeoCountryMaster> geoCountryList;
    private List<String> countryNameList;
}
