package com.batchprocess.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.common.behaviour.ModelBase;
import com.common.constant.SysConstant;

public abstract class ServiceBase<T extends ModelBase> implements IService<T> {
	
	@Override @Transactional( readOnly = true )
	public T get(Integer id) {
		return dao().findById(id).get();
	}

	@Override @Transactional( readOnly = true )
	public List<T> findAll() {
		return dao().findAll();
	}

	@Override @Transactional( readOnly = false )
	public T update(T obj) {
		dao().save(obj);
		return obj;
	}

	@Override @Transactional( readOnly = false )
	public Integer create(T obj) {
		obj = dao().save(obj);
		return obj.getId();
	}
	
	@Override @Transactional( readOnly = false )
	public T remove(T obj) {
		obj.setActive( SysConstant.N );
		dao().save( obj );
		return obj;
	}
	
	@Override @Transactional( readOnly = false )
	public T save(T obj) {
		obj = dao().save(obj);
		return obj;
	}
	
}
