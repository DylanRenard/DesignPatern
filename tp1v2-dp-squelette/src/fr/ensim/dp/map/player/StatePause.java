package fr.ensim.dp.map.player;

public class StatePause extends AbstractState{
	
	public static StatePause INSTANCE = new StatePause();
	private StatePause() {};
	
	@Override
	public void play(IPlayer player) {
		player.firechangeState(StatePlay.INSTANCE);
	}
	
	@Override
	public void stop(IPlayer player) {
		player.firechangeState(StateStop.INSTANCE);
	}
}
