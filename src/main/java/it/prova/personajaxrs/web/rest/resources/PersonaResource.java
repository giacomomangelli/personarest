package it.prova.personajaxrs.web.rest.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.prova.personajaxrs.model.Persona;
import it.prova.personajaxrs.service.factory.MyServiceFactory;
import it.prova.personajaxrs.service.persona.IPersonaService;
import it.prova.personajaxrs.utility.UtilityForm;

@Path("/persona")
public class PersonaResource {

	private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

	@Context
	HttpServletRequest request;

	private IPersonaService personaServiceInstance;

	public PersonaResource() {
		personaServiceInstance = MyServiceFactory.getPersonaServiceInstance();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersona(@PathParam("id") Long id) throws Exception {
		LOGGER.info("Verbo Http..............." + request.getMethod());
		Persona personaToGet = personaServiceInstance.get(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String risultato = objectMapper.writeValueAsString(personaToGet);
		return Response.status(200).entity(risultato).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertPersona(Persona personaInstance) throws Exception {
		LOGGER.info("Verbo Http............." + request.getMethod());
		personaServiceInstance.insert(personaInstance);
		return Response.status(200).entity(personaInstance).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() throws Exception {
		LOGGER.info("Verbo Http........................" + request.getMethod());
		List<Persona> persone = personaServiceInstance.list();
		ObjectMapper objectMapper = new ObjectMapper();
		String risultato = objectMapper.writeValueAsString(persone);
		return Response.status(200).entity(risultato).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	public Response deletePersona(@PathParam("id") Long id) throws Exception {
		LOGGER.info("Verbo Http......................" + request.getMethod());
		if (personaServiceInstance.get(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
		}
		return Response.status(200).entity("Rimossa Persona con id: " + id).build();
	}

	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchByExamplePersona(Persona personaExample) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Persona> persone = personaServiceInstance.findByExample(personaExample);
		ObjectMapper objectMapper = new ObjectMapper();
		String risultato = objectMapper.writeValueAsString(persone);
		return Response.status(200).entity(risultato).build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersona(@QueryParam("nome") String nome, @QueryParam("cognome") String cognome, @QueryParam("dataNascita") String dataNascita) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaExample = new Persona(nome, cognome, UtilityForm.parseDateArrivoFromJson(dataNascita));
		List<Persona> personeFromExample = personaServiceInstance.findByExample(personaExample);
		ObjectMapper objectMapper = new ObjectMapper();
		String risultato = objectMapper.writeValueAsString(personeFromExample);
		return Response.status(200).entity(risultato).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornaPersona(Persona personaInput) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaToUpdate = personaServiceInstance.update(personaInput);
		return Response.status(200).entity(personaToUpdate).build();
	}

}
