package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FirstTest {
	
	MainSceneController controller;
	
	@Test
	public void myTest() {
		controller = new MainSceneController();
		assertEquals(controller.pushTask(), true);
	}
	
}
