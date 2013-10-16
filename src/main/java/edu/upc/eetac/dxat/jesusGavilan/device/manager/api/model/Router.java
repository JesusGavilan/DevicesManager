package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

public class Router {
	private String inventoryId;
	private int ports;
	private InterfacesCollection interfaces;
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getPorts() {
		return ports;
	}
	public void setPorts(int ports) {
		this.ports = ports;
	}
	public InterfacesCollection getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(InterfacesCollection interfaces) {
		this.interfaces = interfaces;
	}
	
	
}
