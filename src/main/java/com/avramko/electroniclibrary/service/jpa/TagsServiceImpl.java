package com.avramko.electroniclibrary.service.jpa;

import com.avramko.electroniclibrary.domain.Tags;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.repository.TagsRepository;
import com.avramko.electroniclibrary.service.TagsService;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaTagService")
@Repository
@Transactional
public class TagsServiceImpl implements TagsService {

	@Autowired
	private TagsRepository tagRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Tags> getAllTags() { 
		Sort sort = new Sort(Direction.ASC, "tagsName");
        return (List<Tags>) tagRepository.findAll(sort);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Tags getTagById(Integer tag_id) {	
		return tagRepository.findOne(tag_id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Tags> getTagsByName(String name_substring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Tags> getTagsByBook(Books book) {
 		HashSet<Books> books = new HashSet<Books>();
		books.add(book);
		return tagRepository.findByBooksOfTags(books);
	}

	@Override
	public void save(Tags tag) {
		tagRepository.save(tag);
	}

	@Override
	public void delete(Tags tag) {	
		tagRepository.delete(tag);
	}
    
}