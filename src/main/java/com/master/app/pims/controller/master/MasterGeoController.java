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
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/web/master")
@Slf4j
@CrossOrigin(origins = "*")
public class MasterGeoController {

    @Autowired
    private GeoCountryMasterRepo geoCountryMasterRepo;

    @Autowired
    private DesignationAppointmentTypeRepository designationAppointmentTypeRepository;

    @Autowired
    private ValidatorImpl validatorImpl;

    @Autowired
    private GeoDistrictRepo geoDistrictRepo;

    @Autowired
    private GeoStateMasterRepository geoStateMasterRepository;

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
    @PostMapping("/submitGeoCountry")
    public BaseResponse submitCountry(@RequestBody GeoCountryMaster country, HttpServletRequest request) {
        BaseResponse resultData = null;
        System.out.println("data: " + UUID.randomUUID().toString());
        if (country != null) {
            if (country.getCountryMasterGuid() == null || country.getCountryMasterGuid().isEmpty()) {
                country.setCreaterIp(request.getRemoteAddr());
                country.setCountryMasterGuid(UUID.randomUUID().toString());
                country.setCreatedDate(new Date());
                // country.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
            } else {
                country.setModifierIp(request.getRemoteAddr());
                country.setIsModified(true);
                // country.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
                country.setModifiedDate(new Date());
            }
            if (country.getIsRecordActive() == null) country.setIsRecordActive(false);
            country.setCountryCode(!Util.isNullOrEmpty(country.getCountryCode()) ? country.getCountryCode().toUpperCase().trim() : null);
            country.setCountryNameEn(!Util.isNullOrEmpty(country.getCountryNameEn()) ? country.getCountryNameEn().toUpperCase().trim() : null);
            country.setCountryNameHi(!Util.isNullOrEmpty(country.getCountryNameHi()) ? country.getCountryNameHi().trim() : null);
            country.setCountryNameRl(!Util.isNullOrEmpty(country.getCountryNameRl()) ? country.getCountryNameRl().trim() : null);

            resultData = validatorImpl.validateMasterCountry(country);
            if (resultData != null && resultData.getStatus()) geoCountryMasterRepo.save(country);
        }
        log.info("Record SaveOrUpdate Successfully");
        return resultData;
    }

    //update data 
    @PutMapping("/{countryMasterGuid}")
    public BaseResponse updateCountry(@PathVariable String countryMasterGuid, @RequestBody GeoCountryMaster updatedCountry, HttpServletRequest request) {
        BaseResponse resultData = null;
        Optional<GeoCountryMaster> optionalCountry = geoCountryMasterRepo.findById(countryMasterGuid);

        if (optionalCountry.isPresent()) {
            GeoCountryMaster existingCountry = optionalCountry.get();

            // Update existing country fields with updatedCountry values
            existingCountry.setCountryCode(!Util.isNullOrEmpty(updatedCountry.getCountryCode()) ? updatedCountry.getCountryCode().toUpperCase().trim() : null);
            existingCountry.setCountryNameEn(!Util.isNullOrEmpty(updatedCountry.getCountryNameEn()) ? updatedCountry.getCountryNameEn().toUpperCase().trim() : null);
            existingCountry.setCountryNameHi(!Util.isNullOrEmpty(updatedCountry.getCountryNameHi()) ? updatedCountry.getCountryNameHi().trim() : null);
            existingCountry.setCountryNameRl(!Util.isNullOrEmpty(updatedCountry.getCountryNameRl()) ? updatedCountry.getCountryNameRl().trim() : null);
            existingCountry.setCountryDescription(updatedCountry.getCountryDescription());
            existingCountry.setToDate(updatedCountry.getToDate());
            existingCountry.setIsRecordActive(updatedCountry.getIsRecordActive() != null ? updatedCountry.getIsRecordActive() : existingCountry.getIsRecordActive());

            // Set modified details
            existingCountry.setModifierIp(request.getRemoteAddr());
            existingCountry.setIsModified(true);
            existingCountry.setModifiedDate(new Date());

            geoCountryMasterRepo.save(existingCountry);
            log.info("Record updated successfully");
            resultData = new BaseResponse();
            resultData.setStatus(true);
            resultData.setMessage("Record updated successfully");
        } else {
            log.error("Country not found");
            resultData = new BaseResponse();
            resultData.setStatus(false);
            resultData.setMessage("Country not found");
        }

        return resultData;
    }

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

    ////////////////////////////////////////////////DesignationAppointment Type Start///////////////////////////////////////////

    //get all data from DesignationAppointmentType according to page and size
    @GetMapping("/getDesignationAppointmentTypeList")
    public ResponseEntity<BaseResponse> getDesignationAppointmentTypeList(@RequestParam(required = true, name = "page") int page, @RequestParam(required = true, name = "size") int size, @RequestParam(defaultValue = "description", required = false) String sortBy) {
        BaseResponse response = new BaseResponse();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<DesignationAppointmentType> pager = designationAppointmentTypeRepository.findAll(pageable);
        response.setMessage("success");
        response.setStatus(true);
        response.setTotalDataCount(designationAppointmentTypeRepository.findAll().size());
        response.setDesignation(pager.toList());
        return ResponseEntity.ok(response);
    }

    //submit new data
    @PostMapping("/submitDesignationAppointmentType")
    public BaseResponse submitDesignationAppointmentType(@RequestBody DesignationAppointmentType designationAppointmentType, HttpServletRequest request) {
        BaseResponse resultData = null;
        System.out.println("data: " + UUID.randomUUID().toString());
        if (designationAppointmentType != null) {
            if (designationAppointmentType.getDesignationAppointmentTypeGuid() == null || designationAppointmentType.getDesignationAppointmentTypeGuid().isEmpty()) {
                designationAppointmentType.setCreaterIp(request.getRemoteAddr());
                designationAppointmentType.setDesignationAppointmentTypeGuid(UUID.randomUUID().toString());
                designationAppointmentType.setCreatedDate(new Date());
                // country.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
            } else {
                designationAppointmentType.setModifierIp(request.getRemoteAddr());
                designationAppointmentType.setIsModified(true);
                // country.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
                designationAppointmentType.setModifiedDate(new Date());
            }
            if (designationAppointmentType.getIsRecordActive() == null)
                designationAppointmentType.setIsRecordActive(false);

            designationAppointmentType.setDescription(!Util.isNullOrEmpty(designationAppointmentType.getDescription()) ? designationAppointmentType.getDescription().toUpperCase().trim() : null);
            designationAppointmentType.setDesignationAppointmentTypeCode(!Util.isNullOrEmpty(designationAppointmentType.getDesignationAppointmentTypeCode()) ? designationAppointmentType.getDesignationAppointmentTypeCode().toUpperCase().trim() : null);
            designationAppointmentType.setDesignationAppointmentTypeName(!Util.isNullOrEmpty(designationAppointmentType.getDesignationAppointmentTypeName()) ? designationAppointmentType.getDesignationAppointmentTypeName().toUpperCase().trim() : null);

            resultData = validatorImpl.validateDesignationAppointmentType(designationAppointmentType);
            if (resultData != null && resultData.getStatus())
                designationAppointmentTypeRepository.save(designationAppointmentType);
        }
        log.info("Record SaveOrUpdate Successfully");
        return resultData;
    }


    //update data 
    @PutMapping("/updateDesignationAppointmentTypeGuid/{designationAppointmentTypeGuid}")
    public BaseResponse updateDesignationAppointmentType(@PathVariable String designationAppointmentTypeGuid, @RequestBody DesignationAppointmentType designationAppointmentType, HttpServletRequest request) {
        BaseResponse resultData = null;
        Optional<DesignationAppointmentType> optionalDesignationAppointmentType = designationAppointmentTypeRepository.findById(designationAppointmentTypeGuid);

        if (optionalDesignationAppointmentType.isPresent()) {
            DesignationAppointmentType existingDesignationAppointmentType = optionalDesignationAppointmentType.get();

            // Update existing fields  with updated values
            existingDesignationAppointmentType.setDescription(!Util.isNullOrEmpty(designationAppointmentType.getDescription()) ? designationAppointmentType.getDescription().toUpperCase().trim() : null);
            existingDesignationAppointmentType.setDesignationAppointmentTypeCode(!Util.isNullOrEmpty(designationAppointmentType.getDesignationAppointmentTypeCode()) ? designationAppointmentType.getDesignationAppointmentTypeCode().toUpperCase().trim() : null);
            existingDesignationAppointmentType.setDesignationAppointmentTypeName(!Util.isNullOrEmpty(designationAppointmentType.getDesignationAppointmentTypeName()) ? designationAppointmentType.getDesignationAppointmentTypeName().trim() : null);
            existingDesignationAppointmentType.setToDate(designationAppointmentType.getToDate());
            existingDesignationAppointmentType.setIsRecordActive(designationAppointmentType.getIsRecordActive() != null ? designationAppointmentType.getIsRecordActive() : existingDesignationAppointmentType.getIsRecordActive());

            // Set modified details
            existingDesignationAppointmentType.setModifierIp(request.getRemoteAddr());
            existingDesignationAppointmentType.setIsModified(true);
            existingDesignationAppointmentType.setModifiedDate(new Date());

            designationAppointmentTypeRepository.save(existingDesignationAppointmentType);
            log.info("Record updated successfully");
            resultData = new BaseResponse();
            resultData.setStatus(true);
            resultData.setMessage("Record updated successfully");
        } else {
            log.error("designation Appointment Type not found");
            resultData = new BaseResponse();
            resultData.setStatus(false);
            resultData.setMessage("designation Appointment Type not found");
        }

        return resultData;
    }

    //delete data from table
    @DeleteMapping("/deleteDesignationAppointmentType/{designationAppointmentTypeGuid}")
    public ResponseEntity<Void> deleteDesignationAppointmentType(@PathVariable("designationAppointmentTypeGuid") String designationAppointmentTypeGuid) {
        DesignationAppointmentType designationAppointmentType = designationAppointmentTypeRepository.findById(designationAppointmentTypeGuid).orElseThrow(() -> new ResourceNotFoundException("Data not found with designationAppointmentTypeGuid : " + designationAppointmentTypeGuid));
        designationAppointmentTypeRepository.delete(designationAppointmentType);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getDesignationAppointmentTypeByGuid/{designationAppointmentTypeGuid}")
    public ResponseEntity<DesignationAppointmentType> getDesignationAppointmentTypeByGuid(@PathVariable("designationAppointmentTypeGuid") String designationAppointmentTypeGuid) {
        DesignationAppointmentType designation = designationAppointmentTypeRepository.findById(designationAppointmentTypeGuid).orElseThrow(() -> new ResourceNotFoundException("Data not found with designationAppointmentTypeGuid : " + designationAppointmentTypeGuid));
        return new ResponseEntity<>(designation, HttpStatus.OK);
    }

    ////////////////////////////////////////////////DesignationAppointment Type End///////////////////////////////////////////

// master state
    /*
     * @RequestMapping(value = "/MasterState", method = RequestMethod.GET) public
     * ModelAndView doShowMasterState(HttpServletRequest
     * httpServletRequest,HttpSession httpSession,HttpServletResponse
     * httpServletResponse) { ModelAndView modelAndView = new ModelAndView();
     * modelAndView.setViewName("MasterState"); return modelAndView; }
     *
     * @SuppressWarnings("unchecked")
     *
     * @RequestMapping(value = "/getStateList", method = RequestMethod.POST)
     * public @ResponseBody void getState(HttpServletRequest
     * request,HttpServletResponse httpServletResponse) { try { List<Object> list =
     * referenceDAO.getMasterEntityList("GeoState","stateNameEn");
     * List<SelectOption> countryList =referenceDAO.getCountryList();
     *
     * DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
     * DateFormat outputFormatter1 = new SimpleDateFormat("dd/MM/yyyy");
     *
     * httpServletResponse.setContentType("application/json"); JSONArray jArray=new
     * JSONArray(); JSONArray jArrayouter=new JSONArray(); for (Object obj : list){
     * Map obj1 = new LinkedHashMap(); String countryGuid=""; GeoState
     * object=(GeoState)obj;
     * obj1.put("stateMasterGuid",object.getStateMasterGuid()!=null?object.
     * getStateMasterGuid().trim(): "");
     * obj1.put("country",object.getCountryMasterGuid()!=null?referenceDAO.
     * getMasterFieldByGuid(object.getCountryMasterGuid().getCountryMasterGuid(),
     * "master.geo_country","country_name_en"):""); obj1.put("stateCode",
     * object.getStateCode()!=null?object.getStateCode().trim(): "");
     * obj1.put("stateNameEn",object.getStateNameEn()!=null?object.getStateNameEn().
     * trim(): "" );
     * obj1.put("stateNameHi",object.getStateNameHi()!=null?object.getStateNameHi().
     * trim(): "" ); obj1.put("stateNameRl",
     * object.getStateNameRl()!=null?object.getStateNameRl().trim(): "" );
     *
     * obj1.put("isRecordActive",
     * object.getIsRecordActive()!=null?object.getIsRecordActive(): "" );
     * obj1.put("stateDescription",object.getStateDescription()!=null?object.
     * getStateDescription().trim(): ""); obj1.put("fromDate",
     * object.getFromDate()!=null?outputFormatter1.format(object.getFromDate()):"");
     * obj1.put("toDate",
     * object.getToDate()!=null?outputFormatter1.format(object.getToDate()):"");
     * obj1.put("createdDate",
     * object.getCreatedDate()!=null?outputFormatter.format(object.getCreatedDate())
     * :""); obj1.put("createrIp", object.getCreaterIp()!=null?
     * object.getCreaterIp().trim(): ""); obj1.put("createrMacId",
     * object.getCreaterMacId()!=null?object.getCreaterMacId().trim(): "");
     * obj1.put("createrRemarks",object.getCreaterRemarks()!=null?object.
     * getCreaterRemarks().trim(): "" ); obj1.put("verifiedDate",
     * object.getVerifiedDate()!=null?outputFormatter.format(object.getVerifiedDate(
     * )):""); obj1.put("isVerified", object.getIsVerified());
     * obj1.put("verifierIp",object.getVerifierIp()!=null?object.getVerifierIp().
     * trim(): "" ); obj1.put("verifierMacId",
     * object.getVerifierMacId()!=null?object.getVerifierMacId().trim(): "");
     * obj1.put("verfierRemarks",
     * object.getVerfierRemarks()!=null?object.getVerfierRemarks().trim(): "");
     * obj1.put("modifiedDate",object.getModifiedDate()!=null?outputFormatter.format
     * (object.getModifiedDate()):"" ); obj1.put("isModified",
     * object.getIsModified()); obj1.put("modifierIp",
     * object.getModifierIp()!=null?object.getModifierIp().trim(): "");
     * obj1.put("modifierMacId",
     * object.getModifierMacId()!=null?object.getModifierMacId().trim(): "");
     * obj1.put("modiferRemarks", object.getModiferRemarks()!=null?
     * object.getModiferRemarks().trim(): ""); obj1.put("attestedDate",
     * object.getAttestedDate()!=null?outputFormatter.format(object.getAttestedDate(
     * )):"" ); obj1.put("isAttested", object.getIsAttested());
     * obj1.put("attesterIp", object.getAttesterIp()!=null?
     * object.getAttesterIp().trim(): ""); obj1.put("attesterMacId",
     * object.getAttesterMacId()!=null? object.getAttesterMacId().trim(): "");
     * obj1.put("createrMacId", object.getCreaterMacId()!=null?
     * object.getCreaterMacId().trim(): ""); obj1.put("countryGuid",
     * object.getCountryMasterGuid().getCountryMasterGuid()!=null?object.
     * getCountryMasterGuid().getCountryMasterGuid().trim(): "" );
     *
     * jArray.add(obj1); } if(list!=null && list.size()==0 && list.isEmpty()){ Map
     * obj1 = new LinkedHashMap(); for (int
     * i=1;i<GeoState.class.getDeclaredFields().length-7;i++){ Field field =
     * GeoState.class.getDeclaredFields()[i]; obj1.put(field.getName(), ""); }
     * jArray.add(obj1); } //jArrayouter.add(jArray);
     * //jArrayouter.add((Util.isValidJson(jArray.toString())?SecurityUtil.
     * encodeStringByBase64(Jsoup.clean(JSONObject.escape(jArray.toString()),
     * Whitelist.none())):""));
     * jArrayouter.add((Util.isValidJson(jArray.toString())?Jsoup.clean(JSONObject.
     * escape(jArray.toString()), Whitelist.none()):"")); jArrayouter.add(new
     * Gson().toJson(countryList, new TypeToken<List<SelectOption>>()
     * {}.getType())); httpServletResponse.setCharacterEncoding("UTF-8");
     * //httpServletResponse.getWriter().print(jArrayouter.toString());
     * httpServletResponse.getWriter().print(SecurityUtil.encodeStringByBase64(
     * jArrayouter.toString()));
     *
     *
     * } catch (Exception e1) {
     *
     * }
     *
     * }
     *
     *
     * @RequestMapping(value = "/submitMasterState", method = RequestMethod.POST)
     * public @ResponseBody ResultData submitMasterState(HttpServletRequest
     * request,HttpServletResponse httpServletResponse,HttpSession
     * httpSession,@RequestParam ("rowData") String rowData) { try { Gson
     * gson=GsonBuilderRegister.getGsonBuilder(); GeoState state =
     * gson.fromJson(rowData, GeoState.class); ResultData resultData = null;
     * UserSessionParam userSessionParam =
     * HttpSessionHelper.getUserSession(httpSession); if(userSessionParam!=null) {
     * if(state!=null) { if(state.getStateMasterGuid()==null ||
     * state.getStateMasterGuid().isEmpty() ) {
     * state.setStateMasterGuid(UUID.randomUUID().toString());
     * state.setCreatedDate(new Date());
     * state.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
     * state.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
     * if(state.getIsRecordActive()==null) state.setIsRecordActive(false); }else{
     * state.setModifierIp(HttpSessionHelper.getClientIPAddress(request));
     * state.setIsModified(true);
     * state.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
     * state.setModifiedDate(new Date()); if(state.getIsRecordActive()==null)
     * state.setIsRecordActive(false); } if(state.getCountry()!=null &&
     * !state.getCountry().isEmpty()){ GeoCountry geocountry=new GeoCountry();
     * geocountry.setCountryMasterGuid(state.getCountry());
     * state.setCountryMasterGuid(geocountry); }
     * state.setStateCode(!Util.isNullOrEmpty(state.getStateCode()) ?
     * state.getStateCode().toUpperCase().trim() : null);
     * state.setStateNameEn(!Util.isNullOrEmpty(state.getStateNameEn()) ?
     * state.getStateNameEn().toUpperCase().trim() : null);
     * state.setStateNameHi(!Util.isNullOrEmpty(state.getStateNameHi()) ?
     * state.getStateNameHi().trim() : null);
     * state.setStateNameRl(!Util.isNullOrEmpty(state.getStateNameRl()) ?
     * state.getStateNameRl().trim() : null); resultData = validateState(state);
     * if(resultData!=null && resultData.getStatus())
     * referenceDAO.saveorUpdateMasterObj(state); } } return resultData; } catch
     * (Exception e1) {
     *
     * } return null; }
     *
     *
     * private ResultData validateState(GeoState state) { ResultData resultData =
     * new ResultData(); resultData.setStatus(true);
     * resultData.setMessage("Record SaveOrUpdate Successfully"); try{
     * if(Util.isNullOrEmpty(state.getStateCode())) { resultData.setStatus(false);
     * resultData.setMessage(PropertyReader.getFormMessage(
     * "master.state.state.code.required"));
     * }if(!Util.isNullOrEmpty(state.getStateCode()) &&
     * masterValidationDAO.isExistStateCode(state.getStateCode(),state.
     * getStateMasterGuid()) ){ resultData.setStatus(false);
     * resultData.setMessage(PropertyReader.getFormMessage(
     * "master.state.code.unique")); return resultData; }
     * if(!Util.isNullOrEmpty(state.getStateNameEn()) &&
     * masterValidationDAO.isExistStateNameEn(state)){ resultData.setStatus(false);
     * resultData.setMessage(PropertyReader.getFormMessage(
     * "master.state.nameEn.unique")); return resultData; }
     * if(!Util.isNullOrEmpty(state.getStateNameHi()) &&
     * masterValidationDAO.isExistStateNameHi(state)){ resultData.setStatus(false);
     * resultData.setMessage(PropertyReader.getFormMessage(
     * "master.state.nameHi.unique")); return resultData; }
     * if(!Util.isNullOrEmpty(state.getStateNameRl()) &&
     * masterValidationDAO.isExistStateNameRl(state)){ resultData.setStatus(false);
     * resultData.setMessage(PropertyReader.getFormMessage(
     * "master.state.nameRl.unique")); return resultData; }
     *
     * } catch(Exception e){
     *
     * } return resultData; }
     *
     * @RequestMapping(value = "/deleteMasterState", method = RequestMethod.POST)
     * public @ResponseBody void deleteState(HttpServletRequest
     * request,HttpServletResponse httpServletResponse,HttpSession
     * httpSession,@RequestParam ("rowData") String rowData) { try { Gson
     * gson=GsonBuilderRegister.getGsonBuilder(); GeoState state =
     * gson.fromJson(rowData, GeoState.class); UserSessionParam userSessionParam =
     * HttpSessionHelper.getUserSession(httpSession); if(userSessionParam!=null) {
     * if(state.getCountryGuid()!=null && !state.getCountryGuid().isEmpty()){
     * GeoCountry geocountry=new GeoCountry();
     * geocountry.setCountryMasterGuid(state.getCountryGuid());
     * state.setCountryMasterGuid(geocountry); }
     * referenceDAO.deleteMasterObj(state);
     *
     * } } catch (Exception e1) {
     *
     * } }
     */

    ////////////////////////////////////////////////State  End///////////////////////////////////////////

//    // district master geo
//    @GetMapping("/getDistrictList")
//    public ResponseEntity<BaseResponse> getDistrict(@RequestParam(required = true, name = "page") int page,
//                                                    @RequestParam(required = true, name = "size") int size,
//                                                    @RequestParam(defaultValue = "districtNameEn", required = false) String sortBy) {
//        BaseResponse response = new BaseResponse();
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<GeoDistrict> districtPage = geoDistrictRepo.findAll(pageable);
//        response.setMessage("success");
//        response.setStatus(true);
//        response.setTotalDataCount(geoDistrictRepo.findAll().size());
//        response.setDistrictList(districtPage.toList());
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/submitMasterDistrict")
//    public BaseResponse submitMasterDistrict(@RequestBody GeoDistrict district, HttpServletRequest request) {
//        BaseResponse resultData = null;
//
//        if (district.getDistrictMasterGuid() == null || district.getDistrictMasterGuid().isEmpty()) {
//            district.setDistrictMasterGuid(UUID.randomUUID().toString());
//            district.setCreatedDate(new Date());
//            // district.setCreatedByGuid(userSessionParam.getEmpBasicGUID());
//            // district.setCreaterIp(HttpSessionHelper.getClientIPAddress(request));
//        } else {
//            //district.setModifierIp(HttpSessionHelper.getClientIPAddress(request));
//            district.setIsModified(true);
//            // district.setModifiedByGuid(userSessionParam.getEmpBasicGUID());
//            district.setModifiedDate(new Date());
//        }
//        if (district.getIsRecordActive() == null)
//            district.setIsRecordActive(false);
//        if (district.getState() != null && !district.getState().isEmpty()) {
//            GeoState geostate = new GeoState();
//            geostate.setStateMasterGuid(district.getState());
//            district.setGeoStateMasterGuid(geostate);
//        }
//        district.setDistrictCode(!Util.isNullOrEmpty(district.getDistrictCode())
//                ? district.getDistrictCode().toUpperCase().trim()
//                : null);
//        district.setDistrictNameEn(!Util.isNullOrEmpty(district.getDistrictNameEn())
//                ? district.getDistrictNameEn().toUpperCase().trim()
//                : null);
//        district.setDistrictNameHi(
//                !Util.isNullOrEmpty(district.getDistrictNameHi()) ? district.getDistrictNameHi().trim()
//                        : null);
//        district.setDistrictNameRl(
//                !Util.isNullOrEmpty(district.getDistrictNameRl()) ? district.getDistrictNameRl().trim()
//                        : null);
//        resultData = validatorImpl.validateDistrict(district);
//        if (resultData != null && resultData.getStatus())
//            geoDistrictRepo.save(district);
//        log.info("Record SaveOrUpdate Successfully");
//        return resultData;
//    }

}
