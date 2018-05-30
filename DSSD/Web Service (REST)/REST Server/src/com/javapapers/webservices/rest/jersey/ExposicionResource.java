package com.javapapers.webservices.rest.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

public class ExposicionResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	ExposicionService exposicionService;

	public ExposicionResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		exposicionService = new ExposicionService();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public Exposicion getExposicion() {
		Exposicion exposicion = exposicionService.getExposicion(id);
		return exposicion;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public Exposicion getAnimalAsHtml() {
		Exposicion exposicion = exposicionService.getExposicion(id);
		return exposicion;
	}


}