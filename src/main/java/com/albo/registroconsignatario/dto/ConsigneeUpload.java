package com.albo.registroconsignatario.dto;

import com.albo.registroconsignatario.model.Consignee;

import org.springframework.web.multipart.MultipartFile;

public class ConsigneeUpload {
  
    private Consignee consignee;
	private MultipartFile file;

    public Consignee getConsignee() {
        return consignee;
    }
    public void setConsignee(Consignee consignee) {
        this.consignee = consignee;
    }
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
