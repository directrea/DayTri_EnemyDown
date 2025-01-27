package plugin.enemyDown;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.enemyDown.command.EnemyDownCommand;

public final class Main extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    // Plugin startup logic
    Bukkit.getPluginManager().registerEvents(this, this);
    Objects.requireNonNull(getCommand("enemyDown")).setExecutor(new EnemyDownCommand());
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
