package pl.mmat.samples.mp.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfigurationsResponse {
	
	List<String> configurations;
	
}
