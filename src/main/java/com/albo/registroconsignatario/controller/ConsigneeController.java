package com.albo.registroconsignatario.controller;

import java.time.LocalDateTime;

import com.albo.registroconsignatario.dto.ConsigneeUpload;
import com.albo.registroconsignatario.model.Consignee;
import com.albo.registroconsignatario.service.IConsigneeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consignee")
public class ConsigneeController {

    @Autowired
    private IConsigneeService consigneeService;

    @PostMapping(value = "/registry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> consigneeRegistration(@RequestBody ConsigneeUpload consigneeUpload) {
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


        //consignee.setDocumentImg();

		consigneeResult = consigneeService.saveOrUpdate(consignee);

		return new ResponseEntity<Consignee>(consigneeResult, HttpStatus.CREATED);
	}    
}
