package com.albo.registroconsignatario.controller;

import java.time.LocalDateTime;

import com.albo.registroconsignatario.dto.ConsigneeUpload;
import com.albo.registroconsignatario.dto.ResponseMessage;
import com.albo.registroconsignatario.model.Consignee;
import com.albo.registroconsignatario.service.IConsigneeService;
import com.albo.registroconsignatario.service.IFilesStorageService;
import com.albo.registroconsignatario.util.LocalDateTimeUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/consignee")
public class ConsigneeController {

    private static final Logger LOGGER = LogManager.getLogger(ConsigneeController.class);

    @Autowired
    private IConsigneeService consigneeService;

    @Autowired
    private IFilesStorageService storageService;

    @PostMapping(value = "/registry", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consigneeRegistration(ConsigneeUpload consigneeUpload) {
        Consignee consignee = new Consignee();
        Consignee consigneeResult = new Consignee();

        consignee.setCreatedAt(LocalDateTime.now());
        consignee.setAddress(consigneeUpload.getConsignee().getAddress());
        consignee.setCellNumber(consigneeUpload.getConsignee().getCellNumber());
        consignee.setCountry(consigneeUpload.getConsignee().getCountry());
        consignee.setDocumentNumber(consigneeUpload.getConsignee().getDocumentNumber());
        consignee.setDocumentType(consigneeUpload.getConsignee().getDocumentType());
        consignee.setEmail(consigneeUpload.getConsignee().getEmail());
        consignee.setPhoneNumber(consigneeUpload.getConsignee().getPhoneNumber());

        // the name of file to be saved
        String newDocumentImgName = consignee.getDocumentNumber() + "_" 
        + LocalDateTimeUtil.localDateTimeToEpochMilliseconds(consignee.getCreatedAt());

        // save the file
        if( !this.saveDocumentImgFile(consigneeUpload.getFile(), newDocumentImgName) ) {
            String message = "Could not upload the file: " + consigneeUpload.getFile().getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

        consignee.setDocumentImg(newDocumentImgName);

        // save the new consignee
        consigneeResult = consigneeService.saveOrUpdate(consignee);

        return new ResponseEntity<Consignee>(consigneeResult, HttpStatus.CREATED);
    }

    private Boolean saveDocumentImgFile(MultipartFile file, String newFileName) {
        try {
            this.storageService.save(file, newFileName);
            LOGGER.info("Uploaded the file successfully: " + file.getOriginalFilename() + "to " + newFileName);
            return true;
            /* return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message)); */
        } catch (Exception e) {
            LOGGER.error("Could not upload the file: " + file.getOriginalFilename() + "!");
            /* return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message)); */
            return false;
        }
    }

}
