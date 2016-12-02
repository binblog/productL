package com.dubbod.service;

import com.dubbod.bean.Blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Path("blog")
public interface BlogService {


    @GET
    @Path("{id : \\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    Blog getBlog(@PathParam("id") Long id);
}
