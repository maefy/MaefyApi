package fr.maefy.account;

import fr.maefy.api.API;
import fr.maefy.db.DBConnection;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

public class Methods {

    static Logger logger = API.logger;

    public static void createAccount(Player player , int renom , int elo , DBConnection dbConnection){

        String playerName = player.getName();
        UUID uuid = UUID.randomUUID();



    }

}
