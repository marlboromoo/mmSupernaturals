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

package com.mmiillkkaa.supernaturals.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.mmiillkkaa.supernaturals.SuperNPlayer;
import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
import com.mmiillkkaa.supernaturals.manager.HunterManager;
import com.mmiillkkaa.supernaturals.manager.SuperNManager;
import com.mmiillkkaa.supernaturals.util.Language;

public class SNCommandKillList extends SNCommand {

    public SNCommandKillList() {
        requiredParameters = new ArrayList<String>();
        optionalParameters = new ArrayList<String>();
        senderMustBePlayer = true;
        permissions = "supernatural.command.killlist";
        helpNameAndParams = "convert [playername] [supernaturalType]";
        helpDescription = "Instantly turn a player into a supernatural.";
    }

    @Override
    public void perform() {

        Player senderPlayer = (Player) sender;
        SuperNPlayer snSender = SuperNManager.get(senderPlayer);
        if (!SupernaturalsPlugin.hasPermissions(senderPlayer, permissions)) {
            this.sendMessage(Language.NO_PREMISSION.toString());
            return;
        }

        if (!snSender.isHunter()) {
            this.sendMessage(Language.NOT_WITCHHUNTER.toString());
        }

        ArrayList<SuperNPlayer> bountyList = HunterManager.getBountyList();

        // Create Messages
        List<String> messages = new ArrayList<String>();
        messages.add(String.format("*** %s ***", Language.NOT_WITCHHUNTER));
        for (SuperNPlayer snplayer : bountyList) {
            messages.add(ChatColor.WHITE + snplayer.getName());
        }

        // Send them
        this.sendMessage(messages);
    }
}
