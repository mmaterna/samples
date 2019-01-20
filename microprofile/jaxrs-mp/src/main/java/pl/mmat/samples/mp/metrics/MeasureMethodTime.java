package pl.mmat.samples.mp.metrics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/metrics")
public class MeasureMethodTime {

	@Path("/method-time")
	@GET
	@Timed(
			name="metrics-time",
			absolute=true,
			description="Processing time of method 'measureTime'", 
			unit = MetricUnits.SECONDS
			)
	public String measureTime() {
		long start = System.currentTimeMillis();
		try {
			double rand = Math.random() * 2000;
			Thread.sleep((long)rand);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long time = System.currentTimeMillis() - start;
		log.info("MeasureMethodTime method time: {}", time);
		return "Method time: " + time;
	}


	@Path("/method-count-monotonic")
	@GET
	@Counted(
			name="metrics-count-monotonic",
			description="Count all invocation of method 'countInvocationsMonotonic'", 
			monotonic = true,
			absolute = true
			)
	public String countInvocationsMonotonic() {
		log.info("count monotonic called");
		return "count monotonic called";
	}

	@Path("/method-count-concurrent")
	@GET
	@Counted(
			name="metrics-count-concurrent",
			description="Count CONCURRENT (not monotonic) invocations of method 'countConcurrentInvocations'", 
			monotonic = false, 
			absolute = true
			)
	public String countConcurrentInvocations() throws InterruptedException {
		log.info("count countConcurrentInvocations called");
		long start = System.currentTimeMillis();
		// long sleep to make concurrent request easier
		double rand = Math.random() * 20000;
		Thread.sleep((long)rand);
		long time = System.currentTimeMillis() - start;
		log.info("countConcurrentInvocations finished after: {}", time);
		return "countConcurrentInvocations finished after: " + time;
	}
	

	private long countedValue = 0;
	
	@Path("/method-gauge")
	@GET
	public String gauge() {
		log.info("gauge called");
		countedValue++;
		return "gauge-result";
	}

	// wildfly throws exception on second call of any endpoint from this class when gauge is declared
	
//	@Gauge(
//			name="metrics-gauge",
//			unit = MetricUnits.NONE,
//			description = "Gauge metrics on method 'gauge'"
//			)
//	public long gaugeMetric() {
//		return countedValue;
//	}
	

	@Path("/method-metered")
	@GET
	@Metered(
			name = "metrics-metered",
			absolute=true
			)
	public String meteredInvocations() {
		return "metered";
	}
	
}
