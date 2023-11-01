package fr.maefy.players;

import fr.maefy.teams.Team;

import java.util.UUID;

public class Player {
    String playerName;
    UUID playerUUID;

    public Player(String playerName, UUID playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    public String getName() {
        return playerName;
    }

    public UUID getUniqueId() {
        return playerUUID;
    }

    public static Player getPlayer(String playerName , UUID uuid){
        return new Player(playerName,uuid);
    }
}
