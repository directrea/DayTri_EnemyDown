package plugin.enemyDown;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.enemyDown.command.EnemyDownCommand;

public final class Main extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    // Plugin startup logic
    Bukkit.getPluginManager().registerEvents(this, this);
    getCommand("enemyDown").setExecutor(new EnemyDownCommand(this));
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
