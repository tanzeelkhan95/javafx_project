package Game.StyleAndControll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {

    @Test
    void TestPlayerName() {
        var test = new PlayerModel();
        assertEquals(test.getPlayerName(),null);
        test.setPlayerName("Ahmad");
        assertEquals(test.getPlayerName(),"Ahmad");
    }


}