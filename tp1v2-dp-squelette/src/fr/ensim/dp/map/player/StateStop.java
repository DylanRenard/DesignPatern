package fr.ensim.dp.map.player;

public class StateStop extends AbstractState{

	public static StateStop INSTANCE = new StateStop();
	private StateStop() {};
	
	@Override
	public void play(IPlayer player) {
		player.firechangeState(StatePlay.INSTANCE);
	}
}
