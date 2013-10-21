package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.HashMap;


public class Switch extends Device{
	private HashMap<Interface, Interface> forwardingTable= null;
	
	public Switch(){
		super();
		this.forwardingTable = new HashMap<Interface, Interface>();
	}

	public HashMap<Interface, Interface> getForwardingTable() {
		return forwardingTable;
	}

	public void setForwardingTable(HashMap<Interface, Interface> forwardingTable) {
		this.forwardingTable = forwardingTable;
	}
	

}
