package br.com.samuel.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import br.com.samuel.controllers.CityController;
import br.com.samuel.handlers.CustomExceptionMapper;
public class ApplicationResourceConfig extends ResourceConfig {

    public ApplicationResourceConfig() {
    	 register(CityController.class);
    	 register(CustomExceptionMapper.class);
    	 property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
