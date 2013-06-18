package com.avramko.electroniclibrary.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="authors",
       catalog="librarydb"
)
public class Authors {

	@Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="AuthorID", unique=true, nullable=false)
    private Integer idAuthors;

	@NotEmpty(message="{validation_name_notEmpty}")
	@Length(min=3, max=64, message="{validation_name_size}")
	@Column(name="AuthorName", nullable=false, length=64)
    private String authorsName;
    
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorsOfBooks")
    private Set<Books> booksOfAuthors = new HashSet<Books>(0);

    public Authors() {
    }
	
    public Authors(String authorsName) {
        this.authorsName = authorsName;
    }
    
    public Authors(Integer idAuthors, String authorsName) {
    	this.idAuthors = idAuthors;
        this.authorsName = authorsName;
    }
    
    public Authors(String authorsName, HashSet<Books> booksOfAuthors) {
       this.authorsName = authorsName;
       this.booksOfAuthors = booksOfAuthors;
    }
   
    public Integer getIdAuthors() {
        return this.idAuthors;
    }
    
    public void setIdAuthors(Integer idAuthors) {
        this.idAuthors = idAuthors;
    }
    
    public String getAuthorsName() {
        return this.authorsName;
    }
    
    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public Set<Books> getBooksOfAuthors() {
        return this.booksOfAuthors;
    }
    
    public void setBooksOfAuthors(Set<Books> booksOfAuthors) {
        this.booksOfAuthors = booksOfAuthors;
    }

}