package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.HashMap;

public class Router extends Device {
	private HashMap<Interface, Interface> routingTable = null;

	public Router() {
		super();
		this.routingTable = new HashMap<Interface, Interface>();
	}

	public HashMap<Interface, Interface> getRoutingTable() {
		return routingTable;
	}

	public void setRoutingTable(HashMap<Interface, Interface> routingTable) {
		this.routingTable = routingTable;
	}  
	
	
	
}
