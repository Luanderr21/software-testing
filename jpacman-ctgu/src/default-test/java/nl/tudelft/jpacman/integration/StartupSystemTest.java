package nl.tudelft.jpacman.integration;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

/**
 * An example test class that conducts integration tests.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StartupSystemTest {

    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    public void before() {
        launcher = new Launcher();
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     * The simplest test that just starts the
     * game and checks it is indeed in progress.
     * @throws InterruptedException 
     */
    @Test
    @Order(1)
    public void gameIsRunning() throws InterruptedException {
        launcher.launch();

        getGame().start();

        Thread.sleep(5000L);
        
        //游戏启动
//        assertThat(getGame().isInProgress()).isTrue();
        
        // Players
        List<Player> players = getGame().getPlayers();
        assertThat(players.size()).isEqualTo(1);
        assertThat(players.get(0).getScore()).isEqualTo(0);
        
    }

    @Test
    @Order(2)
    public void gameIsPending() throws InterruptedException {
    	launcher.launch();
    	getGame().start();
    	
    	Thread.sleep(5000);
    	
    	//游戏启动
//    	assertThat(getGame().isInProgress()).isTrue();
    	
    	//暂停
    	getGame().stop();
    	Thread.sleep(5000);
    	
    	assertThat(getGame().isInProgress()).isFalse();
    	
    }

    private Game getGame() {
        return launcher.getGame();
    }
}
