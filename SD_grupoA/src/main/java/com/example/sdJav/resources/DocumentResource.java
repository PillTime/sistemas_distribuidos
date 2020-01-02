package com.example.sdJav.resources;

import com.example.sdJav.DocumentManager;
import com.google.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


@Path("/resources")
public class DocumentResource {
    private final DocumentManager manager;

    @Inject
    public DocumentResource(DocumentManager manager) {
        this.manager = manager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap getResources() {
        return this.manager.getResources();
    }

    @POST
    public void createResource(String content) {
        System.out.println("Post called!");
        System.out.println("value > " + content + " <");
        this.manager.createResource(content);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResourceById(@PathParam("id") int id) throws IOException {
        File file = this.manager.getResources().get(id);
        if (file == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("File for ID " + id + " not found!").build();
        }
        else {
            return Response.ok(new String(Files.readAllBytes(Paths.get(this.manager.getResources().get(id).getPath()))), MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteResourceById(@PathParam("id") int id) throws IOException {
        if (this.manager.getResources().remove(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("File for ID " + id + " not found!").build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("seeders")
    @Produces(MediaType.APPLICATION_JSON)
    public void getSeeders() {
        System.out.println("Client asked for seeders.");
    }
}
