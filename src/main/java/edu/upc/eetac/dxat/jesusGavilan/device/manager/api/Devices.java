package edu.upc.eetac.dxat.jesusGavilan.device.manager.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Router;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.RouterCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.repository.DeviceRepository;

@Path("/devices")
public class Devices {
	
	public DeviceRepository repo = DeviceRepository.getInstance();
	
	@GET
	@Path("/routers")
	@Produces(MediaType.ROUTERS_COLLECTION)
	public RouterCollection getRouters() {
		return repo.getRouters();
	}
	@GET
	@Path("/routers/{inventoryId}")
	@Produces(MediaType.ROUTER)
	public Router getRouter(@PathParam("inventoryId") String inventoryId){
		return repo.getRouter(inventoryId);
	}
	@POST
	@Path("/routers")
	@Consumes(MediaType.ROUTER)
	@Produces(MediaType.ROUTER)
	public Router insertRouter(Router router){
		repo.insertRouter(router);
		return router;
	}
	@DELETE
	@Path("/routers/{inventoryId}")
	public void deleteRouter(@PathParam("inventoryId") String inventoryId){
		repo.deleteRouter(inventoryId);
	}
	@PUT
	@Path("/routers/{inventoryId}")
	@Consumes(MediaType.ROUTER)
	@Produces(MediaType.ROUTER)
	public Router updateRouter(Router router){
		return repo.updateRouter(router);
	}
	
}
