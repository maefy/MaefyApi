package fr.maefy.account;

import fr.maefy.api.API;
import fr.maefy.db.DBConnection;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.*;
import java.util.logging.Logger;

public class Methods {

    static Logger logger = API.logger;

    public static void createAccount(Player player , int renom , int elo , DBConnection dbConnection){

        Jedis jedis = dbConnection.getJedis();
        String playerName = player.getName();
        UUID uuid = player.getUniqueId();


        Map<String , String> hash = new HashMap<>();
        hash.put("name",playerName);
        hash.put("uuid",String.valueOf(uuid));
        hash.put("renom",String.valueOf(renom));
        hash.put("elo",String.valueOf(elo));

        jedis.hmset("joueurs:" + uuid, hash);

        try {
            // Envoi des données du joueur à Redis dans la table "joueurs"
            jedis.hmset("joueurs:" + uuid , hash);
            logger.info("Données du joueur ajoutées avec succès.");
        } catch (JedisDataException e) {
            logger.info("Erreur : " + e.getMessage());
        } finally {
            // Fermer la connexion à Redis dans le bloc finally pour garantir la fermeture de la connexion
            jedis.close();
        }
    }

}
