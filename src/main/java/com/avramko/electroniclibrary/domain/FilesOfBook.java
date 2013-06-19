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

    /**
	 * @uml.property  name="idFiles"
	 */
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="FileID", unique=true, nullable=false)
    private Integer idFiles;
    
    /**
	 * @uml.property  name="book"
	 * @uml.associationEnd  inverse="booksFile:com.avramko.electroniclibrary.domain.Books"
	 */
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BookID", nullable=false)
    private Books book;
    
    /**
	 * @uml.property  name="filesName"
	 */
    @Column(name="FileName", nullable=false, length=256)
    private String filesName;
    
    /**
	 * @uml.property  name="filesType"
	 */
    @Column(name="FileType", nullable=false, length=64)
    private String filesType;
    
    /**
	 * @uml.property  name="file"
	 */
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
    

	/**
	 * @return
	 * @uml.property  name="idFiles"
	 */
	public Integer getIdFiles() {
		return idFiles;
	}

	/**
	 * @param idFiles
	 * @uml.property  name="idFiles"
	 */
	public void setIdFiles(Integer idFiles) {
		this.idFiles = idFiles;
	}

	/**
	 * @return
	 * @uml.property  name="book"
	 */
	public Books getBook() {
		return book;
	}

	/**
	 * @param book
	 * @uml.property  name="book"
	 */
	public void setBook(Books book) {
		this.book = book;
	}

	/**
	 * @return
	 * @uml.property  name="filesName"
	 */
	public String getFilesName() {
		return filesName;
	}

	/**
	 * @param filesName
	 * @uml.property  name="filesName"
	 */
	public void setFilesName(String filesName) {
		this.filesName = filesName;
	}

	/**
	 * @return
	 * @uml.property  name="filesType"
	 */
	public String getFilesType() {
		return filesType;
	}

	/**
	 * @param filesType
	 * @uml.property  name="filesType"
	 */
	public void setFilesType(String filesType) {
		this.filesType = filesType;
	}

	/**
	 * @return
	 * @uml.property  name="file"
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file
	 * @uml.property  name="file"
	 */
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