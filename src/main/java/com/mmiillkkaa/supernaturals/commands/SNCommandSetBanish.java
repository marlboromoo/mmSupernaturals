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

import org.bukkit.entity.Player;

import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
import com.mmiillkkaa.supernaturals.util.Language;

public class SNCommandSetBanish extends SNCommand {

    public SNCommandSetBanish() {
        super();
        requiredParameters = new ArrayList<String>();
        optionalParameters = new ArrayList<String>();
        senderMustBePlayer = true;
        senderMustBeSupernatural = true;
        permissions = "supernatural.admin.command.setbanish";
        helpNameAndParams = "";
        helpDescription = "Sets the current location as the priests' banish location";
    }

    @Override
    public void perform() {

        Player senderPlayer = (Player) sender;
        if (!SupernaturalsPlugin.hasPermissions(senderPlayer, permissions)) {
            this.sendMessage(Language.NO_PREMISSION.toString());
            return;
        }

        double currentX = senderPlayer.getLocation().getX();
        double currentY = senderPlayer.getLocation().getY();
        double currentZ = senderPlayer.getLocation().getZ();

        SNConfigHandler.priestBanishWorld = senderPlayer.getWorld().getName();
        SNConfigHandler.priestBanishLocationX = (int) currentX;
        SNConfigHandler.priestBanishLocationY = (int) currentY;
        SNConfigHandler.priestBanishLocationZ = (int) currentZ;

        SNConfigHandler.priestBanishLocation = senderPlayer.getLocation();

        SNConfigHandler.getConfig().set("Priest.Banish.World",
                SNConfigHandler.priestBanishWorld);
        SNConfigHandler.getConfig().set("Priest.Banish.Location.X",
                SNConfigHandler.priestBanishLocationX);
        SNConfigHandler.getConfig().set("Priest.Banish.Location.Y",
                SNConfigHandler.priestBanishLocationY);
        SNConfigHandler.getConfig().set("Priest.Banish.Location.Z",
                SNConfigHandler.priestBanishLocationZ);

        SNConfigHandler.saveConfig();

        this.sendMessage(Language.BANISH_SET.toString());
    }
}
