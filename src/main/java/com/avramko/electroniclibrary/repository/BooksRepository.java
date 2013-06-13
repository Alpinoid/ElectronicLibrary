package com.avramko.electroniclibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Tags;

import java.lang.Integer;
import com.avramko.electroniclibrary.domain.Publishers;
import java.util.List;
import java.lang.String;

public interface BooksRepository extends CrudRepository<Books, Integer>{
	
	List<Books> findByBooksPublisher(Publishers bookspublisher, Sort sort);
	List<Books> findByAuthorsOfBooks(List<Authors> authorsofbooks, Sort sort);
	List<Books> findByTagsOfBooks(List<Tags> tagsofbooks, Sort sort);

	Page<Books> findAll(Pageable pageable);
	Page<Books> findByBooksNameLike(String booksname, Pageable pageable);
	Page<Books> findByBooksDescriptionLike(String booksdescription, Pageable pageable);

}