package com.master.app.pims;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BaseResponse {
    private boolean status;
    private String message;

    public boolean isStatus() {
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

    private ResponseEntity<List<GeoCountryMaster>> data;

    public ResponseEntity<List<GeoCountryMaster>> getData() {
        return data;
    }

    public void setData(ResponseEntity<List<GeoCountryMaster>> data) {
        this.data = data;
    }
}
