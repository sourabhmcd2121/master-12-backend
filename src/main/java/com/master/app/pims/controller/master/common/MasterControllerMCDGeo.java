package com.master.app.pims.controller.master.common;

import com.master.app.pims.service.master.common.CommonMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/common/master")
public class MasterControllerMCDGeo {

    @Autowired
    private CommonMasterService commonMasterService;



}
