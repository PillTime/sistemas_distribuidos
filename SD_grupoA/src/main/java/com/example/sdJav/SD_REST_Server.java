package com.example.sdJav;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class SD_REST_Server {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);
		ServletContextHandler sch = new ServletContextHandler(server, "/");

		sch.addEventListener(new GuiceConfig());
		sch.addFilter(GuiceFilter.class, "/*", null);
		sch.addServlet(DefaultServlet.class, "/");

		server.start();
		server.join();
	}
}


class GuiceConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				this.install(new RESTModule());

				Map<String, String> parameters = new HashMap<>();
				parameters.put("com.sun.jersey.config.property.packages", "com.example.sdJav.resources");
				parameters.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

				this.serve("/*").with(GuiceContainer.class, parameters);
			}
		});
	}
}


class RESTModule extends AbstractModule {
	@Override
	public void configure() {
		this.bind(DocumentManager.class).to(DocumentManagerImpl.class).asEagerSingleton();
	}
}


class DocumentManagerImpl implements DocumentManager {
	private HashMap<Integer, File> resources = new HashMap<Integer, File>();

	@Inject
	public DocumentManagerImpl() {
		for (int i = 1; i <= 3; i++) {
			File file = new File(UUID.randomUUID().toString() + ".txt");
			this.writeToFile(file, "UUID: " + UUID.randomUUID().toString());
		}
	}

	public synchronized HashMap<Integer, File> getResources() {
		return this.resources;
	}

	public synchronized void setResources(HashMap<Integer, File> resources) {
		this.resources = resources;
	}

	public synchronized void createResource(String content) {
		File file = new File(UUID.randomUUID().toString());
		this.writeToFile(file, content);
		this.resources.put(this.resources.size() + 1, file);
	}

	private void writeToFile(File file, String content) {
		try {
			FileWriter fw = new FileWriter(file.getPath());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
