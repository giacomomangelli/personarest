package it.prova.personajaxrs.service.persona;

import java.util.List;

import it.prova.personajaxrs.dao.IPersonaDao;
import it.prova.personajaxrs.model.Persona;

public interface IPersonaService {

	public List<Persona> list() throws Exception;
	
	public Persona get(Long id) throws Exception;
	
	public void insert(Persona instance) throws Exception;
	
	public Persona update(Persona instance) throws Exception;

	public void delete(Persona instance) throws Exception;
	
	public List<Persona> findByExample(Persona example) throws Exception;

	public void setPersonaDao(IPersonaDao personaInstance);
	
}
