package com.avramko.electroniclibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Comments;

import java.lang.Integer;
import java.util.List;

public interface CommentsRepository extends CrudRepository<Comments, Integer>{
	
	List<Comments> findByCommentBook(Books book, Sort sort);
	
	Page<Comments> findByCommentBook(Books book, Pageable pageable);

}