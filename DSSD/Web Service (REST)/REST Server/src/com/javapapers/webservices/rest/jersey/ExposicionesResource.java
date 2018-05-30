package com.javapapers.webservices.rest.jersey;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/exposicion")
public class ExposicionesResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@Path("{idTrabajo}")
	public ExposicionResource getAnimal(@PathParam("idTrabajo") String id) {
		return new ExposicionResource(uriInfo, request, "1");
	}

}