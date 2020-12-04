import org.jgroups.*;
import org.jgroups.blocks.*;
import org.jgroups.util.*;

public class Backend {

  private JChannel channel;
  private RpcDispatcher dispatcher;
  private RequestOptions options;

  public Backend() {

    try {
      this.channel = new JChannel();
      this.channel.connect(System.getenv("CHANNEL") == null ? "TEST_CHANNEL" : System.getenv("CHANNEL"));
      this.dispatcher = new RpcDispatcher(this.channel, this);
      this.options = new RequestOptions(ResponseMode.GET_ALL, 5000);
    } catch (Exception e) {
      System.out.println("!! Could not join JChannel... Exiting...");
      System.exit(1);
    }
  }

  public String ping() {
    System.out.printf("[%s] I've been pinged!\n", this.channel.getAddressAsString());
    return "pong";
  }

  public static void main(String []args) {
    System.out.println("Creating Example Backend Server...");
    Backend backendServer = new Backend();
    System.out.println("** Backend Server Created...");
  }

}