package nl.tudelft.jpacman.e2e.framework.suspension;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;

public class SuspensionSteps {
	private Launcher launther;
	
	@Before
	public void setup() {
		launther = new Launcher();
	}
	
	private Game getGame() {
		return launther.getGame();
	}
	
	@Given("The game has started")
	public void the_game_has_started() {
		launther.launch();
		getGame().start();
	}

	@When("The player click the {string} button")
	public void the_player_click_the_button(String string) {
		getGame().stop();
	}

	@Then("The game pauses")
	public void the_game_pauses() {
		assertThat(getGame().isInProgress()).isFalse();
	}
	
	@After("S4")
	public void teardownUI() {
		launther.dispose();
	}
}
