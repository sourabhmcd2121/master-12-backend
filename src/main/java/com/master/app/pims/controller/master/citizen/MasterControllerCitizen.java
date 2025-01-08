package com.master.app.pims.controller.master.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.controller.master.common.MasterControllerMCDCommon;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.validators.Validator;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3001")
public class MasterControllerCitizen {
	
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;

}
