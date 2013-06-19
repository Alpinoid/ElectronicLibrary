package com.avramko.electroniclibrary.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="publishers",
       catalog="librarydb",
       uniqueConstraints = @UniqueConstraint(columnNames="PublisherName") 
)
public class Publishers {

    /**
	 * @uml.property  name="idPublishers"
	 */
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="PublisherID", unique=true, nullable=false)
    private Integer idPublishers;
    
    /**
	 * @uml.property  name="publishersName"
	 */
    @NotEmpty(message="{validation_name_notEmpty}")
	@Length(min=3, max=64, message="{validation_name_size}")
    @Column(name="PublisherName", unique=true, nullable=false, length=64)
    private String publishersName;
    
    /**
	 * @uml.property  name="books"
	 */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="booksPublisher")
    private Set<Books> books = new HashSet<Books>(0);

    public Publishers() {
    }
	
    public Publishers(String publishersName) {
        this.publishersName = publishersName;
    }
    public Publishers(String publishersName, Set<Books> books) {
       this.publishersName = publishersName;
       this.books = books;
    }
   
    /**
	 * @return
	 * @uml.property  name="idPublishers"
	 */
    public Integer getIdPublishers() {
        return this.idPublishers;
    }
    
    /**
	 * @param idPublishers
	 * @uml.property  name="idPublishers"
	 */
    public void setIdPublishers(Integer idPublishers) {
        this.idPublishers = idPublishers;
    }
    
    /**
	 * @return
	 * @uml.property  name="publishersName"
	 */
    public String getPublishersName() {
        return this.publishersName;
    }
    
    /**
	 * @param publishersName
	 * @uml.property  name="publishersName"
	 */
    public void setPublishersName(String publishersName) {
        this.publishersName = publishersName;
    }

    public Set<Books> getBooks() {
        return this.books;
    }
    
    public void setBooks(Set<Books> books) {
        this.books = books;
    }

}