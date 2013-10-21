package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.ArrayList;
import java.util.List;

public class SwitchCollection {
	
	private List<Switch> devices;
	
	public SwitchCollection (){
		devices = new ArrayList<Switch>();
	}
	
	public void addSwitch(Switch d){
		devices.add(d);
	}
	
	public List<Switch> getSwitchs() {
		return devices;
	}

	public void setSwitchs(List<Switch> devices) {
		this.devices = devices;
	}

}
