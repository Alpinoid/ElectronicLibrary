package com.avramko.electroniclibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.avramko.electroniclibrary.domain.Tags;
import java.lang.Integer;
import java.util.List;
import java.util.Set;

public interface TagsRepository extends CrudRepository<Tags, Integer>{
	
	@SuppressWarnings("rawtypes")
	List<Tags> findByBooksOfTags(Set booksoftags);
	
}
