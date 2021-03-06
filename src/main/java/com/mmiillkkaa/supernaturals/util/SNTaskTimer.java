/*
 * Supernatural Players Plugin for Bukkit
 * Copyright (C) 2011  Matt Walker <mmw167@gmail.com>
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
 * 
 */

package com.mmiillkkaa.supernaturals.util;

import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
import com.mmiillkkaa.supernaturals.manager.SuperNManager;

public class SNTaskTimer implements Runnable {
	private SupernaturalsPlugin plugin;

	public SNTaskTimer(SupernaturalsPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		//Do you REALLY need to make a new scheduler for EVERY player? Why not one for ALL players?
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				plugin.getSuperManager().advanceTime(SuperNManager.findAllOnline());
			}
		});
	}
}
