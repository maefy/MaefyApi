package fr.maefy.account;

import fr.maefy.db.DBConnection;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Account {

    public static void createAccount(Player player , int renom , int elo ,boolean isStaff,DBConnection dbConnection){

        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO Account (name,uuid,renom,elo,is_staff) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1 , playerName);
            preparedStatement.setString(2 , uuid.toString());
            preparedStatement.setInt(3 , renom);
            preparedStatement.setInt(4 ,elo);
            preparedStatement.setBoolean(5 , isStaff);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void deleteAccount(Player player , DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("DELETE FROM Account WHERE uuid = ?");
            preparedStatement.setString(1 , uuid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public static void updateAccount(Player player , int renom , int elo , DBConnection dbConnection) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET renom = ?, elo = ? WHERE uuid = ?");

            preparedStatement.setInt(1, renom);
            preparedStatement.setInt(2, elo);
            preparedStatement.setString(3, uuid);

            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated > 0) {
                System.out.println("Mise à jour ok");
            } else {
                System.out.println("Erreur");
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean hasAccount(Player player , DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM Account WHERE uuid = ?");
            preparedStatement.setString(1 , uuid);

            ResultSet resultSet = preparedStatement.getResultSet();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setRenom(Player player , int renom , DBConnection dbConnection ){
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET renom = ? WHERE uuid = ?");
            preparedStatement.setInt(1 ,renom);
            preparedStatement.setString(2 , uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int getRenom(Player player , DBConnection dbConnection ){
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT renom FROM Account WHERE uuid = ?");
            preparedStatement.setString(1 , uuid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt("renom");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void addRenom(Player player , int renom , DBConnection dbConnection ){
        int newRenom = getRenom(player, dbConnection) + renom;
        setRenom(player, newRenom, dbConnection);
    }

    public static void removeRenom(Player player , int renom , DBConnection dbConnection ){
        int newRenom = getRenom(player, dbConnection) - renom;
        setRenom(player, newRenom, dbConnection);
    }

    public static void setElo(Player player , int elo , DBConnection dbConnection ){
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET elo = ? WHERE uuid = ?");
            preparedStatement.setInt(1 ,elo);
            preparedStatement.setString(2 , uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int getElo(Player player , DBConnection dbConnection ){
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT elo FROM Account WHERE uuid = ?");
            preparedStatement.setString(1 , uuid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt("elo");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void addElo(Player player , int elo , DBConnection dbConnection ){
        int newElo = getRenom(player, dbConnection) + elo;
        setElo(player, newElo, dbConnection);
    }

    public static void removeElo(Player player , int elo , DBConnection dbConnection ){
        int newElo = getRenom(player, dbConnection) - elo;
        setElo(player, newElo, dbConnection);
    }


    public static void setTimePlay(Player player , String timePlay , DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET timeplay = ? WHERE uuid = ?");
            preparedStatement.setString(1 ,timePlay);
            preparedStatement.setString(2 , uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setTimePlay(Player player , int timePlay , DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET timeplay = ? WHERE uuid = ?");
            preparedStatement.setString(1 ,String.valueOf(timePlay));
            preparedStatement.setString(2 , uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTimePlay(Player player , DBConnection dbConnection ){
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT timeplay FROM Account WHERE uuid = ?");
            preparedStatement.setString(1 , uuid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getString("timeplay");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static int getIntTimePlay(Player player , DBConnection dbConnection ){
        String timePlay = getTimePlay((Player) player, dbConnection);

        if (timePlay == null){
            return 0;
        }

        return Integer.parseInt(timePlay);

    }

    public static void addTimePlay(Player player , int timePlay , DBConnection dbConnection){
        String newTimePlay = String.valueOf(getIntTimePlay(player, dbConnection) + timePlay);
        setTimePlay(player , newTimePlay , dbConnection);
    }

    public static void removeTimePlay(Player player , int timePlay , DBConnection dbConnection){
        String newTimePlay = String.valueOf(getIntTimePlay(player, dbConnection) - timePlay);
        setTimePlay(player , newTimePlay , dbConnection);
    }

    public static boolean isStaff(Player player , DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT is_staff FROM Account WHERE uuid = ?");
            preparedStatement.setString(1 , uuid);

            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()){
                return resultSet.getBoolean("isStaff");
            }

            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setStaff(Player player , boolean isStaff,DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET is_staff = ? WHERE uuid = ?");
            preparedStatement.setBoolean(1 ,isStaff);
            preparedStatement.setString(2 , uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
