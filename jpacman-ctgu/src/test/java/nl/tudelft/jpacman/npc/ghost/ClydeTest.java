package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClydeTest {
    PacManSprites pacManSprites;
    PlayerFactory playerFactory;
    BoardFactory boardFactory;
    GhostFactory ghostFactory;
    LevelFactory levelFactory;
    GhostMapParser mapParser;
    Level level;
    List<String> map;
    @BeforeEach
    void setup() {

        pacManSprites = new PacManSprites();
        playerFactory = new PlayerFactory(pacManSprites);
        boardFactory = new BoardFactory(pacManSprites);
        ghostFactory = new GhostFactory(pacManSprites);
        levelFactory = new LevelFactory(pacManSprites, ghostFactory, new DefaultPointCalculator());
        mapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }
    /*
    * when clyde is far from pacman
    * Expect clyde would go toward to pacman
    * */
    @Test
    void farTest() {
        //pacman and clyde distance more than 8 squares (10 squares)
        map = Arrays.asList("##############", "#P..........C#", "##############");
        level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertEquals(clyde.nextAiMove().get(), Direction.WEST);
    }

    /*
    * when clyde is at a distance of boundary of shyness (8 squares)
    * Expect clyde would go toward to pacman
    * */
    @Test
    void boundaryTest() {
        //pacman and clyde distance just 8 squares
        map = Arrays.asList("##############", "#.P........C.#", "##############");
        level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertEquals(clyde.nextAiMove().get(), Direction.WEST);
    }

    /*
     * when clyde is near pacman
     * Expect clyde would go backward to pacman
     * */
    @Test
    void nearTest() {
        //pacman and clyde distance less than 8 squares (6 squares)
        map = Arrays.asList("##############", "#..P......C..#", "##############");
        level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertEquals(clyde.nextAiMove().get(), Direction.EAST);
    }


    /*
    * when clyde has no way to player
    * clyde won't move (return empty)
    * */
    @Test
    void noPathTest() {
        //there's no way from clyde to pacman
        map = Arrays.asList("##############", "#P#........#C#", "##############");
        level = mapParser.parseMap(map);
        Player player = playerFactory.createPacMan();
        level.registerPlayer(player);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertEquals(clyde.nextAiMove(), Optional.empty());
    }

    /*
     * when clyde can't find a player in the map
     * clyde won't move (return empty)
     * */
    @Test
    void noPlayerTest() {
        //there's no pacman for clyde to find
        map = Arrays.asList("##############", "#...........C#", "##############");
        level = mapParser.parseMap(map);
        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        assertEquals(clyde.nextAiMove(), Optional.empty());
    }
}
