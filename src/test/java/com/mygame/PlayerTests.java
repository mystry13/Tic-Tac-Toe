package com.mygame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {

    @Test
    void constructPlayerWithChar() {
        Player p1 = new Player("❌");
        assertEquals("❌", p1.getCharacter());
    }
}
