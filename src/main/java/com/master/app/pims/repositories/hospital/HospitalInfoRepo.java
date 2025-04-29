package com.master.app.pims.repositories.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.hospital.HospitalInfo;

public interface HospitalInfoRepo  extends JpaRepository<HospitalInfo, String>{

}
