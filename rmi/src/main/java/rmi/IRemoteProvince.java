package rmi;

import java.rmi.*;
import java.util.*;

public interface IRemoteProvince extends Remote {

  int save(Province p) throws RemoteException;

  int update(Province p) throws RemoteException;

  int delete(Province p) throws RemoteException;

  void deleteAll() throws RemoteException;

  ArrayList findAll() throws RemoteException;

  ArrayList findByName(String criteria) throws RemoteException;
}
