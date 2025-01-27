package plugin.enemyDown.command;

import java.util.SplittableRandom;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;
import plugin.enemyDown.Main;

public class EnemyDownCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (sender instanceof Player player) {
      World world = player.getWorld();

      player.setHealth(20);
      player.setFoodLevel(20);

      world.spawnEntity(getEnemySpawnLocation(player, world), EntityType.ZOMBIE);
      player.sendMessage("実行完了");
    }
    return false;
  }

  /**
   * 敵の出現エリアを取得します。 出現エリアはX,Z軸で、現在地から-10~9の値が設定されます。
   *
   * @param player コマンド実行者
   * @param world  コマンド実行者が所属するワールド
   * @return 敵の出現場所
   */
  private Location getEnemySpawnLocation(Player player, World world) {
    Location playerLocation = player.getLocation();
    int randomX = new SplittableRandom().nextInt(20) - 10;
    int randomZ = new SplittableRandom().nextInt(20) - 10;

    double x = playerLocation.getX() + randomX;
    double y = playerLocation.getY();
    double z = playerLocation.getZ() + randomZ;

    return new Location(world, x, y, z);
  }
}
