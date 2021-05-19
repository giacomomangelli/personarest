package it.prova.personajaxrs.service.factory;

import it.prova.personajaxrs.dao.IPersonaDao;
import it.prova.personajaxrs.dao.PersonaDaoImpl;
import it.prova.personajaxrs.service.persona.IPersonaService;
import it.prova.personajaxrs.service.persona.PersonaServiceImpl;

public class MyServiceFactory {

	private static IPersonaDao PERSONA_DAO_INSTANCE = null;
	private static IPersonaService PERSONA_SERVICE_INSTANCE = null;

	public static IPersonaService getPersonaServiceInstance() {
		if (PERSONA_SERVICE_INSTANCE == null)
			PERSONA_SERVICE_INSTANCE = new PersonaServiceImpl();

		if (PERSONA_DAO_INSTANCE == null)
			PERSONA_DAO_INSTANCE = new PersonaDaoImpl();

		PERSONA_SERVICE_INSTANCE.setPersonaDao(PERSONA_DAO_INSTANCE);
		return PERSONA_SERVICE_INSTANCE;
	}

}
