package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Publishers;
import com.avramko.electroniclibrary.domain.Books;
import java.util.List;

public interface PublishersService {
    
    //Получить список всех издательств
    public List<Publishers> getAllPublishers();
    
	//Получить издательство по ID
	public Publishers getPublisherById(Integer publisher_id);
   
    //Получить издательства/издательств по подстроке наименования
    public List<Publishers> getPublishersByName(String name_substring);
    
    //Получить издательство по книге
	public Publishers getPublishersByBook(Books book);
    
    //Добавить или обновить издательство
    public void save(Publishers publisher);
    
    //Удалить издательство
    public void delete(Publishers publisher);
   
}