package it.prova.personajaxrs.service.persona;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.personajaxrs.dao.IPersonaDao;
import it.prova.personajaxrs.model.Persona;
import it.prova.personajaxrs.web.listener.LocalEntityManagerFactoryListener;

public class PersonaServiceImpl implements IPersonaService {

	private IPersonaDao personaDao;

	@Override
	public List<Persona> list() throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			personaDao.setEntityManager(entityManager);
			return personaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Persona get(Long id) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			personaDao.setEntityManager(entityManager);

			return personaDao.get(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void insert(Persona instance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			personaDao.setEntityManager(entityManager);

			personaDao.insert(instance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Persona update(Persona instance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			entityManager.getTransaction().begin();

			personaDao.setEntityManager(entityManager);

			Persona personaUpdatedInstance = personaDao.update(instance).orElse(null);

			entityManager.getTransaction().commit();
			
			return personaUpdatedInstance;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void delete(Persona instance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			personaDao.setEntityManager(entityManager);

			instance = entityManager.merge(instance);
			personaDao.delete(instance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void setPersonaDao(IPersonaDao personaInstance) {
		this.personaDao = personaInstance;
	}

	@Override
	public List<Persona> findByExample(Persona example) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			personaDao.setEntityManager(entityManager);

			return personaDao.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
