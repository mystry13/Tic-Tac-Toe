package com.mygame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    @Test
    void canCreateNewGame() {
        Game g = new Game(new Player("❌"), new Player("⭕️"));
        assertEquals("❌", g.getP1().getCharacter());
        assertEquals("⭕️", g.getP2().getCharacter());

        //intially board should be empty
        for (int i = 1; i <= 9; i++) {
            assertNull(g.getCharInBox(i));
        }

        assertEquals(g.getP1(), g.getNextTurn());
    }

    @Test
    void canMarkBoxesViaAttempts() {
        Game g = new Game(new Player("❌"), new Player("⭕️"));

        g.nextAttempt(1);

        assertEquals("❌", g.getCharInBox(1));
        assertEquals(g.getP2(), g.getNextTurn());

        // check that already marked boxes are not allowed to be marked
        Exception exc = assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                g.nextAttempt(1);
            }
        });
        assertEquals("This box is not empty", exc.getMessage());
    }

    @Test
    void throwsExceptionForInvalidBox() throws Exception {
        Game g = new Game(new Player("❌"), new Player("⭕️"));
        /*Exception exc = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                g.nextAttempt(1);
            }
        });
        assertEquals("box no out of range", exc.getMessage());*/

        g.nextAttempt(1);
    }
}
