package com.dubbod.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Path(value = "echo")
public class EchoServiceImpl implements  EchoService{



    @GET
    @Path(value = "/{message}")
    public String echo(@PathParam("message") String message)
    {

        return message;
    }
}
