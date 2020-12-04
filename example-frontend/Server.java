import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.HashMap;

import org.jgroups.*;
import org.jgroups.blocks.*;
import org.jgroups.util.*;

public class Server 
extends UnicastRemoteObject 
implements ServerInterface {

  private JChannel channel;
  private RpcDispatcher dispatcher;
  private RequestOptions options;

  public Server()  throws RemoteException {

    // Join JChannel
    try {
      this.channel = new JChannel();
      channel.connect(System.getenv("CHANNEL") == null ? "TEST_CHANNEL" : System.getenv("CHANNEL"));
      this.channel.setDiscardOwnMessages(true);
      this.dispatcher = new RpcDispatcher(this.channel, null);
      this.options = new RequestOptions(ResponseMode.GET_ALL, 1000);
    } catch (Exception e) {
      System.out.println("!! Could not join JChannel");
      System.exit(1);
    }

    while (true) {
      System.out.println("\n\n? Just press Enter to run tests on all replicas!");
      try {
        System.in.read(); // press return key

        //Ping Test [rpc dispatcher]
        System.out.printf("** Pinging Replicas -> -> -> \n");
        this.ping();

      } catch(Exception e) {
        System.out.println("!! Ping Failed");
        e.printStackTrace();
      }
    }

  }

  public String ping() throws RemoteException {
    try {
      RspList<String> responses = this.dispatcher.callRemoteMethods(null, "ping", new Object[]{}, new Class<?>[]{}, this.options);
      System.out.printf("Responses: %s\n", responses.size());
      System.out.println(responses.getResults());
    } catch (Exception e) {
      System.out.println("!! Remote Procedure Call Failed");
      e.printStackTrace();
    }
    
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
      rmiRegistry.bind("example_server", server);
    } catch(Exception e) {
      System.out.println("!! Failed to bind server stub to registry");
      System.exit(1);
    }

    System.out.println("** Now bound to RMI Registry...");

  }

}