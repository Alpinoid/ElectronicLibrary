package com.avramko.electroniclibrary.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="books",
       catalog="librarydb"
)
public class Books {

    /**
	 * @uml.property  name="idBooks"
	 */
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="BookID", unique=true, nullable=false)
    private Integer idBooks;
    
    /**
	 * @uml.property  name="booksName"
	 */
    @NotEmpty(message="{validation_name_notEmpty}")
	@Length(min=3, max=128, message="{validation_name_size}")
    @Column(name="BookName", nullable=false, length=128)
    private String booksName;
    
    /**
	 * @uml.property  name="authorsOfBooks"
	 */
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "authorsofbooks", catalog = "librarydb",
			joinColumns = { @JoinColumn(name = "BooksID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "AuthorsID", nullable = false, updatable = false) })
	private Set<Authors> authorsOfBooks = new HashSet<Authors>(0);
      
    /**
	 * @uml.property  name="booksPublisher"
	 * @uml.associationEnd  
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BookPublisher", nullable=false)
    private Publishers booksPublisher;
    
    /**
	 * @uml.property  name="booksDescription"
	 */
    @Length(max=65535, message="{validation_name_size}")
    @Column(name="BookDescription", length=65535)
    private String booksDescription;
    
    /**
	 * @uml.property  name="booksImage"
	 */
    @Basic(fetch=FetchType.LAZY)
    @Lob @Column(name="BookImage")
    private byte[] booksImage;
    
    /**
	 * @uml.property  name="tagsOfBooks"
	 */
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "tagsofbooks", catalog = "librarydb",
			joinColumns = { @JoinColumn(name = "BooksID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "TagsID", nullable = false, updatable = false) })
	private Set<Tags> tagsOfBooks = new HashSet<Tags>(0);
    
    /**
	 * @uml.property  name="booksPublishDate"
	 * @uml.associationEnd  
	 */
    @Column(name="BookPublishDate")
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @DateTimeFormat(iso=ISO.DATE)
    private DateTime booksPublishDate;
    
    /**
	 * @uml.property  name="booksFile"
	 * @uml.associationEnd  inverse="book:com.avramko.electroniclibrary.domain.FilesOfBook"
	 */
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="book")
    private FilesOfBook booksFile;
    
    /**
	 * @uml.property  name="comments"
	 */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="commentBook")
    private Set<Comments> comments = new HashSet<Comments>(0);

    public Books() {
    	this.booksName = "";
    	this.booksPublishDate = new DateTime();
    }
	
    public Books(Publishers booksPublisher, String booksName) {
        this.booksPublisher = booksPublisher;
        this.booksName = booksName;
    }

    /**
	 * @return
	 * @uml.property  name="idBooks"
	 */
    public Integer getIdBooks() {
        return this.idBooks;
    }
    
    /**
	 * @param idBooks
	 * @uml.property  name="idBooks"
	 */
    public void setIdBooks(Integer idBooks) {
        this.idBooks = idBooks;
    }
    
    /**
	 * @return
	 * @uml.property  name="booksName"
	 */
    public String getBooksName() {
        return this.booksName;
    }
    
    /**
	 * @param booksName
	 * @uml.property  name="booksName"
	 */
    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }
     
    public Set<Authors> getAuthorsOfBooks() {
    	return this.authorsOfBooks;
    }

    public void setAuthorsOfBooks(Set<Authors> authorsOfBooks) {
    	this.authorsOfBooks = authorsOfBooks;
    }

    /**
	 * @return
	 * @uml.property  name="booksDescription"
	 */
    public String getBooksDescription() {
        return this.booksDescription;
    }
    
    /**
	 * @param booksDescription
	 * @uml.property  name="booksDescription"
	 */
    public void setBooksDescription(String booksDescription) {
        this.booksDescription = booksDescription;
    }
    

    /**
	 * @return
	 * @uml.property  name="booksPublisher"
	 */
    public Publishers getBooksPublisher() {
        return this.booksPublisher;
    }
    
    /**
	 * @param booksPublisher
	 * @uml.property  name="booksPublisher"
	 */
    public void setBooksPublisher(Publishers booksPublisher) {
        this.booksPublisher = booksPublisher;
    }

    /**
	 * @return
	 * @uml.property  name="booksImage"
	 */
    public byte[] getBooksImage() {
        return this.booksImage;
    }
    
    /**
	 * @param booksImage
	 * @uml.property  name="booksImage"
	 */
    public void setBooksImage(byte[] booksImage) {
        this.booksImage = booksImage;
    }

    public Set<Tags> getTagsOfBooks() {
        return this.tagsOfBooks;
    }
    
    public void setTagsOfBooks(Set<Tags> tagsOfBooks) {
        this.tagsOfBooks = tagsOfBooks;
    }
    

	/**
	 * @return
	 * @uml.property  name="booksPublishDate"
	 */
	public DateTime getBooksPublishDate() {
		return booksPublishDate;
	}

	/**
	 * @param booksPublishDate
	 * @uml.property  name="booksPublishDate"
	 */
	public void setBooksPublishDate(DateTime booksPublishDate) {
		this.booksPublishDate = booksPublishDate;
	}

	/**
	 * @return
	 * @uml.property  name="booksFile"
	 */
	public FilesOfBook getBooksFile() {
		return booksFile;
	}

	/**
	 * @param booksFile
	 * @uml.property  name="booksFile"
	 */
	public void setBooksFile(FilesOfBook booksFile) {
		this.booksFile = booksFile;
	}

	@Override
	public String toString() {
		String result = null;
		result = "ID: " + this.getIdBooks() +"\n" +
				 "Name: " + this.getBooksName() +"\n" +
				 "Authors: ";
		Set<Authors> itrA = this.authorsOfBooks;
		for (Authors author : itrA) {
			result = result + "\n"+author.getIdAuthors()+": "+author.getAuthorsName();
		}
		
		result = result + "\n" + "Tags: ";
		Set<Tags> itrT = this.tagsOfBooks;
		for (Tags tag : itrT) {
			result = result + "\n"+tag.getIdTags()+": "+tag.getTagsName();
		}
		return result;
	}

}