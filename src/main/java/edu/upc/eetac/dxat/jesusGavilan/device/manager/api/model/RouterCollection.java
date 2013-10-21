package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.ArrayList;
import java.util.List;

public class RouterCollection {
	
	private List<Router> devices;
	
	public RouterCollection (){
		devices = new ArrayList<Router>();
	}
	
	public void addRouter(Router d){
		devices.add(d);
	}
	
	public List<Router> getRouters() {
		return devices;
	}

	public void setRouters(List<Router> devices) {
		this.devices = devices;
	}

}
