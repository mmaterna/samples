package pl.mmat.samples.mp.ping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/ping")
public class PingResource {

	@GET
	public String ping() {
		String pingMessage = "Ping: " + System.currentTimeMillis();
		log.info(pingMessage);
		return pingMessage;
	}
}
