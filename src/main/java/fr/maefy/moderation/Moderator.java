package fr.maefy.moderation;

import fr.maefy.db.DBConnection;
import fr.maefy.player.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Moderator {

    // mettre à jour le nombre de sanctions
    public static void setSanctions(Player player, int sanctions, DBConnection dbConnection) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Account SET sanctions = ? WHERE uuid = ?");
            preparedStatement.setInt(1, sanctions);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSanctions(Player player, DBConnection dbConnection){
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT sanctions FROM Account WHERE uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("sanctions");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void setStaffStatus(Player player, String status, DBConnection dbConnection) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("REPLACE INTO staffStatues (uuid, name, status) VALUES (?, ?, ?)");
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, status);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStaffStatus(Player player, DBConnection dbConnection) {
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT status FROM staffStatus WHERE uuid = ?");
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch(SQLException e) {
            throw  new RuntimeException(e);
        }

        return null;
    }

}
