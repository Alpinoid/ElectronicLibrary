package com.avramko.electroniclibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.FilesOfBook;

import java.lang.Integer;

public interface FilesOfBookRepository extends CrudRepository<FilesOfBook, Integer>{
	FilesOfBook findByBook(Books book);
}
