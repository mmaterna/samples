package pl.mmat.samples.mp.tracing;

import static pl.mmat.samples.mp.utils.LongOperation.sleep;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/traced")
public class TracedResource {

	@GET
	public String traced() {
		log.info("traced resource called");
		sleep();
		return "" + System.currentTimeMillis();
	}

}
