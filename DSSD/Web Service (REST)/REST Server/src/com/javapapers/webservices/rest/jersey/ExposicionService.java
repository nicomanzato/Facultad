package com.javapapers.webservices.rest.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExposicionService {

	ExposicionDao exposicionDao;

	public ExposicionService() {
		exposicionDao = ExposicionDao.instance;
	}

	public void createAnimal(Exposicion animal) {
		exposicionDao.getExposiciones().put(animal.getId(), animal);
	}

	public Exposicion getExposicion(String id) {
		return exposicionDao.getExposiciones().get(id);
	}

	public Map<String, Exposicion> getExposiciones() {
		return exposicionDao.getExposiciones();
	}

	public List<Exposicion> getExposicionesAsList() {
		List<Exposicion> animalList = new ArrayList<Exposicion>();
		animalList.addAll(exposicionDao.getExposiciones().values());
		return animalList;
	}

	public int getExposicionesCount() {
		return exposicionDao.getExposiciones().size();
	}

}
