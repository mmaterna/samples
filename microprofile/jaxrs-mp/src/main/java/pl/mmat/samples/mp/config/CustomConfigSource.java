package pl.mmat.samples.mp.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

import lombok.extern.slf4j.Slf4j;

/**
 * User-provided ConfigSources are dynamic. The getProperties() method will be
 * periodically invoked by the runtime to retrieve up-to-date values. The
 * frequency is controlled by the microprofile.config.refresh.rate Java system
 * property, which is in milliseconds and can be customized.
 */
@Slf4j
public class CustomConfigSource implements ConfigSource {

	@Override
	public int getOrdinal() {
		log.info("CustomConfigSources - called getOridinal");
		return Integer.parseInt(getProperties().get("config_ordinal"));
	}

	@Override
	public Set<String> getPropertyNames() {
		log.info("CustomConfigSources - called getPropertyNames");
		return getProperties().keySet();
	}

	@Override
	public String getValue(String key) {
		log.info("CustomConfigSources - called getValue, key: {}", key);
		return getProperties().get(key);
	}

	@Override
	public String getName() {
		log.info("CustomConfigSources - called getName");
		return "Custom Config Source: hardcoded";
	}

	/**
	 * Instead of hardcoding we can check a file configuration or request to another service
	 */
	public Map<String, String> getProperties() {
		Map<String, String> customPropertiesMap = new HashMap<>();
		customPropertiesMap.put("jaxrs-mp.mp.config.element", "value from custom config source");
		customPropertiesMap.put("jaxrs-mp.mp.config.only-in-custom-source", "Only in custom source " + System.currentTimeMillis());
		customPropertiesMap.put("config_ordinal", "150");
		return customPropertiesMap;

	}

}
