package com.albo.registroconsignatario.controller;

import java.time.LocalDateTime;

import com.albo.registroconsignatario.dto.ResponseMessage;
import com.albo.registroconsignatario.exception.ModelAlreadyExistsException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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

    @PostMapping(value = "/registry", 
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> consigneeRegistration(
            @RequestPart(value = "consignee", required = true) Consignee consignee,
            @RequestPart(value = "file", required = true) MultipartFile file
        ) {

        // check if the document number already exists
        if( consigneeService.findByDocumentNumber(consignee.getDocumentNumber()).isPresent() ) {
            throw new ModelAlreadyExistsException("El número de documento ya se encuentra registrado");
        }

        consignee.setCreatedAt(LocalDateTime.now());

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        // the name of file to be saved
        String newDocumentImgName = consignee.getDocumentNumber() + "_" 
        + LocalDateTimeUtil.localDateTimeToEpochMilliseconds(consignee.getCreatedAt())
        + "." + extension;


        // save the file
        if( !this.saveDocumentImgFile(file, newDocumentImgName) ) {
            String message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

        consignee.setDocumentImg(newDocumentImgName);

        // save the new consignee
        Consignee consigneeResult = consigneeService.saveOrUpdate(consignee);

        return new ResponseEntity<Object>(consigneeResult, HttpStatus.CREATED);
    }

    private Boolean saveDocumentImgFile(MultipartFile file, String newFileName) {
        try {
            this.storageService.save(file, newFileName);
            LOGGER.info("Uploaded the file successfully: " + file.getOriginalFilename() + " to " + newFileName);
            return true;
            /* return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message)); */
        } catch (Exception e) {
            LOGGER.error("Could not upload the file: " + file.getOriginalFilename() + "!");
            /* return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message)); */
            return false;
        }
    }

}
