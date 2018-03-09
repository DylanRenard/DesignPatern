package fr.ensim.dp.map.player;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import fr.ensim.dp.map.player.event.ChangeStateEvent;
import fr.ensim.dp.map.player.event.ChangeStateListener;
import fr.ensim.dp.util.LoggerUtil;

public class Player implements IPlayer {

	private IStatePlayer currentState = StateStop.INSTANCE;
	private ArrayList<ChangeStateListener> listListener = new ArrayList<ChangeStateListener>();

	final static Logger logger = LoggerUtil.getLogger();

	@Override
	public void play() {
		logger.debug("on passe a play");
		currentState.play(this);
	}

	@Override
	public void stop() {
		logger.debug("on passe a stop");
		currentState.stop(this);
	}

	@Override
	public void pause() {
		logger.debug("on passe a pause");
		currentState.pause(this);
	}

	@Override
	public void forward() {
		logger.debug("on passe a forward");
		currentState.forward(this);
	}

	@Override
	public void backward() {
		logger.debug("on passe a backward");
		currentState.backward(this);
	}

	@Override
	public void firechangeState(IStatePlayer state) {
		currentState = state;
		
		ChangeStateEvent event = new ChangeStateEvent(this);
		for(ChangeStateListener csl : listListener) csl.changedState(event);
	}

	@Override
	public void addChangeStateListener(ChangeStateListener listener) {
		listListener.add(listener);
	}

	@Override
	public void removeChangeStateListener(ChangeStateListener listener) {
		if(listListener.contains(listener)) listListener.remove(listener);
	}

	@Override
	public IStatePlayer getState() {
		return currentState;
	}

	@Override
	public boolean isStatePlay() {
		return currentState == StatePlay.INSTANCE;
	}

	@Override
	public boolean isStatePause() {
		return currentState == StatePause.INSTANCE;
	}

	@Override
	public boolean isStateStop() {
		return currentState == StateStop.INSTANCE;
	}

}
