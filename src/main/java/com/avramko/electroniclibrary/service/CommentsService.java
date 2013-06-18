package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Comments;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CommentsService {
	
    //Получить комментарии по книге
	public List<Comments> getCommentsByBook(Books book);
	
	//Получить комментарии по книге с разбивкой по страницам
    public Page<Comments> getCommentsByBook(Books book, PageRequest pageRequest);
 
    //Добавить или обновить комментарий
    public void save(Comments comment);
    
    //Удалить комментарий
    public void delete(Comments comment);
    
}