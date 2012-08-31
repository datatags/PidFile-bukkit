package net.servertube.pidfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;
import sun.management.ManagementFactory;

/**
 *
 * @author Sebastian "prodigy" G.
 */
public class PidFile extends JavaPlugin {

  @Override
  public void onEnable() {
    String pid = ManagementFactory.getRuntimeMXBean().getName();
    pid = pid.substring(0, pid.indexOf("@"));
    getLogger().info("Server PID: " + pid);
    FileWriter fw = null;
    BufferedWriter fwout = null;
    try {
      fw = new FileWriter("server.pid");
      fwout = new BufferedWriter(fw);
      fwout.write(pid);
      fwout.close();
      fw.close();
    } catch (IOException ex) {
      getLogger().log(Level.WARNING, "Error writing server.pid file", ex);
    } finally {
      fw = null;
      fwout = null;
    }
  }

  @Override
  public void onDisable() {
    new File("server.pid").delete();
  }
}
