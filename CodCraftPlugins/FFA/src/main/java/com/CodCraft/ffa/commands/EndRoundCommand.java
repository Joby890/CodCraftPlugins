package com.CodCraft.ffa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.CodCraft.api.modules.GameManager;
import com.CodCraft.ffa.CodCraft;

public class EndRoundCommand implements CommandExecutor {
   private CodCraft plugin;

   public EndRoundCommand(CodCraft plugin) {
      this.plugin = plugin;
   }

   @Override
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if(label.equalsIgnoreCase("endround") && args.length == 0) {
    	 plugin.getGame().game.deinitialize();
         plugin.getGame().Savedata();
      }
      return false;
   }
}
