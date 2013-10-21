package edu.upc.eetac.dxat.jesusGavilan.device.manager.api.repository;

import java.util.HashMap;
import java.util.Set;

import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Device;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.DeviceCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.GenericCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Interface;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.InterfacesCollection;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Router;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.model.Switch;
import edu.upc.eetac.dxat.jesusGavilan.device.manager.api.utils.MACGenerator;

public class DeviceRepository {
	private HashMap<String, Device> devicesRepository;
	private static DeviceRepository instance;
	private DeviceRepository(){
		devicesRepository = new HashMap<String, Device>();
		
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
			devicesRepository.put(r.getInventoryId(), r);
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
			devicesRepository.put(sw.getInventoryId(), sw);
			
		}
	}
	public static DeviceRepository getInstance(){
		if(instance == null)
			instance = new DeviceRepository();
		return instance;
	}
	
	public void deleteDevice(String inventoryId){
		devicesRepository.remove(inventoryId);
	}
	
	public Device getDevice(String inventoryId){
		return devicesRepository.get(inventoryId);
	}
	
	public DeviceCollection getDevices(){
		DeviceCollection devices = new DeviceCollection();
		Set<String> s = devicesRepository.keySet();
		for(String id : s ){
			devices.addDevice(devicesRepository.get(id));
		}
		return devices;
	}
	
	public DeviceCollection getDevices(Class c){
		DeviceCollection devices = new DeviceCollection();
		Set<String> s = devicesRepository.keySet();
		for(String id : s ){
			Device d = devicesRepository.get(id);
			if (d.getClass()==c)
				devices.addDevice(d);
		}
		return devices;
	}
	
	public Device insertDevice(Device device){
		devicesRepository.put(device.getInventoryId(), device);
		return device;
	}
	
	public Device updateDevice(Device device){
		Device d = devicesRepository.get(device.getInventoryId());
		d.setInterfaces(device.getInterfaces());
		return d;
	}
	
}
