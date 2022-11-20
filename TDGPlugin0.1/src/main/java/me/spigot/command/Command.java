package me.spigot.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Command extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup login
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[" + ChatColor.GREEN + "TDGPlugin" + ChatColor.RED + "]" +ChatColor.GREEN + " Plugin Enable");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.GREEN + "สวัสดี" + ChatColor.YELLOW + player.getName());
        } else {
            player.sendMessage(ChatColor.GREEN + "สวัสดี" + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "ยินดีต้อนรับเข้าสู่เซิฟเวอร์ The Dark Gamer");
        }
    }

    @Override
    public  boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("freeitem")) {
                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED + "กรุณาพิมพ์คำสั่งให้ครบ");
                } else if (args.length == 1) {
                    Material material = Material.getMaterial(args[0].toUpperCase());
                    player.getInventory().addItem(new ItemStack(material, 1));
                    player.sendMessage(ChatColor.RED + "[" + ChatColor.GREEN + "TDGPlugin" + ChatColor.RED + "]" + ChatColor.GRAY + "ได้รับ" + args[0] + " 1 ชิ้น");
                }

            } else if ((command.getName().equalsIgnoreCase("heal"))) {
                if(args.length < 1){
                    player.sendMessage(ChatColor.RED+" กรุณาพิมพ์คำสั่งให้ครบ ");
                } else if (args.length == 1) {
                    double dnumber = player.getHealth();
                    double dvalue = Double.parseDouble(args[0]);
                    if (player.getHealth() < 20) {
                        player.setHealth(dnumber + dvalue);
                        player.sendMessage(ChatColor.GREEN + "ฮีลจำนวน " + args[0]);
                    } else {
                        player.sendMessage("ไม่ตรงเงื่อนไข");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "ไม่มีคำสั่งนี้");
            }
        }
        return true;
    }
    @Override
    public void onDisable() {
        {
            // Plugin shutdown logic
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[" + ChatColor.GREEN + "TDGPlugin" + ChatColor.RED + "]" +ChatColor.RED + " Plugin Disable");
        }
    }
}
