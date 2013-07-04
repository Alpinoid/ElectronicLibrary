package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Publishers;
import java.util.List;

public interface PublishersService {
    
    //Получить список всех издательств
    public List<Publishers> getAllPublishers();
    
	//Получить издательство по ID
	public Publishers getPublisherById(Integer publisherId);
    
    //Добавить или обновить издательство
    public void save(Publishers publisher);
    
    //Удалить издательство
    public void delete(Publishers publisher);
   
}