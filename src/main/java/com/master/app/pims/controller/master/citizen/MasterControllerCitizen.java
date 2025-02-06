package com.master.app.pims.controller.master.citizen;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.exceptions.ResourceNotFoundException;
import com.master.app.pims.models.common.response.BaseResponse;
import com.master.app.pims.repositories.citizen.AdminDetailRepo;
import com.master.app.pims.repositories.citizen.FooterRibbonRepo;
import com.master.app.pims.service.master.CommonMasterServiceHandler;
import com.master.app.pims.service.master.common.CommonMasterService;
import com.master.app.pims.utils.Util;
import com.master.app.pims.validators.Validator;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/web/master")
@CrossOrigin(origins = "http://localhost:3000")
public class MasterControllerCitizen {
	@Autowired
	private Validator validator;

	@Autowired
	private CommonMasterService commonMasterService;

	@Autowired
	private CommonMasterServiceHandler serviceHandler;

	@Autowired
	private AdminDetailRepo adminDetailRepo;

	@Autowired
	private FooterRibbonRepo footerRibbonRepo;

/////////////////////////////////////AdminDetail Start///////////////////////////////////
//get all data from table
	@GetMapping("/getAdminDetailList")
	public ResponseEntity<BaseResponse> getAdminDetailList() {
		BaseResponse response = new BaseResponse();
// Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		List<AdminDetail> list = adminDetailRepo.findAll();
		response.setMessage("success");
		response.setStatus(true);
		response.setTotalDataCount(list.size());
		response.setAdminDetail(list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/submitAdminDetail")
	public BaseResponse submitAdminDetail(@RequestBody AdminDetail adminDetail, HttpServletRequest request) {
		BaseResponse resultData = new BaseResponse();

		try {
			// Check if GUID is provided (indicating an update)
			if (adminDetail.getAdminDetailGuid() == null || adminDetail.getAdminDetailGuid().isEmpty()) {
				// Add new data
				adminDetail.setCreatedIpAddr(request.getRemoteAddr());
				adminDetail.setAdminDetailGuid(UUID.randomUUID().toString());
				adminDetail.setCreatedDate(new Date());
				adminDetail.setModifiedIpAddr(null);
				adminDetail.setModifiedBy(null);
				adminDetail.setModifiedDate(null);
				adminDetail.setCreatedBy(request.getRemoteAddr());

				if (adminDetail.getIsActive() == null)
					adminDetail.setIsActive(false);

				// Image upload code for creating new record
				if (adminDetail.getUserImage1Base64() != null && !adminDetail.getUserImage1Base64().isEmpty()) {
					try {
						// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
						String base64String = adminDetail.getUserImage1Base64();
						if (base64String.contains("base64,")) {
							base64String = base64String.split("base64,")[1]; // Remove the prefix
						}

						// Decode the base64 image string
						byte[] imageBytes = Base64.getDecoder().decode(base64String);
						adminDetail.setUserImage1(imageBytes); // Set image bytes to entity
					} catch (IllegalArgumentException e) {
						log.error("Invalid Base64 image data.", e);
						resultData.setStatus(false);
						resultData.setMessage("Invalid Base64 image data.");
						return resultData;
					}
				}

			} else {
				// Update existing data
				AdminDetail existingAdminDetail = commonMasterService
						.getAdminDetailById(adminDetail.getAdminDetailGuid());

				if (existingAdminDetail != null) {
					existingAdminDetail.setUserName(!Util.isNullOrEmpty(adminDetail.getUserName())
							? adminDetail.getUserName().toUpperCase().trim()
							: null);
					existingAdminDetail.setDepartmentName(!Util.isNullOrEmpty(adminDetail.getDepartmentName())
							? adminDetail.getDepartmentName().toUpperCase().trim()
							: null);
					existingAdminDetail.setEncryptedPwd(!Util.isNullOrEmpty(adminDetail.getEncryptedPwd())
							? adminDetail.getEncryptedPwd().toUpperCase().trim()
							: null);
					existingAdminDetail.setIpAddress(
							!Util.isNullOrEmpty(adminDetail.getIpAddress()) ? adminDetail.getIpAddress().trim() : null);
					existingAdminDetail.setActiveFromDate(adminDetail.getActiveFromDate());
					existingAdminDetail.setActiveTill(adminDetail.getActiveTill());
					existingAdminDetail.setIsActive(adminDetail.getIsActive() != null ? adminDetail.getIsActive()
							: existingAdminDetail.getIsActive());

					if (existingAdminDetail.getIsActive() == null)
						existingAdminDetail.setIsActive(false);

					// Handle image update for existing record
					if (adminDetail.getUserImage1Base64() != null && !adminDetail.getUserImage1Base64().isEmpty()) {
						try {
							// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
							String base64String = adminDetail.getUserImage1Base64();
							if (base64String.contains("base64,")) {
								base64String = base64String.split("base64,")[1]; // Remove the prefix
							}

							// Decode the base64 image string
							byte[] imageBytes = Base64.getDecoder().decode(base64String);
							existingAdminDetail.setUserImage1(imageBytes); // Update the image bytes in existing object
						} catch (IllegalArgumentException e) {
							log.error("Invalid Base64 image data.", e);
							resultData.setStatus(false);
							resultData.setMessage("Invalid Base64 image data.");
							return resultData;
						}
					} else if (adminDetail.getUserImage1() != null && adminDetail.getUserImage1().length > 0) {
						existingAdminDetail.setUserImage1(adminDetail.getUserImage1());
					}

					// Update modification details
					existingAdminDetail.setModifiedIpAddr(request.getRemoteAddr());
					existingAdminDetail.setModifiedDate(new Date());
					existingAdminDetail.setModifiedBy(UUID.randomUUID().toString());
					existingAdminDetail.setModifiedMacAddr(UUID.randomUUID().toString());
					adminDetail = existingAdminDetail; // Use the updated existing menu master object
				} else {
					log.error("AdminDetail Master not found");
					resultData.setStatus(false);
					resultData.setMessage("AdminDetail Master not found");
					return resultData;
				}
			}

			// Validation
			resultData = validator.validateAdminDetail(adminDetail);
			if (resultData != null && !resultData.getStatus()) {
				log.error("Validation failed: {}", resultData.getMessage());
				return resultData;
			}

			// Set some default values for empty fields
			if (adminDetail.getIsActive() == null)
				adminDetail.setIsActive(false);
			adminDetail.setUserName(
					!Util.isNullOrEmpty(adminDetail.getUserName()) ? adminDetail.getUserName().toUpperCase().trim()
							: null);
			adminDetail.setDepartmentName(!Util.isNullOrEmpty(adminDetail.getDepartmentName())
					? adminDetail.getDepartmentName().toUpperCase().trim()
					: null);
			adminDetail.setEncryptedPwd(
					!Util.isNullOrEmpty(adminDetail.getEncryptedPwd()) ? adminDetail.getEncryptedPwd().trim() : null);
			adminDetail.setIpAddress(
					!Util.isNullOrEmpty(adminDetail.getIpAddress()) ? adminDetail.getIpAddress().trim() : null);
			adminDetail.setActiveFromDate(adminDetail.getActiveFromDate());
			adminDetail.setActiveTill(adminDetail.getActiveTill());

			// Save or update the data
			try {
				adminDetailRepo.save(adminDetail);
				log.info("Record SaveOrUpdate Successfully");
				resultData.setStatus(true);
				resultData.setMessage("Record saved or updated successfully");
			} catch (Exception e) {
				log.error("Error saving or updating record: {}", e.getMessage(), e);
				resultData.setStatus(false);
				resultData.setMessage("Error saving or updating record: " + e.getMessage());
			}

		} catch (Exception e) {
			log.error("Unexpected error occurred: {}", e.getMessage(), e);
			resultData.setStatus(false);
			resultData.setMessage("Unexpected error occurred: " + e.getMessage());
		}

		return resultData;
	}

//get data by id
	@GetMapping("/getAdminDetailByGuid/{adminDetailGuid}")
	public ResponseEntity<AdminDetail> getAdminDetailByGuid(@PathVariable("adminDetailGuid") String adminDetailGuid) {
		AdminDetail adminDetail = adminDetailRepo.findById(adminDetailGuid).orElseThrow(
				() -> new ResourceNotFoundException("Resource not found with menuMasterGuid : " + adminDetailGuid));
		return new ResponseEntity<>(adminDetail, HttpStatus.OK);
	}

/////////////////////////////////////AdminDetail  End///////////////////////////////////
	
	
/////////////////////////////////////FooterRibbon Start///////////////////////////////////
//get all data from table
@GetMapping("/getFooterRibbonList")
public ResponseEntity<BaseResponse> getFooterRibbonList() {
BaseResponse response = new BaseResponse();
//Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
List<FooterRibbon> list = footerRibbonRepo.findAll();
response.setMessage("success");
response.setStatus(true);
response.setTotalDataCount(list.size());
response.setFooterRibbon(list);
return ResponseEntity.ok(response);
}

@PostMapping("/submitFooterRibbon")
public BaseResponse submitFooterRibbon(@RequestBody FooterRibbon footerRibbon, HttpServletRequest request) {
BaseResponse resultData = new BaseResponse();
try {
// Check if GUID is provided (indicating an update)
if (footerRibbon.getFooterRibbonGuid() == null || footerRibbon.getFooterRibbonGuid().isEmpty()) {
// Add new data
	footerRibbon.setCreatedIpAddr(request.getRemoteAddr());
	footerRibbon.setFooterRibbonGuid(UUID.randomUUID().toString());
	footerRibbon.setCreatedDate(new Date());
	footerRibbon.setModifiedIpAddr(null);
	footerRibbon.setModifiedBy(null);
	footerRibbon.setModifiedDate(null);
footerRibbon.setCreatedBy(request.getRemoteAddr());

if (footerRibbon.getIsActive() == null)
	footerRibbon.setIsActive(false);

// Image upload code for creating new record
if (footerRibbon.getUserImage1Base64() != null && !footerRibbon.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = footerRibbon.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
footerRibbon.setImageBinary1(imageBytes); // Set image bytes to entity
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
}

} else {
// Update existing data
FooterRibbon existingFooterRibbon = commonMasterService.getFooterRibbonById(footerRibbon.getFooterRibbonGuid());

if (existingFooterRibbon != null) {
	existingFooterRibbon.setImageHeadingEn(!Util.isNullOrEmpty(footerRibbon.getImageHeadingEn())? footerRibbon.getImageHeadingEn().toUpperCase().trim(): null);
	
existingFooterRibbon.setImageHeadingHi(!Util.isNullOrEmpty(footerRibbon.getImageHeadingHi())? footerRibbon.getImageHeadingHi().toUpperCase().trim(): null);
	
existingFooterRibbon.setImageHeadingRl(!Util.isNullOrEmpty(footerRibbon.getImageHeadingRl())? footerRibbon.getImageHeadingRl().toUpperCase().trim(): null);
	
existingFooterRibbon.setFooterImageUrl(!Util.isNullOrEmpty(footerRibbon.getFooterImageUrl()) ? footerRibbon.getFooterImageUrl().trim() : null);
	
existingFooterRibbon.setOrderNumber(!Util.isNullOrZero(footerRibbon.getOrderNumber()) ? footerRibbon.getOrderNumber() : null);

existingFooterRibbon.setFooterContent(!Util.isNullOrEmpty(footerRibbon.getFooterContent())? footerRibbon.getFooterContent().toUpperCase().trim(): null);


existingFooterRibbon.setIsActive(footerRibbon.getIsActive() != null ? footerRibbon.getIsActive(): existingFooterRibbon.getIsActive());



if (existingFooterRibbon.getIsActive() == null)
	existingFooterRibbon.setIsActive(false);

// Handle image update for existing record
if (footerRibbon.getUserImage1Base64() != null && !footerRibbon.getUserImage1Base64().isEmpty()) {
try {
// Clean the base64 string by removing the prefix (e.g., "image/jpeg;base64,")
String base64String = footerRibbon.getUserImage1Base64();
if (base64String.contains("base64,")) {
base64String = base64String.split("base64,")[1]; // Remove the prefix
}

// Decode the base64 image string
byte[] imageBytes = Base64.getDecoder().decode(base64String);
existingFooterRibbon.setImageBinary1(imageBytes); // Update the image bytes in existing object
} catch (IllegalArgumentException e) {
log.error("Invalid Base64 image data.", e);
resultData.setStatus(false);
resultData.setMessage("Invalid Base64 image data.");
return resultData;
}
} else if (footerRibbon.getImageBinary1() != null && footerRibbon.getImageBinary1().length > 0)
{
	existingFooterRibbon.setImageBinary1(footerRibbon.getImageBinary1());
}

// Update modification details
existingFooterRibbon.setModifiedIpAddr(request.getRemoteAddr());
existingFooterRibbon.setModifiedDate(new Date());
existingFooterRibbon.setModifiedBy(UUID.randomUUID().toString());
existingFooterRibbon.setModifiedMacAddr(UUID.randomUUID().toString());
footerRibbon = existingFooterRibbon; // Use the updated existing menu master object
}
else
{
log.error("FooterRibbon Master not found");
resultData.setStatus(false);
resultData.setMessage("FooterRibbon Master not found");
return resultData;
}
}

// Validation
resultData = validator.validateFooterRibbon(footerRibbon);
if (resultData != null && !resultData.getStatus()) {
log.error("Validation failed: {}", resultData.getMessage());
return resultData;
}

// Set some default values for empty fields
if (footerRibbon.getIsActive() == null)
	footerRibbon.setIsActive(false);

footerRibbon.setImageHeadingEn(!Util.isNullOrEmpty(footerRibbon.getImageHeadingEn()) ? footerRibbon.getImageHeadingEn().toUpperCase().trim(): null);

footerRibbon.setImageHeadingHi(!Util.isNullOrEmpty(footerRibbon.getImageHeadingHi())? footerRibbon.getImageHeadingHi().toUpperCase().trim(): null);

footerRibbon.setImageHeadingRl(!Util.isNullOrEmpty(footerRibbon.getImageHeadingRl()) ? footerRibbon.getImageHeadingRl().trim() : null);

footerRibbon.setFooterImageUrl(!Util.isNullOrEmpty(footerRibbon.getFooterImageUrl()) ? footerRibbon.getFooterImageUrl().trim() : null);

footerRibbon.setOrderNumber(!Util.isNullOrZero(footerRibbon.getOrderNumber()) ? footerRibbon.getOrderNumber() : null);

footerRibbon.setFooterContent(!Util.isNullOrEmpty(footerRibbon.getFooterContent()) ? footerRibbon.getFooterContent().trim() : null);

// Save or update the data
try {
	footerRibbonRepo.save(footerRibbon);
log.info("Record SaveOrUpdate Successfully");
resultData.setStatus(true);
resultData.setMessage("Record saved or updated successfully");
} catch (Exception e) {
log.error("Error saving or updating record: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Error saving or updating record: " + e.getMessage());
}

} catch (Exception e) {
log.error("Unexpected error occurred: {}", e.getMessage(), e);
resultData.setStatus(false);
resultData.setMessage("Unexpected error occurred: " + e.getMessage());
}

return resultData;
}

//get data by id
@GetMapping("/getFooterRibbonByGuid/{footerRibbonGuid}")
public ResponseEntity<FooterRibbon> getFooterRibbonByGuid(@PathVariable("footerRibbonGuid") String footerRibbonGuid) {
	FooterRibbon footerRibbon = footerRibbonRepo.findById(footerRibbonGuid).orElseThrow(
() -> new ResourceNotFoundException("Resource not found with footerRibbonGuid : " + footerRibbonGuid));
return new ResponseEntity<>(footerRibbon, HttpStatus.OK);
}

/////////////////////////////////////FooterRibbon  End///////////////////////////////////

//Function to  convert image in Base64
	public String convertImageToBase64(byte[] imageBytes) {
		return Base64.getEncoder().encodeToString(imageBytes);
	}

}
