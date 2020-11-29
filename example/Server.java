import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

import org.jgroups.*;
import org.jgroups.blocks.*;
import org.jgroups.util.*;

public class Server 
extends UnicastRemoteObject 
implements ServerInterface {

  public Server() throws RemoteException {

    // Join JChannel
    try {
      JChannel channel = new JChannel();
      channel.connect("EXAMPLE_CHANNEL");
    } catch (Exception e) {
      System.out.println("!! Could not join JChannel");
    }
  }

  public String ping() throws RemoteException {
    return "pong";
  }

  public static void main(String []args) {
    System.out.println("Creating Example Server...");

    Server server = null;
    Registry rmiRegistry = null;

    
    try {
      // Create Server Instance
      server = new Server();
    } catch (RemoteException re) {
      System.out.println("!! Failed to create a server instance");
      System.exit(1);
    }

    // Create Registry and Bind
    try {
      rmiRegistry = LocateRegistry.createRegistry(1099); 
    } catch(Exception e) {
      System.out.println("!! Failed to create a registry on port 1099");
      System.exit(1);
    }

    // Bind to Registry
    try{
      // (Server)UnicastRemoteObject.exportObject(server, 0)
      rmiRegistry.bind("example_server", server);
    } catch(Exception e) {
      System.out.println("!! Failed to bind server stub to registry");
      System.exit(1);
    }

    System.out.println("** Now bound to RMI Registry...");

  }

}