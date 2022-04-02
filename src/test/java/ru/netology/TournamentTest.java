package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.exceptions.NotRegisteredException;
import ru.netology.tournament.Game;
import ru.netology.tournament.Player;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentTest {
    Game gm = new Game();
    Player p1 = new Player(1000, "Santa", 100);
    Player p2 = new Player(1001, "Grumpy", 80);
    Player p3 = new Player(1002, "Psycho", 120);
    Player p4 = new Player(1003, "Maniac", 120);
    Player p5 = new Player(1004, "Necromancer", 999);
    Player p6 = new Player(1005, "Ant Agonizer", 500);

    @Test
    public void shouldRegisterUnique() {
        gm.register(p1);
        gm.register(p2);
        gm.register(p3);
        gm.register(p4);
        gm.register(p5);
        gm.register(p6);

        Player[] expected = new Player[]{p1, p2, p3, p4, p5, p6};
        Player[] actual = gm.findAllRegistered();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRegisterOnlyOnce() {
        gm.register(p1);
        gm.register(p1);
        gm.register(p2);

        Player[] expected = new Player[]{p1, p2};
        Player[] actual = gm.findAllRegistered();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFirstWin() {
        gm.register(p1);
        gm.register(p2);

        int result = gm.round("Santa", "Grumpy");

        assertEquals(1, result);
    }

    @Test
    public void shouldSecondWin() {
        gm.register(p1);
        gm.register(p5);

        int result = gm.round("Santa", "Necromancer");

        assertEquals(2, result);
    }

    @Test
    public void shouldTie() {
        gm.register(p3);
        gm.register(p4);

        int result = gm.round("Psycho", "Maniac");
        assertEquals(0, result);
    }

    @Test
    public void shouldThrowFirstNotRegistered() {
        gm.register(p4);
        assertThrows(NotRegisteredException.class, () -> gm.round("Psycho", "Maniac"));
    }

    @Test
    public void shouldThrowSecondNotRegistered() {
        gm.register(p3);
        assertThrows(NotRegisteredException.class, () -> gm.round("Psycho", "Maniac"));
    }
}
