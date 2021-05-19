package it.prova.personajaxrs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.personajaxrs.model.Persona;

public class PersonaDaoImpl implements IPersonaDao {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Persona> list() throws Exception {
		return entityManager.createQuery("from Persona", Persona.class).getResultList();
	}

	@Override
	public Optional<Persona> get(Long id) throws Exception {
		return Optional.ofNullable(entityManager.find(Persona.class, id));
	}

	@Override
	public void insert(Persona instance) throws Exception {
		entityManager.persist(instance);
	}

	@Override
	public Optional<Persona> update(Persona instance) throws Exception {
		return Optional.ofNullable(entityManager.merge(instance));
	}

	@Override
	public void delete(Persona instance) throws Exception {
		entityManager.remove(entityManager.merge(instance));
	}

	@Override
	public List<Persona> findByExample(Persona example) throws Exception {
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Persona p where p.id = p.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" p.nome like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" p.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (example.getDataNascita() != null) {
			whereClauses.add("p.dataNascita >= :dataNascita ");
			paramaterMap.put("dataNascita", example.getDataNascita());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Persona> typedQuery = entityManager.createQuery(queryBuilder.toString(), Persona.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
