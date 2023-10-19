package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FirstTest {
	
	ScreenController controller;
	
	@Test
	public void myTest() {
		controller = new ScreenController();
		assertEquals(controller.pushTask(), true);
	}
	
}
