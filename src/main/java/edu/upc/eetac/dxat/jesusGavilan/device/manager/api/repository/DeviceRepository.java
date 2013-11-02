package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.repository;

import java.util.HashMap;
import java.util.Set;

import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Interface;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.InterfacesCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Router;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.RouterCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Switch;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.SwitchCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.utils.MACGenerator;

public class DeviceRepository {
	private HashMap<String, Router> routersRepository;
	private HashMap<String, Switch> switchesRepository;
	private static DeviceRepository instance;
	private DeviceRepository(){
		routersRepository  = new HashMap<String, Router>();
		switchesRepository = new HashMap<String, Switch>();
		
		for(int i=0; i<9; i++){
			Router r =  new Router();
			r.setInventoryId("RT-" + i);
			r.setPorts(1);
			InterfacesCollection ifcs = new InterfacesCollection();
			Interface ifc = new Interface();
			ifc.setIp("192.168.1." + (i+1));
			ifc.setMac(Integer.toString(200-i));
			ifcs.addInterface(ifc);
			ifc = new Interface();
			ifc.setIp("192.168.2." + (i+1));
			ifc.setMac(Integer.toString(200-i));
			ifcs.addInterface(ifc);
			r.setInterfaces(ifcs);
			routersRepository.put(r.getInventoryId(), r);
		}
		for(int i=0;i<25;i++){
			Switch sw = new Switch();
			sw.setInventoryId("SW-" + i);
			sw.setPorts(2);
			InterfacesCollection ifcs = new InterfacesCollection();
			Interface ifc = new Interface();
			ifc.setMac(MACGenerator.create("SW-" + Integer.toString(i) + "a"));
			ifcs.addInterface(ifc);
			ifc = new Interface();
			ifc.setMac(MACGenerator.create("SW-" + Integer.toString(i) + "b"));
			ifcs.addInterface(ifc);
			sw.setInterfaces(ifcs);
			switchesRepository.put(sw.getInventoryId(), sw);
			
		}
	}
	public static DeviceRepository getInstance(){
		if(instance == null)
			instance = new DeviceRepository();
		return instance;
	}
	
	public void deleteRouter(String inventoryId){
		routersRepository.remove(inventoryId);
	}
	
	public void deleteSwitch(String inventoryId){
		switchesRepository.remove(inventoryId);
	}
	
	public Router getRouter(String inventoryId){
		return routersRepository.get(inventoryId);
	}
	
	public Switch getSwitch(String inventoryId){
		return switchesRepository.get(inventoryId);
	}
	
	public RouterCollection getRouters(){
		RouterCollection routers = new RouterCollection();
		Set<String> s = routersRepository.keySet();
		for(String id : s ){
			routers.addRouter(routersRepository.get(id));
		}
		return routers;
		
	}
	
	public SwitchCollection getSwitches(){
		SwitchCollection switches = new SwitchCollection();
		Set<String> sw = switchesRepository.keySet();
		for(String id : sw){
			switches.addSwitch(switchesRepository.get(id));
		}
		return switches;
	}
	
	public Router insertRouter(Router router){
		routersRepository.put(router.getInventoryId(), router);
		return router;
	}
	
	public Switch inserSwitch(Switch sw){
		switchesRepository.put(sw.getInventoryId(), sw);
		return sw;
	}
	
	public Router updateRouter(Router router){
		Router r = routersRepository.get(router.getInventoryId());
		r.setInterfaces(router.getInterfaces());
		return r;
	}
	
	public Switch updateSwitch(Switch swtch){
		Switch sw = switchesRepository.get(swtch.getInventoryId());
		sw.setInterfaces(swtch.getInterfaces());
		return sw;
	}
	
}
