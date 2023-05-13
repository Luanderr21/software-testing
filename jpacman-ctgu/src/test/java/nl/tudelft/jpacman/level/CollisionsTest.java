package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/*
*
* Verifying different collision behavior mainly through verifying mockPointCalculator's behavior
* Player and Ghost:     mockPointCalculator: collidedWithAGhost
*                       mockplayer.setAlive
*                       mockplayer.setKiller
*
* Player and Pellet:    mockPointCalculator: consumedAPellet
*                       mockpellet.leaveSquare
*
* Verifying no behavior by verifying that the behavior of mockPointCalculator above not been executed
* (Verifying the function executed times with Mockito.never())
*
* */

public class CollisionsTest {
    @Mock
    private Player mockplayer;
    @Mock
    private Ghost mockghost;
    @Mock
    private Pellet mockpellet;
    @Mock
    private Unit mockunit;
    @Mock
    private PointCalculator mockPointCalculator;
    private PlayerCollisions playerCollisions;

    @BeforeEach
    void setUp() {
        mockplayer = Mockito.mock(Player.class);
        mockghost = Mockito.mock(Ghost.class);
        mockpellet = Mockito.mock(Pellet.class);
        mockPointCalculator = Mockito.mock(PointCalculator.class);
        mockunit = Mockito.mock(Unit.class);
        playerCollisions = new PlayerCollisions(mockPointCalculator);
    }

    /*
    * Player collides to pellet
    * PlayerCollision.playerVersusPellet()
    * */
    @Test
    public void testPlayerPellet() {
        playerCollisions.collide(mockplayer, mockpellet);

        Mockito.verify(mockPointCalculator).consumedAPellet(mockplayer, mockpellet);
        Mockito.verify(mockpellet).leaveSquare();
    }

    /*
    * Pellet collides to player
    * Same as above
    * */
    @Test
    public void testPelletPlayer() {
        playerCollisions.collide(mockpellet, mockplayer);

        Mockito.verify(mockPointCalculator).consumedAPellet(mockplayer, mockpellet);
        Mockito.verify(mockpellet).leaveSquare();
    }

    /*
    * Player collides to ghost
    * PlayerCollision.playerVersusGhost()
    * */
    @Test
    public void testPlayerGhost() {
        playerCollisions.collide(mockplayer, mockghost);

        Mockito.verify(mockPointCalculator).collidedWithAGhost(mockplayer, mockghost);
        Mockito.verify(mockplayer).setAlive(false);
        Mockito.verify(mockplayer).setKiller(mockghost);
    }

    /*
    * Ghost collides to player
    * same as above
    * */
    @Test
    public void testGhostPlayer() {
        playerCollisions.collide(mockghost, mockplayer);

        Mockito.verify(mockPointCalculator).collidedWithAGhost(mockplayer, mockghost);
        Mockito.verify(mockplayer).setAlive(false);
        Mockito.verify(mockplayer).setKiller(mockghost);
    }

    /*
    * Pellet collides to ghost
    * Nothing happens
    * */
    @Test
    public void testPelletGhost() {
        playerCollisions.collide(mockpellet, mockghost);

        Mockito.verify(mockPointCalculator, Mockito.never()).collidedWithAGhost(Mockito.any(), Mockito.any());
        Mockito.verify(mockPointCalculator, Mockito.never()).consumedAPellet(Mockito.any(), Mockito.any());
    }

    /*
    * Ghost collides to pellet
    * Nothing happens
    * */
    @Test
    public void testGhostPellet() {
        playerCollisions.collide(mockghost, mockpellet);

        Mockito.verify(mockPointCalculator, Mockito.never()).collidedWithAGhost(Mockito.any(), Mockito.any());
        Mockito.verify(mockPointCalculator, Mockito.never()).consumedAPellet(Mockito.any(), Mockito.any());
    }

    /*
    * Player collides to player
    * Noting happens
    * */
    @Test
    public void testPlayerPlayer() {
        playerCollisions.collide(mockplayer, mockplayer);

        Mockito.verify(mockPointCalculator, Mockito.never()).collidedWithAGhost(Mockito.any(), Mockito.any());
        Mockito.verify(mockPointCalculator, Mockito.never()).consumedAPellet(Mockito.any(), Mockito.any());
    }

    /*
    * Pellet collides to pellet
    * Nothing happens
    * */
    @Test
    public void testPelletPellet() {
        playerCollisions.collide(mockpellet, mockpellet);

        Mockito.verify(mockPointCalculator, Mockito.never()).collidedWithAGhost(Mockito.any(), Mockito.any());
        Mockito.verify(mockPointCalculator, Mockito.never()).consumedAPellet(Mockito.any(), Mockito.any());
    }

    /*
    * Ghost collides to ghost
    * Noting happens
    * */
    @Test
    public void testGhostGhost() {
        playerCollisions.collide(mockghost, mockghost);

        Mockito.verify(mockPointCalculator, Mockito.never()).collidedWithAGhost(Mockito.any(), Mockito.any());
        Mockito.verify(mockPointCalculator, Mockito.never()).consumedAPellet(Mockito.any(), Mockito.any());
    }

    @Test
    public void moverTest() {
        playerCollisions.collide(mockunit, mockunit);

        Mockito.verify(mockPointCalculator, Mockito.never()).collidedWithAGhost(Mockito.any(), Mockito.any());
        Mockito.verify(mockPointCalculator, Mockito.never()).consumedAPellet(Mockito.any(), Mockito.any());
    }
}
