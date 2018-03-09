package fr.ensim.dp.map.player;

public class StatePlay extends AbstractState{
	
	public static StatePlay INSTANCE = new StatePlay();
	private StatePlay() {};
	
	@Override
	public void stop(IPlayer player) {
		player.firechangeState(StateStop.INSTANCE);
	}

	@Override
	public void pause(IPlayer player) {
		player.firechangeState(StatePause.INSTANCE);
	}

	@Override
	public void forward(IPlayer player) {
		player.firechangeState(StateForward.INSTANCE);
	}

	@Override
	public void backward(IPlayer player) {
		player.firechangeState(StateBackward.INSTANCE);
	}
}
