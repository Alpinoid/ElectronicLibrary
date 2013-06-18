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

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="BookID", unique=true, nullable=false)
    private Integer idBooks;
    
    @NotEmpty(message="{validation_name_notEmpty}")
	@Length(min=3, max=128, message="{validation_name_size}")
    @Column(name="BookName", nullable=false, length=128)
    private String booksName;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "authorsofbooks", catalog = "librarydb",
			joinColumns = { @JoinColumn(name = "BooksID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "AuthorsID", nullable = false, updatable = false) })
	private Set<Authors> authorsOfBooks = new HashSet<Authors>(0);
      
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BookPublisher", nullable=false)
    private Publishers booksPublisher;
    
    @Length(max=65535, message="{validation_name_size}")
    @Column(name="BookDescription", length=65535)
    private String booksDescription;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob @Column(name="BookImage")
    private byte[] booksImage;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "tagsofbooks", catalog = "librarydb",
			joinColumns = { @JoinColumn(name = "BooksID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "TagsID", nullable = false, updatable = false) })
	private Set<Tags> tagsOfBooks = new HashSet<Tags>(0);
    
    @Column(name="BookPublishDate")
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @DateTimeFormat(iso=ISO.DATE)
    private DateTime booksPublishDate;
    
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="book")
    private FilesOfBook booksFile;
    
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

    public Integer getIdBooks() {
        return this.idBooks;
    }
    
    public void setIdBooks(Integer idBooks) {
        this.idBooks = idBooks;
    }
    
    public String getBooksName() {
        return this.booksName;
    }
    
    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }
     
    public Set<Authors> getAuthorsOfBooks() {
    	return this.authorsOfBooks;
    }

    public void setAuthorsOfBooks(Set<Authors> authorsOfBooks) {
    	this.authorsOfBooks = authorsOfBooks;
    }

    public String getBooksDescription() {
        return this.booksDescription;
    }
    
    public void setBooksDescription(String booksDescription) {
        this.booksDescription = booksDescription;
    }
    

    public Publishers getBooksPublisher() {
        return this.booksPublisher;
    }
    
    public void setBooksPublisher(Publishers booksPublisher) {
        this.booksPublisher = booksPublisher;
    }

    public byte[] getBooksImage() {
        return this.booksImage;
    }
    
    public void setBooksImage(byte[] booksImage) {
        this.booksImage = booksImage;
    }

    public Set<Tags> getTagsOfBooks() {
        return this.tagsOfBooks;
    }
    
    public void setTagsOfBooks(Set<Tags> tagsOfBooks) {
        this.tagsOfBooks = tagsOfBooks;
    }
    

	public DateTime getBooksPublishDate() {
		return booksPublishDate;
	}

	public void setBooksPublishDate(DateTime booksPublishDate) {
		this.booksPublishDate = booksPublishDate;
	}

	public FilesOfBook getBooksFile() {
		return booksFile;
	}

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