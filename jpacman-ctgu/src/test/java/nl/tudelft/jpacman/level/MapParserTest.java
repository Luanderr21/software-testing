package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MapParserTest {
    @Mock
    LevelFactory mocklevelFactory;
    @Mock
    BoardFactory mockboardFactory;
    @Mock
    Pellet mockPellet;
    @Mock
    Ghost mockGhost;
    MapParser mapParser;
    List<String> groundString;
    List<String> wallString;
    List<String> ghostString;
    List<String> pelletString;
    List<String> playerString;
    List<String> invalidString;
    List<String> wrongSizeString;
    String fileName;

    @BeforeEach
    void setUp() {
        groundString = Arrays.asList(" ");
        wallString = Arrays.asList("#");
        ghostString = Arrays.asList("G");
        pelletString = Arrays.asList(".");
        playerString = Arrays.asList("P");
        invalidString = Arrays.asList("?");
        wrongSizeString = Arrays.asList("#", "##");
        fileName = "/testBoard.txt";

        mocklevelFactory = mock(LevelFactory.class);
        mockboardFactory = mock(BoardFactory.class);
        mockPellet = mock(Pellet.class);
        mockGhost = mock(Ghost.class);

        mapParser = new MapParser(mocklevelFactory, mockboardFactory);
        when(mocklevelFactory.createPellet()).thenReturn(mockPellet);
        when(mocklevelFactory.createGhost()).thenReturn(mockGhost);
    }

    @Test
    void wallMakeTest() {
        mapParser.parseMap(wallString);

        verify(mockboardFactory).createWall();
        verify(mockboardFactory).createBoard(any());
        verify(mocklevelFactory).createLevel(any(), any(), any());
    }

    @Test
    void groundMakeTest() {
        mapParser.parseMap(groundString);

        verify(mockboardFactory).createGround();
        verify(mockboardFactory).createBoard(any());
        verify(mocklevelFactory).createLevel(any(), any(), any());
    }

    @Test
    void ghostMakeTest() {
        mapParser.parseMap(ghostString);

        verify(mocklevelFactory).createGhost();
        verify(mockboardFactory).createGround();
        verify(mockboardFactory).createBoard(any());
        verify(mocklevelFactory).createLevel(any(), any(), any());
    }

    @Test
    void playerMakeTest() {
        mapParser.parseMap(playerString);

        verify(mockboardFactory).createGround();
        verify(mockboardFactory).createBoard(any());
        verify(mocklevelFactory).createLevel(any(), any(), any());
    }

    @Test
    void pelletMakeTest() {
        mapParser.parseMap(pelletString);

        verify(mocklevelFactory).createPellet();
        verify(mockboardFactory).createGround();
        verify(mockboardFactory).createBoard(any());
        verify(mocklevelFactory).createLevel(any(), any(), any());
    }

    @Test
    void createFromFile() throws IOException {
        mapParser.parseMap(fileName);

        verify(mockboardFactory, atLeastOnce()).createGround();
        verify(mockboardFactory, atLeastOnce()).createWall();
        verify(mockboardFactory, atLeastOnce()).createBoard(any());
        verify(mocklevelFactory, atLeastOnce()).createPellet();
        verify(mocklevelFactory, atLeastOnce()).createGhost();
        verify(mocklevelFactory, atLeastOnce()).createLevel(any(), any(), any());
    }

    @Test
    void invalidSquareTest() {
        assertThrows(PacmanConfigurationException.class, ()->{
            mapParser.parseMap(invalidString);
        });
    }

    @Test
    void wrongMapSizeTest() {
        assertThrows(PacmanConfigurationException.class, ()->{
            mapParser.parseMap(wrongSizeString);
        });
    }

    @Test
    void nullMapTest() {
        assertThrows(PacmanConfigurationException.class, ()->{
            mapParser.parseMap((List<String>) null);
        });
    }

    @Test
    void emptyMapTest() {
        assertThrows(PacmanConfigurationException.class, () -> {
            mapParser.parseMap(new ArrayList<>());
        });
    }

    @Test
    void nothingTest() {
        assertThrows(PacmanConfigurationException.class, () -> {
            mapParser.parseMap(Arrays.asList(""));
        });
    }

    @Test
    void noFileResourceTest() {
        assertThrows(PacmanConfigurationException.class, () -> {
            mapParser.parseMap("nonExistsFile");
        });
    }

    @Test
    void boardCreatorTest() {
        assertNotNull(mapParser.getBoardCreator());
    }
}
