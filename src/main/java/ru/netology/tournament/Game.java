package ru.netology.tournament;

import ru.netology.exceptions.NotRegisteredException;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> registeredPlayers = new ArrayList<>();

    public void register(Player p) {
        Player found = findById(p.getId());
        if (found == null) {
            registeredPlayers.add(p);
        }
    }

    public int round(String playerName1, String playerName2) {
        Player p1 = findByName(playerName1);
        Player p2 = findByName(playerName2);

        if (p1 == null) {
            throw new NotRegisteredException("Player " + playerName1 + " is not registered");
        }

        if (p2 == null) {
            throw new NotRegisteredException("Player " + playerName2 + " is not registered");
        }

        if (p1.getStrength() > p2.getStrength()) {
            return 1;//first wins
        }

        if (p1.getStrength() == p2.getStrength()) {
            return 0;//tie
        }

        return 2;//second wins
    }

    public Player[] findAllRegistered() {
        Player[] temp = new Player[0];
        return registeredPlayers.toArray(temp);
    }

    private Player findById(int idToFind) {
        for (Player currPlayer : registeredPlayers) {
            if (currPlayer.getId() == idToFind) {
                return currPlayer;
            }
        }
        return null;
    }

    private Player findByName(String nameToFind) {
        for (Player currPlayer : registeredPlayers) {
            if (currPlayer.getName().equalsIgnoreCase(nameToFind)) {
                return currPlayer;
            }
        }
        return null;
    }
}
