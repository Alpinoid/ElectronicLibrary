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

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="CommentID", unique=true, nullable=false)
    private Integer commentID;
    
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="CommentUser", nullable=false)
    @Column(name="CommentUser", nullable=false, length=50)
    private String commentUser;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CommentBook", nullable=false)
    private Books commentBook;
    
    @Column(name="CommentDate", nullable=false)
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @DateTimeFormat(iso=ISO.DATE)
    private DateTime commentDate;
    
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

	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public String getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	public Books getCommentBook() {
		return commentBook;
	}

	public void setCommentBook(Books commentBook) {
		this.commentBook = commentBook;
	}

	public DateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(DateTime commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentText() {
		return commentText;
	}

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