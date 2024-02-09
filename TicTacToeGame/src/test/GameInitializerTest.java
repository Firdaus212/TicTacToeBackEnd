package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.GameInitializer;

class GameInitializerTest {
	
	GameInitializer gameInitializer;

	@BeforeEach
	void setUp() throws Exception {
		gameInitializer = new GameInitializer();
	}

	@Test
	void test() {
		gameInitializer.sizeField.setText("3");
		gameInitializer.startButton.doClick();
		assertFalse(gameInitializer.isVisible());
	}

}
