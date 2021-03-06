/*
 * Copyright (C) 2014 cnaude
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cnaude.purpleirc;

import org.bukkit.scheduler.BukkitTask;

/**
 * This thread checks each for users and updates the
 * internal lists.
 * 
 * @author Chris Naude 
 */
public class ChannelWatcher {

    private final PurpleIRC plugin;
    private final BukkitTask bt;

    /**
     *
     * @param plugin the PurpleIRC plugin
     */
    public ChannelWatcher(final PurpleIRC plugin) {
        this.plugin = plugin;

        bt = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable() {
            @Override
            public void run() {
                for (PurpleBot ircBot : plugin.ircBots.values()) {
                    ircBot.updateNickList();
                }
            }
        }, plugin.ircChannelCheckInterval, plugin.ircChannelCheckInterval);
    }

    /**
     * Cancel the scheduled BukkitTask. Call this when
     * shutting down.
     */
    public void cancel() {
        bt.cancel();
    }
    
}
