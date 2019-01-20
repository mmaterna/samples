package pl.mmat.samples.mp.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
public class MicroprofileHealth implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse
				.named("jaxrs-mp application")
				.withData("application health metrics 1", "value-1")
				.withData("application health metrics 2", "value-2")
				.up()
				.build();
	}

}
