package nl.tudelft.jpacman.e2e.framework.startup;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;

public class StartGameSteps {
	private Launcher launcher;
	
	private Game getGame() {
		return launcher.getGame();
	}
	
	@Given("The user has lanched the game")
	public void the_user_has_lanched_the_game() {
		launcher = new Launcher();
		launcher.launch();
	}

	@When("The user presses the {string} button")
	public void the_user_presses_the_button(String string) {
		getGame().start();
	}

	@Then("The game is running")
	public void the_game_is_running() {
	   assertThat(getGame().isInProgress()).isTrue();
	}
	
	@After("framework")
	public void teardownUI() {
		launcher.dispose();
	}
	
}
