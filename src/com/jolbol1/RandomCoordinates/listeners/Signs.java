package com.jolbol1.RandomCoordinates.listeners;

import com.jolbol1.RandomCoordinates.RandomCoords;
import com.jolbol1.RandomCoordinates.managers.Coordinates;
import com.jolbol1.RandomCoordinates.managers.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Created by James on 04/07/2016.
 */
public class Signs implements Listener {

    private final MessageManager messages = new MessageManager();
    final Coordinates coordinates = new Coordinates();

    @EventHandler
    public void onSignChange(final SignChangeEvent e) {
        if (e.getLine(0) == null) {
            return;
        }
        if (!(e.getLine(0).equalsIgnoreCase("[RandomCoords]"))) {
            return;
        }
        if(RandomCoords.getPlugin().hasPermission(e.getPlayer(), "Random.Admin.Sign") || RandomCoords.getPlugin().hasPermission(e.getPlayer(), "Random.Admin.*") || RandomCoords.getPlugin().hasPermission(e.getPlayer(), "Random.*")) {
            if (e.getLine(1).length() == 0 || e.getLine(1) == null) {
                e.setLine(0, ChatColor.GREEN + "[RandomCoords]");
                messages.signCreated(e.getPlayer());
            } else if(e.getLine(2).length() == 0 || e.getLine(2) == null) {
                final String wName = e.getLine(1).replaceAll("\uF701", "");
                if (wName.length() == 0 || wName.equals("")) {
                    return;
                }
                if (e.getPlayer().getServer().getWorld(wName) == null) {
                    messages.invalidWorld(e.getPlayer(), wName);
                } else {
                    e.setLine(0, ChatColor.GREEN + "[RandomCoords]");
                    messages.signCreated(e.getPlayer());
                }
            } else {
                e.setLine(0, ChatColor.GREEN + "[RandomCoords]");
                messages.signCreated(e.getPlayer());
            }
        } else {
            messages.noPermission(e.getPlayer());

        }


    }


}
