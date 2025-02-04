package com.master.app.pims.service.master;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.repositories.citizen.AdminDetailRepo;

@Service
public class CommonMasterServiceHandler {
	@Autowired
	private AdminDetailRepo adminDetailRepo;
	
	  // Save image to database as binary
//    public AdminDetail saveImage(MultipartFile file) throws IOException {
//    	AdminDetail image = new AdminDetail();
//        image.setUserImage(file.getOriginalFilename());
//        image.setData(file.getBytes());  // Store image in binary format
//        return adminDetailRepo.save(image);
 //   }

//    // Fetch image from database by ID
    public Optional<AdminDetail> getUserImage(String id) {
        return adminDetailRepo.findById(id);
    }
}
