package com.codcraft.testgame;

import com.CodCraft.api.model.Game;
import com.CodCraft.api.model.GameState;

public class TestGame extends Game<TestMain> {

	public TestGame(TestMain instance) {
		super(instance);
	}

	@Override
	public void initialize() {
		knownStates.put(new TestState(this).getId(), new TestState(this));
		setState(new TestState(this));
		addHook(new TestHook(this));
		
	}

	@Override
	public void preStateSwitch(GameState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postStateSwitch(GameState state) {
		// TODO Auto-generated method stub
		
	}

}
