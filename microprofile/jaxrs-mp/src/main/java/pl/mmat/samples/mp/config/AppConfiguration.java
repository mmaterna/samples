package pl.mmat.samples.mp.config;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;

public class AppConfiguration {

	@Getter
	@Inject
	@ConfigProperty(name="jaxrs-mp.mp.config.element")
	private String definedEverywhere;
	
	@Inject
	@ConfigProperty(name="jaxrs-mp.mp.config.only-in-custom-source")
	private Provider<String> customSourceConfigValue;
	
	@Getter
	@Inject
	@ConfigProperty(name="jaxrs-mp.mp.config.only-in-microprofile-config.properties")
	private String mpConfigValue;
	
	/**
	 * Dynamic get for a value
	 */
	public String getCustomSourceConfigValue() {
		return this.customSourceConfigValue.get();
	}
	
}
