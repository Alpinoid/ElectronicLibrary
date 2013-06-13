package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Publishers;
import com.avramko.electroniclibrary.domain.Tags;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BooksService {
    
	//Получить книгу по ID
	public Books getBookById(Integer book_id);
	
    //Получить список всех книг
    public Page<Books> getAllBooks(PageRequest pageRequest);
    
    //Получить книги/книгу по подстроке наименования
    public Page<Books> getBooksByName(String nameSubstring, PageRequest pageRequest);
    
    //Получить книги/книгу по подстроке описания
    public Page<Books> getBooksByDescription(String descriptionSubstring, PageRequest pageRequest);

	//Получить книги/книгу по подстроке из произвольного поля
	public Page<Books> getBooksByCustomField (String customField, String searchSubstring, PageRequest pageRequest);
	
    //Получить книги/книгу по автору
	public List<Books> getBooksByAuthor(Authors author);
	
    //Получить книги/книгу по издательству
	public List<Books> getBooksByPublisher(Publishers publisher);
    
    //Получить книги/книгу по тэгу
	public List<Books> getBooksByTag(Tags tag);
 
    //Добавить или обновить книгу
    public void save(Books book);
    
    //Удалить книгу
    public void delete(Books book);
    
}