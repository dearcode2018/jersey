/**
  * @filename ItemResource.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.controller;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

 /**
 * @type ItemResource
 * @description 
 * @author qye.zheng
 */
@Path("/item")
public class ItemResource
{
	@Context UriInfo uriInfo;
	 
    @Path("content")
    public ItemContentResource getItemContentResource() {
        return new ItemContentResource();
    }
 
    	@GET
    	@Produces("application/xml")
        public String get() { return null;}
    }
class ItemContentResource {
	 
    @GET
    public Response get() {  return null; }
 
    @PUT
    @Path("{version}")
    public void put(@PathParam("version") int version,
                    @Context HttpHeaders headers,
                    byte[] in) {
    }
}
