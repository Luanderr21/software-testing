package nl.tudelft.jpacman.e2e.framework.startup;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;

public class MovePlayerSteps {
	private Launcher launcher;
	private Player player;
	private Square nextSquare;
	private Pellet pellet;
	private int score;
	private Game getGame() {
		return launcher.getGame();
	}
	
	@Before
	public void setup() {
		launcher = new Launcher();
		launcher.launch();
		launcher.withMapFile("simplemap.txt");
	}
	
	@Given("the game has started,")
	public void the_game_has_started() {
		getGame().start();
		
		assertThat(getGame().isInProgress()).isTrue();
	}

	@Given("my Pacman is next to a square containing a pellet;")
	public void my_Pacman_is_next_to_a_square_containing_a_pellet() {
		List<Player> players = getGame().getPlayers();
		player = players.get(0);
		
		//存储原始分数
		score  = player.getScore();
		
		Square square = player.getSquare();
		
		//取豆子占的方块
		nextSquare = square.getSquareAt(Direction.EAST);
		List<Unit> units = nextSquare.getOccupants();
		pellet = (Pellet)units.get(0);
		
		//断言
		assertThat(units.size()).isEqualTo(1);
		assertThat(units.get(0)).isInstanceOf(Pellet.class);
		
		
	}
	

	@When("I press an arrow key towards that square;")
	public void i_press_an_arrow_key_towards_that_square() {
	    getGame().move(player, Direction.EAST);
	}

	@Then("my Pacman can move to that square,")
	public void my_Pacman_can_move_to_that_square() {
		assertThat(player.getSquare()).isEqualTo(nextSquare);
	}

	@Then("I earn the points for the pellet,")
	public void i_earn_the_points_for_the_pellet() {
		assertThat(score).isEqualTo(0);
		assertThat(player.getScore()).isEqualTo(score+pellet.getValue());
	}

	@Then("the pellet disappears from that square.")
	public void the_pellet_disappears_from_that_square() {
	   assertThat(nextSquare.getOccupants()).containsExactly(player).doesNotContain(pellet);
	}
	
	@After
	public void teardown() {
		launcher.dispose();
	}
}
