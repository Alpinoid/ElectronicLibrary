package com.avramko.electroniclibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.avramko.electroniclibrary.domain.Publishers;

import java.lang.Integer;

public interface PublishersRepository extends CrudRepository<Publishers, Integer>{
}
