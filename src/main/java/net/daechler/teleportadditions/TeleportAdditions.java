package net.daechler.teleportadditions;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TeleportAdditions extends JavaPlugin {

    // Fired when the plugin is first enabled
    @Override
    public void onEnable() {
        this.getLogger().info(ChatColor.GREEN + getName() + " has been enabled!");
    }

    // Fired when the plugin is disabled
    @Override
    public void onDisable() {
        this.getLogger().info(ChatColor.RED + getName() + " has been disabled!");
    }

    // Command handling
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Command to teleport to the center of the current block
            if (cmd.getName().equalsIgnoreCase("center")) {
                Location currentLocation = player.getLocation();
                Location centerLocation = new Location(currentLocation.getWorld(),
                        Math.floor(currentLocation.getX()) + 0.5,
                        currentLocation.getY(),
                        Math.floor(currentLocation.getZ()) + 0.5,
                        currentLocation.getYaw(),
                        currentLocation.getPitch());
                player.teleport(centerLocation);
                player.sendMessage(ChatColor.YELLOW + "Teleported to the center of the block.");
                return true;
            }

            // Command to make the player look straight forward
            if (cmd.getName().equalsIgnoreCase("forward")) {
                Location currentLocation = player.getLocation();
                player.teleport(new Location(currentLocation.getWorld(),
                        currentLocation.getX(),
                        currentLocation.getY(),
                        currentLocation.getZ(),
                        Math.round(currentLocation.getYaw() / 90) * 90,
                        0));
                player.sendMessage(ChatColor.YELLOW + "Now looking straight forward.");
                return true;
            }

            // Command to combine teleportation and straight forward look
            if (cmd.getName().equalsIgnoreCase("centerforward")) {
                Location currentLocation = player.getLocation();
                Location newLocation = new Location(currentLocation.getWorld(),
                        Math.floor(currentLocation.getX()) + 0.5,
                        currentLocation.getY(),
                        Math.floor(currentLocation.getZ()) + 0.5,
                        Math.round(currentLocation.getYaw() / 90) * 90,
                        0);
                player.teleport(newLocation);
                player.sendMessage(ChatColor.YELLOW + "Teleported and looking straight forward.");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
        }
        return false;
    }
}
