package com.avramko.electroniclibrary.service.jpa;

import com.avramko.electroniclibrary.domain.Publishers;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.repository.PublishersRepository;
import com.avramko.electroniclibrary.service.PublishersService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaPublisherService")
@Repository
@Transactional
public class PublishersServiceImpl implements PublishersService {

	/**
	 * @uml.property  name="publisherRepository"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
	private PublishersRepository publisherRepository;	
	
	/**
	 * @uml.property  name="em"
	 * @uml.associationEnd  readOnly="true"
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public List<Publishers> getAllPublishers() { 

        Iterable<Publishers> publishers = publisherRepository.findAll();

        return (List<Publishers>) publishers;
	}

	@Override
	@Transactional(readOnly=true)
	public Publishers getPublisherById(Integer publisher_id) {	
		return publisherRepository.findOne(publisher_id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Publishers> getPublishersByName(String name_substring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public Publishers getPublishersByBook(Books book) {				
		return null;
	}

	@Override
	public void save(Publishers publisher) {
		publisherRepository.save(publisher);
	}

	@Override
	public void delete(Publishers publisher) {	
		publisherRepository.delete(publisher);
	}

}