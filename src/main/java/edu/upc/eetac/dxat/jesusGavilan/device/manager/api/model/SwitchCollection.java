package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model;

import java.util.ArrayList;
import java.util.List;

public class SwitchCollection {
	
	private List<Switch> switches;
	
	public SwitchCollection(){
		switches = new ArrayList<Switch>();
	}
	
	public void addSwitch(Switch sw){
		switches.add(sw);
	}
	
	public List<Switch> getSwitches() {
		return switches;
	}

	public void setSwitches(List<Switch> switches) {
		this.switches = switches;
	}

}
