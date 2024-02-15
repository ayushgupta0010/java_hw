import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class GameTests {
    @Test
    void testUserLoseOnSameChoice() {
        // When user and computer make same choice
        assertEquals(Game.didUserWin(0, 0), false);
    }

    @Test
    void testUserWin() {
        // When user choses Fjallbo, and computer choses Vittsjo
        assertTrue(Game.didUserWin(0, 2));
    }

    @Test
    void testComputerWin() {
        // When user choses Vittsjo, and computer choses Fjallbo
        assertFalse(Game.didUserWin(2, 0));
    }
}
