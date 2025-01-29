package plugin.enemyDown.command;

import java.util.List;
import java.util.SplittableRandom;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class EnemyDownCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (sender instanceof Player player) {
      World world = player.getWorld();

      //プレイヤーの状態を初期化
      InitializePlayerEnemyDown(player);

      world.spawnEntity(getEnemySpawnLocation(player, world), getRandomEnemy());
      player.sendMessage("実行完了");
    }
    return false;
  }

  /**
   * プレイヤーの体力、空腹度を最大に。 鉄装備一式を装備する。
   *
   * @param player コマンド実行者
   */
  private static void InitializePlayerEnemyDown(Player player) {
    player.setHealth(20);
    player.setFoodLevel(20);
    PlayerInventory inventory = player.getInventory();
    inventory.setHelmet(ItemStack.of(Material.valueOf("NETHERITE_HELMET")));
    inventory.setChestplate(ItemStack.of(Material.NETHERITE_CHESTPLATE));
    inventory.setLeggings(ItemStack.of(Material.NETHERITE_LEGGINGS));
    inventory.setBoots(ItemStack.of(Material.NETHERITE_LEGGINGS));
    inventory.setItemInMainHand(ItemStack.of(Material.NETHERITE_SWORD));
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

  /**
   * 敵をランダムで抽選して、その結果を返します。
   *
   * @return 敵
   */
  private EntityType getRandomEnemy() {
    List<EntityType> entities = List.of(EntityType.ZOMBIE, EntityType.SKELETON);
    int randomEntity = new SplittableRandom().nextInt(entities.size());
    return entities.get(randomEntity);
  }
}

