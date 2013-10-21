package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceCollection {
	
	private List<Device> devices;
	
	public DeviceCollection (){
		devices = new ArrayList<Device>();
	}
	
	public void addDevice(Device d){
		devices.add(d);
	}
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

}
