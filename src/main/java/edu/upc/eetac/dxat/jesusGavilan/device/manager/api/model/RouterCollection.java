package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.ArrayList;
import java.util.List;

public class RouterCollection {
	private List<Router> routers;
	public RouterCollection(){
		routers = new ArrayList<Router>();
	}
	public void addRouter(Router router){
		routers.add(router);
	}
	public List<Router> getRouters() {
		return routers;
	}
	public void setRouters(List<Router> routers) {
		this.routers = routers;
	}

}
