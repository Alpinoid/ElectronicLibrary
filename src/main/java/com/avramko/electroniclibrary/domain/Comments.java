package com.avramko.electroniclibrary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@Table(name="commentsofbooks",
       catalog="librarydb"
)
public class Comments {

    /**
	 * @uml.property  name="commentID"
	 */
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="CommentID", unique=true, nullable=false)
    private Integer commentID;
    
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="CommentUser", nullable=false)
    /**
	 * @uml.property  name="commentUser"
	 */
    @Column(name="CommentUser", nullable=false, length=50)
    private String commentUser;
    
    /**
	 * @uml.property  name="commentBook"
	 * @uml.associationEnd  
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CommentBook", nullable=false)
    private Books commentBook;
    
    /**
	 * @uml.property  name="commentDate"
	 * @uml.associationEnd  
	 */
    @Column(name="CommentDate", nullable=false)
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @DateTimeFormat(iso=ISO.DATE)
    private DateTime commentDate;
    
    /**
	 * @uml.property  name="commentText"
	 */
    @NotEmpty(message="{validation_name_notEmpty}")
    @Length(max=65535, message="{validation_name_size}")
    @Column(name="CommentText", nullable=false, length=65535)
    private String commentText;

	public Comments() {
		this.commentDate = new DateTime();
	}

	public Comments(String commentUser, Books commentBook) {
		this.commentUser = commentUser;
		this.commentBook = commentBook;
	}

	/**
	 * @return
	 * @uml.property  name="commentID"
	 */
	public Integer getCommentID() {
		return commentID;
	}

	/**
	 * @param commentID
	 * @uml.property  name="commentID"
	 */
	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	/**
	 * @return
	 * @uml.property  name="commentUser"
	 */
	public String getCommentUser() {
		return commentUser;
	}

	/**
	 * @param commentUser
	 * @uml.property  name="commentUser"
	 */
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	/**
	 * @return
	 * @uml.property  name="commentBook"
	 */
	public Books getCommentBook() {
		return commentBook;
	}

	/**
	 * @param commentBook
	 * @uml.property  name="commentBook"
	 */
	public void setCommentBook(Books commentBook) {
		this.commentBook = commentBook;
	}

	/**
	 * @return
	 * @uml.property  name="commentDate"
	 */
	public DateTime getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate
	 * @uml.property  name="commentDate"
	 */
	public void setCommentDate(DateTime commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * @return
	 * @uml.property  name="commentText"
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 * @uml.property  name="commentText"
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	@Override
	public String toString() {
		String result = null;
		result = "ID: " + this.getCommentID() +"\n" +
				 "Date: " + this.getCommentDate() +"\n" +
				 "Book: " + this.getCommentBook() +"\n" +
				 "User: " + this.getCommentUser() +"\n" +
				 "Text: " + this.getCommentText();
		return result;
	}

}