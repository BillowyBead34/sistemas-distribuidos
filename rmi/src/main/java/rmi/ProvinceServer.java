package rmi;

import java.rmi.registry.*;

public class ProvinceServer {

  public static void main(String[] args) {
    try {
      //Create and get reference to rmi registry
      Registry registry = LocateRegistry.createRegistry(1099);
      //Instantiate server object
      ProvinceObject po = new ProvinceObject();
      //Register server object
      registry.rebind("Province", po);
      System.out.println("ProvinceServer is created!!!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
