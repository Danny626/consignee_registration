package com.albo.registroconsignatario.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "consignee")
public class Consignee implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;

    @Column(name = "document_number", nullable = false, unique = true)
	private String documentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type", nullable = false, referencedColumnName = "id")
	private DocumentType documentType;

    @Column(name = "email", nullable = true)
	private String email;

    @Column(name = "phone_number", nullable = true)
	private String phoneNumber;

    @Column(name = "cell_number", nullable = true)
	private String cellNumber;

    @Column(name = "document_img", nullable = true)
	private String documentImg;

    @ManyToOne
	@JoinColumn(name = "country", nullable = true, referencedColumnName = "id")
	private Country country;

    @Column(name = "address", nullable = true)
	private String address;

    @JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "created_at")
	private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getDocumentImg() {
        return documentImg;
    }

    public void setDocumentImg(String documentImg) {
        this.documentImg = documentImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

}
