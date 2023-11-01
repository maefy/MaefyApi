package fr.maefy.teams;

import fr.maefy.players.Player;

import java.util.List;

public class Team {
    String name;
    Player owner;
    List<Player> players;

    public Team(String name, Player owner, List<Player> players) {
        this.name = name;
        this.owner = owner;
        this.players = players;
    }

    public void createTeam(){

    }

    public void removeTeam(){

    }

    public void changeOwner(Player player){

    }

    public void removePlayer(Player player){

    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int numberPlayers(){
        return players.size();
    }

    public static Team getTeam(String name){
        Team team = new Team(null , null , null);
        return team;
    }

    public static List<Player> getPlayerList(String name){

    }

    public static Player getTeamOwner(String name){



    }


}
