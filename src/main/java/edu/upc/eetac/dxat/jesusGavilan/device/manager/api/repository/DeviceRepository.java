package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.repository;

import java.util.HashMap;
import java.util.Set;

import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Interface;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.InterfacesCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Router;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.RouterCollection;

public class DeviceRepository {
	private HashMap<String, Router> routersRepository;
	private static DeviceRepository instance;
	private DeviceRepository(){
		routersRepository= new HashMap<String, Router>();
		
		for(int i=0; i<100; i++){
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
	}
	public static DeviceRepository getInstance(){
		if(instance == null)
			instance = new DeviceRepository();
		return instance;
	}
	
	public void deleteRouter(String inventoryId){
		routersRepository.remove(inventoryId);
	}
	
	public Router getRouter(String inventoryId){
		return routersRepository.get(inventoryId);
	}
	
	public RouterCollection getRouters(){
		RouterCollection routers = new RouterCollection();
		Set<String> s = routersRepository.keySet();
		for(String id : s ){
			routers.addRouter(routersRepository.get(id));
		}
		return routers;
		
	}
	
	public Router insertRouter(Router router){
		routersRepository.put(router.getInventoryId(), router);
		return router;
	}
	
	public Router updateRouter(Router router){
		Router r = routersRepository.get(router.getInventoryId());
		r.setInterfaces(router.getInterfaces());
		return r;
	}
}
