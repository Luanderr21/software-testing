package nl.tudelft.jpacman.e2e.framework.suspension;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;

public class ResumeSteps {
	private Launcher launcher;
	
	private Game getGame() {
		return launcher.getGame();
	}
	
	@Before
	public void setup() {
		launcher = new Launcher();
		launcher.launch();
	}
	@Given("The game is suspended")
	public void the_game_is_suspended() {
		getGame().start();
		getGame().stop();
		assertThat(getGame().isInProgress()).isFalse();
	}

	@When("The player clicks the {string} button")
	public void the_player_clicks_the_button(String string) {
		getGame().start();
	}

	@Then("the game is resumed")
	public void the_game_is_resumed() {
		assertThat(getGame().isInProgress()).isTrue();
	}
	
	@After("S4")
	public void teardownUI() {
		launcher.dispose();
	}
}
