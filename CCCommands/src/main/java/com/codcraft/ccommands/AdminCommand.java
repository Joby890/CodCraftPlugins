package com.codcraft.ccommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.CodCraft.api.event.RequestJoinGameEvent;
import com.CodCraft.api.model.Game;
import com.CodCraft.api.model.Team;
import com.CodCraft.api.model.TeamPlayer;
import com.CodCraft.api.modules.GameManager;
import com.CodCraft.api.services.CCGamePlugin;
import com.codcraft.codcraftplayer.CCPlayer;
import com.codcraft.codcraftplayer.CCPlayerModule;

public class AdminCommand implements CommandExecutor {
	private CCCommands plugin;
	
	public AdminCommand(CCCommands plugin) {
		this.plugin = plugin;
	}

	@Override
	 public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("a")) {
			if(args.length == 0) {
				showhelp(sender);
				return true;
			}
			final GameManager gm = plugin.api.getModuleForClass(GameManager.class);
			if(args[0].equalsIgnoreCase("joingame")) {
				String g = args[1];
				for(Game<?> game : gm.getAllGames()) {
					if(game.getName().equalsIgnoreCase(g)) {
						RequestJoinGameEvent event = new RequestJoinGameEvent(new TeamPlayer(sender.getName()), game, game.getTeams().get(0));
						Bukkit.getPluginManager().callEvent(event);
					}
				}
			}
			
			
			if(args[0].equalsIgnoreCase("showplugins")) {
				Player p = (Player) sender;
				p.sendMessage(getPluginList());
				return true;
			}
		
			
			if(args[0].equalsIgnoreCase("setteam")) {
				Player p = (Player) sender;
				Game<?> g = gm.getGameWithPlayer((Player)sender);
				Team team = g.findTeamWithName(args[1]);
				if(team == null) {
					p.sendMessage(args[1] + " is not a current team!");
				} else {
					g.findTeamWithPlayer(p).removePlayer(p);
					g.findTeamWithName(args[1]).addPlayer(p);
					p.sendMessage("You are now on "+ g.findTeamWithPlayer(p).getName());
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("endallgames")) {
				for(Game<?> g : gm.getAllGames()) {
					gm.deregisterGame(g);
					sender.sendMessage(""+g.getName()+" has ended.");
				}
				
				return true;
				
			}
			
			if(args[0].equalsIgnoreCase("setClasses")) {
				String player = args[1];
				String ammount = args[2];
				CCPlayer player1 = plugin.api.getModuleForClass(CCPlayerModule.class).getPlayer(player);
				if(player1 != null) {
					player1.setCaCint(Integer.parseInt(ammount));
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("listgames")) {
				for(Game<?> g : gm.getAllGames()) {
					sender.sendMessage(g.getName());
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("getBuddies")) {
				//for(String s : bud.getBuddys(new Buddies(args[1]))) {
				//	sender.sendMessage(s);
				//}
			}
			if(args[0].equalsIgnoreCase("restartallgames")) {
				final ArrayList<Game<?>> games = new ArrayList<>();
				for(Game<?> g : gm.getAllGames()) {
					games.add(g);
					gm.deregisterGame(g);
				}
				
				sender.sendMessage(""+games.size()+" has ended.");
				Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				final ArrayList<Game<?>> startedgames = new ArrayList<>();
					@Override
					public void run() {
						for(Game<?> g : games) {
							gm.registerGame(g);
							startedgames.add(g);
						}
						
					}
				}, 20L);
				sender.sendMessage(""+games.size()+" has started.");

			}
			
			if(args[0].equalsIgnoreCase("map")) {
				if(Bukkit.getWorld(args[1]) == null) {
					Bukkit.createWorld(new WorldCreator(args[1]));
					sender.sendMessage("Starting Map");
				}
				((Player)sender).teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
				
			}
			
			if(args[0].equalsIgnoreCase("listmaps")) {
				for(World w : Bukkit.getWorlds()) {
					sender.sendMessage(w.getName());
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("reloadplugin")) {
				if(args[1] != null) {
					Plugin plug = Bukkit.getPluginManager().getPlugin(args[1]);
					if(plug != null) {
						//Disable Plugin
						Bukkit.getPluginManager().disablePlugin(plug);
						//Enable Plugin
						Bukkit.getPluginManager().enablePlugin(plug);
					}
				}
			}
			
			
			if(args[0].equalsIgnoreCase("kills")) {
				if(args.length != 3) {
					return false;
				}
				CCPlayer player = plugin.api.getModuleForClass(CCPlayerModule.class).getPlayer((Player) sender);
				if(args[1].equalsIgnoreCase("add")) {
					player.setKills(player.getKills() + Integer.parseInt(args[2]));
					sender.sendMessage("Your kills are now "+ player.getKills());
					return true;
				} else if (args[1].equalsIgnoreCase("minus")) {
					player.setKills(player.getKills() - Integer.parseInt(args[2]));
					sender.sendMessage("Your kills are now "+ player.getKills());
					return true;
				}
			}
			
			
			if(args[0].equalsIgnoreCase("endgame")) {
				if(args.length == 1) {
					showhelp(sender);
					return true;
				} else if(args.length == 2) {
					
					Game<?> game = null; 
					for(Game<?> g : gm.getAllGames()) {
						if(g.getName().equalsIgnoreCase(args[1])) {
							game = g;
						}
					}
					if(game == null) {
						sender.sendMessage(args[1]+" is not an active game");
						return true;
					}
					gm.deregisterGame(game);
					
					sender.sendMessage(""+game.getName()+" has ended.");
					return true;
				}
			} else if(args[0].equalsIgnoreCase("startgame")) {
				Plugin plug = Bukkit.getPluginManager().getPlugin(args[1]);
				if(plug instanceof CCGamePlugin) {
					String[] args1 = {args[2]};
					((CCGamePlugin)plug).makeGame(args1);
					
				}
				return true;
			}
		}
		return false;
	}
	private void showhelp(CommandSender sender) {
		sender.sendMessage("Commands");
		sender.sendMessage("setTeam");
		
	}
	
    private String getPluginList() {
        StringBuilder pluginList = new StringBuilder();
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();

        for (Plugin plugin : plugins) {
            if (pluginList.length() > 0) {
                pluginList.append(ChatColor.WHITE);
                pluginList.append(", ");
            }

            pluginList.append(plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED);
            pluginList.append(plugin.getDescription().getName());
            pluginList.append(" ");
            pluginList.append(plugin.getDescription().getVersion());
        }

        return "(" + plugins.length + "): " + pluginList.toString();
    }

}
