package com.javapapers.webservices.rest.jersey;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum ExposicionDao {
	instance;

	private Map<String, Exposicion> exposiciones = new HashMap<String, Exposicion>();

	private ExposicionDao() {

		//pumping-in some default data
		
		Date d = new Date();
		
		d.setYear(117);
		d.setMonth(11);
		d.setDate(24);
		
		d.setHours(16);
		d.setMinutes(30);
		
		Exposicion exposicion = new Exposicion("1", new SimpleDateFormat("yyyy-MM-dd hh:mm").format(d) , "Salon 2-b");
		exposiciones.put("1", exposicion);

	}

	public Map<String, Exposicion> getExposiciones() {
		return exposiciones;
	}

}
