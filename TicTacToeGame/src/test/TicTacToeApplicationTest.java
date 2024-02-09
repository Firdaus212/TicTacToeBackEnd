package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.TicTacToeApplication;

class TicTacToeApplicationTest {
	
	TicTacToeApplication ticTacToeApplication;

	@BeforeEach
	void setUp() throws Exception {
		ticTacToeApplication = new TicTacToeApplication();
	}

	@Test
	void test() {
		assertDoesNotThrow(() -> TicTacToeApplication.main(new String[] {}));
	}

}
