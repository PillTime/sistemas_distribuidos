package pt.up.fc.dcc.pdm.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.up.fc.dcc.pdm.core.KK;

import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Path("/resources")
public class DocumentResource {
	private final KK echo;

	@Inject
	public DocumentResource(KK k) {
		this.echo = k;
	}

	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response send(String content) {
		System.out.println("Received " + content + "\nSending it back!");
		return Response.ok(content + '\n').build();
	}
}