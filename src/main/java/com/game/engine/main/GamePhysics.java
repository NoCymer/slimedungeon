package com.game.engine.main;

import com.game.engine.physics.PhysicsEngine;
import com.game.engine.view.Display;

/** GamePhysics class */
public class GamePhysics {

	/** The physics engine */
	public PhysicsEngine physicsEngine;

	/** The display */
	public Display display;
	/** Updates the physics engine */
	public void update() {
		physicsEngine.update();
	}
	/** Renders the display  */
	public void render() {
		display.render();
	}
}