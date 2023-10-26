package fr.maefy.moderation;

import fr.maefy.db.DBConnection;
import fr.maefy.player.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reports {

    public static void createReport(Player player, String playerReported, String reason, String description, DBConnection dbConnection) {

        String playerName = player.getName();
        UUID uuid = player.getUniqueId();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO Reports (player,uuid,reason,description,playerReported) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.setString(3, reason);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, playerReported);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void removeReport(Player player, String playerReported, String reason, String description, DBConnection dbConnection) {
        String playerName = player.getName();
        String uuid = player.getUniqueId().toString();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("DELETE FROM Reports WHERE player = ? && uuid = ? && reason = ? && description = ? && playerReported = ?");
            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, uuid);
            preparedStatement.setString(3, reason);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, playerReported);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setResolved(Player player, boolean isResolved,String playerReported, String reason, String description, DBConnection dbConnection) {
        String playerName = player.getName();
        String uuid = player.getUniqueId().toString();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE Reports SET is_Resolved = ? WHERE player = ? && uuid = ? && reason = ? && description = ? && playerReported = ?");
            preparedStatement.setBoolean(1,isResolved);
            preparedStatement.setString(2, playerName);
            preparedStatement.setString(3, uuid);
            preparedStatement.setString(4, reason);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, playerReported);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*public static List<String> getPlayerReportsReason(Player player, DBConnection dbConnection) {
        String playerName = player.getName();
        String uuid = player.getUniqueId().toString();
        List<String> reasons = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT reason FROM Reports WHERE player = ? && uuid = ?");
            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, uuid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                reasons.add(resultSet.getString("reason"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reasons;

    }*/

}
