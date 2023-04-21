package nl.tudelft.jpacman.e2e.framework.startup;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;

public class StartupCnSteps {
	private Launcher launcher;
	private Game getGame() {
		return launcher.getGame();
	}
	@假如("用户已经启动游戏")
	public void 用户已经启动游戏() {
		launcher = new Launcher();
		launcher.launch();
	}

	@当("用户按下 {string} 按钮")
	public void 用户按下_按钮(String string) {
		getGame().start();
	}

	@那么("游戏开始运行")
	public void 游戏开始运行() {
		assertThat(getGame().isInProgress()).isTrue();
	}
	
	@After
	void teardownUI() {
		launcher.dispose();
		System.out.println("teardown");
	}

}
