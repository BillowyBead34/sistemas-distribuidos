package rmi;

import java.math.*;
import java.rmi.registry.*;
import java.util.*;

public class ProvinceClient {

  public static void main(String[] args) {
    BigDecimal decimal;
    decimal.longValue();
    try {
      //Get reference to rmi registry server
      Registry registry = LocateRegistry.getRegistry("192.168.0.7");
      //Lookup server object
      IRemoteProvince rp = (IRemoteProvince) registry.lookup("Province");
      //Save province
      Province mid = new Province(1, "Mérida", "MID");
      Province ens = new Province(2, "Ensenada", "ENS");
      Province cdmx = new Province(3, "Ciudad de México", "CDMX");
      Province cam = new Province(4, "Campeches", "CAM"); //w
      Province mty = new Province(5, "Monterrey", "MTY");
      //Save province
      System.out.println("Saving provinces...");
      rp.save(mid);
      rp.save(ens);
      rp.save(cdmx);
      rp.save(cam);
      rp.save(mty);
      //Update province
      System.out.println("Update Campeches to Campeche");
      Province updatedCAM = new Province(4, "Campeche", "CAM");
      int iRet = rp.update(updatedCAM);
      //Display all provinces
      System.out.println("Display all provinces");
      ArrayList<Province> arrProv = rp.findAll();
      for (Province p : arrProv) {
        System.out.println(p.toString());
      }
      //Delete Campeche
      System.out.println("Delete CAM");
      rp.delete(cam);
      //Display province starts by "C"
      System.out.println("Display province starts by \"C\"");
      arrProv = rp.findByName("C");
      for (Province p : arrProv) {
        System.out.println(p.toString());
      }
      //Delete all provinces
      // System.out.println("Delete all provinces");
      // rp.deleteAll();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
