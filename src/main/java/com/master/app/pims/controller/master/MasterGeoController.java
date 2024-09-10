package com.master.app.pims.controller.master;

import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoDistrict;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.models.response.MasterGeoCountryResponse;
import com.master.app.pims.repositories.DesignationAppointmentTypeRepository;
import com.master.app.pims.repositories.master.GeoCountryMasterRepo;
import com.master.app.pims.repositories.master.GeoDistrictRepo;
import com.master.app.pims.repositories.master.GeoStateMasterRepository;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/web/master")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class MasterGeoController {

    @Autowired
    private GeoCountryMasterRepo geoCountryMasterRepo;

    @Autowired
    private DesignationAppointmentTypeRepository designationAppointmentTypeRepository;

    @Autowired
    private ValidatorImpl validatorImpl;

    @Autowired
    private GeoDistrictRepo geoDistrictRepo;

//    @Autowired
//    private GeoStateMasterRepository geoStateMasterRepository;

// master schema
//    //get all data from country according to page and size
//    @GetMapping("/getMasterGeoCountryList")
//    public ResponseEntity<BaseResponse> getMasterGeoCountryList(@RequestParam(required = true, name = "page") int page, @RequestParam(required = true, name = "size") int size, @RequestParam(defaultValue = "countryNameEn", required = false) String sortBy) {
//        BaseResponse response = new BaseResponse();
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<GeoCountryMaster> countryPage = masterGeoCountryRepository.findAll(pageable);
//        response.setMessage("success");
//        response.setStatus(true);
//        response.setTotalDataCount(masterGeoCountryRepository.findAll().size());
//        response.setData(countryPage.toList());
//        return ResponseEntity.ok(response);
//    }
    
    //get all data from table
    @GetMapping("/getAllGeoCountryList")
    public ResponseEntity<BaseResponse> getAllGeoCountryList() {
        BaseResponse response = new BaseResponse();
        // Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<GeoCountryMaster> list = geoCountryMasterRepo.findAll();
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(list.size());
        response.setMasterCountry(list);
        return ResponseEntity.ok(response);
    }


    // fetch data from another table in dropdown  in column and save data in cache 
    @GetMapping("/getCountryNameList")
    public ResponseEntity<MasterGeoCountryResponse> getCountryList() {
        MasterGeoCountryResponse response = new MasterGeoCountryResponse();
        try {
            Map<String, String> countryMap = geoCountryMasterRepo.getGeoMasterCountryList();
            if (countryMap.isEmpty()) {
                throw new ResourceNotFoundException("No countries found");
            }
            response.setMessage("success");
            response.setStatus(true);
            response.setCountryNameList(countryMap);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException ex) {
            response.setMessage(ex.getMessage());
            response.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception ex) {
            response.setMessage(ex.getMessage());
            response.setStatus(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    //submit new data
//    @PostMapping("/submitGeoCountry")
//    public BaseResponse submitCountry(@RequestBody GeoCountryMaster country, HttpServletRequest request) {
//        BaseResponse resultData = null;
//        System.out.println("data: " + UUID.randomUUID().toString());
//        if (country != null) {
//            if (country.getCountryMasterGuid() == null || country.getCountryMasterGuid().isEmpty()) {
//                country.setCreaterIp(request.getRemoteAddr());
//                country.setCountryMasterGuid(UUID.randomUUID().toString());
//                country.setCreatedDate(new Date());
//                // country.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//            } else {
//                country.setModifierIp(request.getRemoteAddr());
//                country.setIsModified(true);
//                // country.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
//                country.setModifiedDate(new Date());
//            }
//            if (country.getIsRecordActive() == null) country.setIsRecordActive(false);
//            country.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);
//            country.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
//            country.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
//            country.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);
//
//            resultData = validatorImpl.validateMasterCountry(country);
//            if (resultData != null && resultData.getStatus()) geoCountryMasterRepo.save(country);
//        }
//        log.info("Record SaveOrUpdate Successfully");
//        return resultData;
//    }

    //update data 
//    @PutMapping("/{countryMasterGuid}")
//    public BaseResponse updateCountry(@PathVariable String countryMasterGuid, @RequestBody GeoCountryMaster updatedCountry, HttpServletRequest request) {
//        BaseResponse resultData = null;
//        Optional<GeoCountryMaster> optionalCountry = geoCountryMasterRepo.findById(countryMasterGuid);
//
//        if (optionalCountry.isPresent()) {
//            GeoCountryMaster existingCountry = optionalCountry.get();
//
//            // Update existing country fields with updatedCountry values
//            existingCountry.setCountryCode(!Util.isNullOrEmpty(updatedCountry.getCountryCode()) ? updatedCountry.getCountryCode().toUpperCase().trim() : null);
//            existingCountry.setCountryNameEn(!Util.isNullOrEmpty(updatedCountry.getCountryNameEn()) ? updatedCountry.getCountryNameEn().toUpperCase().trim() : null);
//            existingCountry.setCountryNameHi(!Util.isNullOrEmpty(updatedCountry.getCountryNameHi()) ? updatedCountry.getCountryNameHi().trim() : null);
//            existingCountry.setCountryNameRl(!Util.isNullOrEmpty(updatedCountry.getCountryNameRl()) ? updatedCountry.getCountryNameRl().trim() : null);
//            existingCountry.setCountryDescription(updatedCountry.getCountryDescription());
//            existingCountry.setToDate(updatedCountry.getToDate());
//            existingCountry.setIsRecordActive(updatedCountry.getIsRecordActive() != null ? updatedCountry.getIsRecordActive() : existingCountry.getIsRecordActive());
//
//            // Set modified details
//            existingCountry.setModifierIp(request.getRemoteAddr());
//            existingCountry.setIsModified(true);
//            existingCountry.setModifiedDate(new Date());
//
//            geoCountryMasterRepo.save(existingCountry);
//            log.info("Record updated successfully");
//            resultData = new BaseResponse();
//            resultData.setStatus(true);
//            resultData.setMessage("Record updated successfully");
//        } else {
//            log.error("Country not found");
//            resultData = new BaseResponse();
//            resultData.setStatus(false);
//            resultData.setMessage("Country not found");
//        }
//
//        return resultData;
//    }

    //delete data from country
    @DeleteMapping("/deleteMasterGeoCountry/{countryMasterGuid}")
    public ResponseEntity<Void> deleteMasterGeoCountry(@PathVariable("countryMasterGuid") String countryMasterGuid) {
        GeoCountryMaster geoCountry = geoCountryMasterRepo.findById(countryMasterGuid).orElseThrow(() -> new ResourceNotFoundException("Data not found with countryMasterGuid : " + countryMasterGuid));
        geoCountryMasterRepo.delete(geoCountry);
        return ResponseEntity.noContent().build();
    }

    //get all data 
    @GetMapping("/getMasterGeoCountryByGuid/{countryMasterGuid}")
    public ResponseEntity<GeoCountryMaster> getMasterGeoCountryByGuid(@PathVariable("countryMasterGuid") String countryMasterGuid) {
        GeoCountryMaster geoCountry = geoCountryMasterRepo.findById(countryMasterGuid).orElseThrow(() -> new ResourceNotFoundException("Data not found with countryMasterGuid : " + countryMasterGuid));
        return new ResponseEntity<>(geoCountry, HttpStatus.OK);
    }

 

}
