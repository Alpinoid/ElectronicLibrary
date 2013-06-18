package com.avramko.electroniclibrary.domain;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="filesofbook",
       catalog="librarydb") 

public class FilesOfBook {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="FileID", unique=true, nullable=false)
    private Integer idFiles;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BookID", nullable=false)
    private Books book;
    
    @Column(name="FileName", nullable=false, length=256)
    private String filesName;
    
    @Column(name="FileType", nullable=false, length=64)
    private String filesType;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob @Column(name="File")
    private byte[] file;
    
    public FilesOfBook() {
    }
    
    public FilesOfBook(Books book, MultipartFile fileBook) {
    	this.book = book;
    	this.filesName = fileBook.getOriginalFilename();
    	this.filesType = fileBook.getContentType();
    	
    	byte[] fileContent = null;
    	try {
    		InputStream inputStream = fileBook.getInputStream();
    		fileContent = IOUtils. toByteArray(inputStream);
    	} catch (IOException ex) {}
    	this.file = fileContent;
    }
    

	public Integer getIdFiles() {
		return idFiles;
	}

	public void setIdFiles(Integer idFiles) {
		this.idFiles = idFiles;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public String getFilesName() {
		return filesName;
	}

	public void setFilesName(String filesName) {
		this.filesName = filesName;
	}

	public String getFilesType() {
		return filesType;
	}

	public void setFilesType(String filesType) {
		this.filesType = filesType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	public void setFile(MultipartFile fileBook) {
    	this.filesName = fileBook.getOriginalFilename();
    	this.filesType = fileBook.getContentType();
    	
    	byte[] fileContent = null;
    	try {
    		InputStream inputStream = fileBook.getInputStream();
    		fileContent = IOUtils.toByteArray(inputStream);
    	} catch (IOException ex) {}
    	this.file = fileContent;
	}
	
}