package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Tags;
import com.avramko.electroniclibrary.domain.Books;
import java.util.List;

public interface TagsService {
    
    //Получить список всех тэгов
    public List<Tags> getAllTags();
    
	//Получить тэг по ID
	public Tags getTagById(Integer tag_id);
	
    //Поиск тэга/тэгов по подстроке наименования
    public List<Tags> getTagsByName(String name_substring);
    
    //Получить тэг/тэги книги
	public List<Tags> getTagsByBook(Books book);
    
    //Добавить или обновить тэг
    public void save(Tags tag);
    
    //Удалить тэг
    public void delete(Tags tag);

}