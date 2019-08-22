package com.batchprocess.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.behaviour.ModelBase;

public interface IService<T extends ModelBase> {
	T get(Integer id);
	List<T> findAll();
	JpaRepository<T, Integer> dao();
	T update(T obj);
	Integer create(T obj);
	T save(T obj);
	T remove( T obj );
}
