package com.master.app.pims.controller.master.hospital;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.controller.master.citizen.MasterControllerCitizen;
import com.master.app.pims.controller.master.rbd.RbdMasterController;
import com.master.app.pims.entities.schemas.hospital.HospitalInfo;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.rbd.RbdMstCommonList;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.hospital.HospitalInfoRepo;
import com.master.app.pims.repositories.rbd.RbdMstDocsCategoryRepo;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalMasterController {
private Logger logger = LoggerFactory.getLogger(MasterControllerCitizen.class);
	
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;
	
	  @Autowired
	   	private HospitalInfoRepo hospitalInfoRepo;
	
	  ///////////////////////////////HospitalInfo Start///////////////////////////////////////
		//get all data from table
		  @GetMapping("/getHospitalInfoList")
		  public ResponseEntity<BaseResponse> getHospitalInfoList() {
		  BaseResponse response = new BaseResponse();
		  //Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		  List<HospitalInfo> list = hospitalInfoRepo.findAll();
		  response.setMessage("success");
		  response.setStatus(true);
		  response.setTotalDataCount(list.size());
		  response.setHospitalInfo(list);
		  return ResponseEntity.ok(response);
		  }

		 
		  
		  ///////////////////////////////HospitalInfo End///////////////////////////////////////
}
