package pl.mmat.samples.mp.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/configurations")
public class ConfigurationsResource {

	@Inject
	AppConfiguration appConfiguration;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ConfigurationsResponse configurations() {

		List<String> configurationParams = new ArrayList<>();
		configurationParams.add("jaxrs-mp.mp.config.element: " + appConfiguration.getDefinedEverywhere());
		configurationParams.add("jaxrs-mp.mp.config.only-in-custom-source (dynamic): " + appConfiguration.getCustomSourceConfigValue());
		configurationParams.add("jaxrs-mp.mp.config.only-in-microprofile-config.properties: " + appConfiguration.getMpConfigValue());
		
		log.info("Known configurations: {}", configurationParams);
		return new ConfigurationsResponse(configurationParams);
	}
	
}
