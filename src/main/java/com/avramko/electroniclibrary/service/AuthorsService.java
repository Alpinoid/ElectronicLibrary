package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import java.util.List;

public interface AuthorsService {
    
    //Получить список всех авторов
    public List<Authors> getAllAuthors();
    
	//Получить автора по ID
	public Authors getAuthorById(Integer author_id);
	
    //Поиск автора/авторов по подстроке наименования
    public List<Authors> getAuthorsByName(String name_substring);
    
    //Получить автора/авторов книги
	public List<Authors> getAuthorsByBook(Books book);
    
    //Добавить или обновить автора
    public void save(Authors author);
    
    //Удалить автора
    public void delete(Authors author);

}