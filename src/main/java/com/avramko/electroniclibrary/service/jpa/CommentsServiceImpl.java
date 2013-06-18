package com.avramko.electroniclibrary.service.jpa;

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

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Comments;
import com.avramko.electroniclibrary.repository.CommentsRepository;
import com.avramko.electroniclibrary.service.CommentsService;

@Service("jpaCommentService")
@Repository
@Transactional
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsRepository commentRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public List<Comments> getCommentsByBook(Books book) {
		Sort sort = new Sort(Direction.DESC, "commentDate");
		return commentRepository.findByCommentBook(book, sort);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Comments> getCommentsByBook(Books book, PageRequest pageRequest) {
		return commentRepository.findByCommentBook(book, pageRequest);
	}

	@Override
	public void save(Comments comment) {
		commentRepository.save(comment);
	}

	@Override
	public void delete(Comments comment) {
		commentRepository.delete(comment);
	}

}