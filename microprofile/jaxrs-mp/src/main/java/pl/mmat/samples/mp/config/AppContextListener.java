package pl.mmat.samples.mp.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {

	
	@PostConstruct
	private void init() {
		log.info("Adding configuration at system property level");
		System.setProperty("jaxrs-mp.mp.config.element", "value from system property");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("App context initialized: " + applicationPath(sce));
		log.info(sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("App context destroyed: " + applicationPath(sce));
	}

	private String applicationPath(ServletContextEvent sce) {
		return sce.getServletContext().getRealPath("");
	}

}
