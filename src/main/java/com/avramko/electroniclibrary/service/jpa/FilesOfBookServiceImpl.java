package com.avramko.electroniclibrary.service.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.FilesOfBook;
import com.avramko.electroniclibrary.repository.FilesOfBookRepository;
import com.avramko.electroniclibrary.service.FilesOfBookService;

@Service("jpaFilesService")
@Repository
@Transactional
public class FilesOfBookServiceImpl implements FilesOfBookService{
	
	/**
	 * @uml.property  name="filesRepository"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
	private FilesOfBookRepository filesRepository;	
	
	/**
	 * @uml.property  name="em"
	 * @uml.associationEnd  readOnly="true"
	 */
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public FilesOfBook getFileById(Integer file_id) {
		return filesRepository.findOne(file_id);
	}

	@Override
	@Transactional(readOnly=true)
	public FilesOfBook getFileByBook(Books book) {
		return filesRepository.findByBook(book);
	}

	@Override
	public void save(FilesOfBook file) {
		filesRepository.save(file);
	}

	@Override
	public void delete(FilesOfBook file) {
		filesRepository.delete(file);
	}
	

}
