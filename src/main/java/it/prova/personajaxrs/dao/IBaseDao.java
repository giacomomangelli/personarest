package it.prova.personajaxrs.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

public interface IBaseDao<T> {

	public List<T> list() throws Exception;
	
	public Optional<T> get(Long id) throws Exception;
	
	public void insert(T instance) throws Exception;
	
	public Optional<T> update(T instance) throws Exception;

	public void delete(T instance) throws Exception;
	
	public List<T> findByExample(T example) throws Exception;
	
	public void setEntityManager(EntityManager entityManager);

}
