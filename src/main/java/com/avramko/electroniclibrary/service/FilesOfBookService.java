package com.avramko.electroniclibrary.service;

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.FilesOfBook;

public interface FilesOfBookService {
    
	//Получить файл по ID
	public FilesOfBook getFileById(Integer file_id);
	
    //Получить файл/файлы по книге
	public FilesOfBook getFileByBook(Books book);
    
    //Добавить или обновить файл
    public void save(FilesOfBook file);
    
    //Удалить файл
    public void delete(FilesOfBook file);
    
}