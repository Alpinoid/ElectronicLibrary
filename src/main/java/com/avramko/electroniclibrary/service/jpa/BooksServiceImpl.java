package com.avramko.electroniclibrary.service.jpa;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Publishers;
import com.avramko.electroniclibrary.domain.Tags;
import com.avramko.electroniclibrary.repository.BooksRepository;
import com.avramko.electroniclibrary.service.BooksService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaBookService")
@Repository
@Transactional
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository bookRepository;

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public Books getBookById(Integer bookId) {
		return bookRepository.findOne(bookId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Books> getAllBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Books> getBooksByName(String nameSubstring, PageRequest pageRequest) {
		return bookRepository.findByBooksNameLike(nameSubstring, pageRequest);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Books> getBooksByDescription(String descriptionSubstring, PageRequest pageRequest) {
		return bookRepository.findByBooksDescriptionLike(descriptionSubstring, pageRequest);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Books> getBooksByCustomField (String customField, String searchSubstring, PageRequest pageRequest) {
		switch (customField) {
		case "NAME":
			return getBooksByName(searchSubstring, pageRequest);
		case "DESCRIPTION":
			return getBooksByDescription(searchSubstring, pageRequest);
		default:
			return null;
		}
	}

	@Override
	public Page<Books> getBooksByName(Tags tagsOfBooks, String nameSubstring, PageRequest pageRequest) {
		return bookRepository.findByTagsOfBooksAndBooksNameLike(tagsOfBooks, nameSubstring, pageRequest);
	}

	@Override
	public Page<Books> getBooksByDescription(Tags tagsOfBooks, String descriptionSubstring, PageRequest pageRequest) {
		return bookRepository.findByTagsOfBooksAndBooksDescriptionLike(tagsOfBooks, descriptionSubstring, pageRequest);
	}

	@Override
	public Page<Books> getBooksByCustomField(Tags tagsOfBooks, String customField, String searchSubstring, PageRequest pageRequest) {
		switch (customField) {
		case "NAME":
			return getBooksByName(tagsOfBooks, searchSubstring, pageRequest);
		case "DESCRIPTION":
			return getBooksByDescription(tagsOfBooks, searchSubstring, pageRequest);
		default:
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Books> getBooksByTag(Tags tag) {
		Sort sort = new Sort(Direction.ASC, "booksName");
		List<Tags> tagsOfBooks = new ArrayList<Tags>();
		tagsOfBooks.add(tag);
		return bookRepository.findByTagsOfBooks(tagsOfBooks, sort);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Books> getBooksByAuthor(Authors author) {
		Sort sort = new Sort(Direction.ASC, "booksName");
		List<Authors> authorsOfBooks = new ArrayList<Authors>();
		authorsOfBooks.add(author);
		return bookRepository.findByAuthorsOfBooks(authorsOfBooks, sort);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Books> getBooksByPublisher(Publishers publisher) {
		Sort sort = new Sort(Direction.ASC, "booksName");
		return bookRepository.findByBooksPublisher(publisher, sort);
	}

	@Override
	public void save(Books book) {
		bookRepository.save(book);
	}

	@Override
	public void delete(Books book) {
		bookRepository.delete(book);		
	}

    
}