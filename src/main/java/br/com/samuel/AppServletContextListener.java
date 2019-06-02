package br.com.samuel;

import java.util.Arrays;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.samuel.entity.City;
import br.com.samuel.service.CityService;

@WebListener
public  class AppServletContextListener implements ServletContextListener {

	private void init(){
		City city1 = new City(1L, "São Paulo", -23.5489, -46.6388);
		City city2 = new City(2L, "Belo Horizonte", -19.8157, -43.9542);
		City city3 = new City(3L, "Brasília", -15.7801, -47.9292);
		City city4 = new City(4L, "Salvador", -12.9704, -38.5124);
		City city5 = new City(5L, "Porto Alegre", -30.0277, -51.2287);
		CityService service = new CityService();
		service.deleteAllCities();
		service.insertCities(Arrays.asList(city1,city2,city3,city4,city5));
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		init();
	}
}
