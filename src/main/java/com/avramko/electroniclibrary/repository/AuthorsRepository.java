package com.avramko.electroniclibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.avramko.electroniclibrary.domain.Authors;

import java.lang.Integer;
import java.util.List;
import java.util.Set;

public interface AuthorsRepository extends CrudRepository<Authors, Integer>{
	
	@SuppressWarnings("rawtypes")
	List<Authors> findByBooksOfAuthors(Set booksOfAuthors);
	
}
