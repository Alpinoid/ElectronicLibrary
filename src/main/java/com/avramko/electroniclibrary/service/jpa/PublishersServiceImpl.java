package com.avramko.electroniclibrary.service.jpa;

import com.avramko.electroniclibrary.domain.Publishers;
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

	@Autowired
	private PublishersRepository publisherRepository;	

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
	public Publishers getPublisherById(Integer publisherId) {	
		return publisherRepository.findOne(publisherId);
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