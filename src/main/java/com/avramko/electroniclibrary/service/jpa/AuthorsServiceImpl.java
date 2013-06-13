package com.avramko.electroniclibrary.service.jpa;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.repository.AuthorsRepository;
import com.avramko.electroniclibrary.service.AuthorsService;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaAuthorService")
@Repository
@Transactional
public class AuthorsServiceImpl implements AuthorsService {

	@Autowired
	private AuthorsRepository authorRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Authors> getAllAuthors() { 
        return (List<Authors>) authorRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Authors getAuthorById(Integer author_id) {	
		return authorRepository.findOne(author_id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Authors> getAuthorsByName(String name_substring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Authors> getAuthorsByBook(Books book) {
		HashSet<Books> books = new HashSet<Books>();
		books.add(book);
		return authorRepository.findByBooksOfAuthors(books);
	}

	@Override
	public void save(Authors author) {
		authorRepository.save(author);
	}

	@Override
	public void delete(Authors author) {	
		authorRepository.delete(author);
	}
    
}