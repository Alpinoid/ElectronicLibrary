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
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tags",
       catalog="librarydb",
       uniqueConstraints = @UniqueConstraint(columnNames="TagName") 
)
public class Tags {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="TagID", unique=true, nullable=false)
    private Integer idTags;

    @NotEmpty(message="{validation_name_notEmpty}")
	@Length(min=3, max=32, message="{validation_name_size}")
    @Column(name="TagName", unique=true, nullable=false, length=32)
    private String tagsName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tagsOfBooks")
    private Set<Books> booksOfTags = new HashSet<Books>(0);

    public Tags() {
    }
    
    public Tags(String tagsName) {
        this.tagsName = tagsName;
    }
    
    public Tags(String tagsName, Set<Books> booksOfTags) {
       this.tagsName = tagsName;
       this.booksOfTags = booksOfTags;
    }

    public Integer getIdTags() {
        return this.idTags;
    }

    public void setIdTags(Integer idTags) {
        this.idTags = idTags;
    }

    public String getTagsName() {
        return this.tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public Set<Books> getBooksOfTags() {
        return this.booksOfTags;
    }
    
    public void setBooksOfTags(Set<Books> booksOfTags) {
        this.booksOfTags = booksOfTags;
    }
    
	@Override
	public String toString() {
		String result = "Tag - ID: " + this.getIdTags() + "; Name: " + this.getTagsName();
		return result;
	}

}