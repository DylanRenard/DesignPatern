package fr.ensim.dp.map.player.event;

import java.util.EventObject;

import fr.ensim.dp.map.player.IPlayer;
import fr.ensim.dp.map.player.StateBackward;
import fr.ensim.dp.map.player.StateForward;
import fr.ensim.dp.map.player.StatePause;
import fr.ensim.dp.map.player.StatePlay;
import fr.ensim.dp.map.player.StateStop;

@SuppressWarnings("serial")
public class ChangeStateEvent extends EventObject {

	public ChangeStateEvent(IPlayer source) {
		super(source);
	}

	public boolean isEventStateStop() {
		return ((IPlayer) source).getState() == StateStop.INSTANCE;
	}

	public boolean isEventStatePause() {
		return ((IPlayer) source).getState() == StatePause.INSTANCE;
	}

	public boolean isEventStatePlay() {
		return ((IPlayer) source).getState() == StatePlay.INSTANCE;
	}

	public boolean isEventStateForward() {
		return ((IPlayer) source).getState() == StateForward.INSTANCE;
	}

	public boolean isEventStateBackward() {
		return ((IPlayer) source).getState() == StateBackward.INSTANCE;
	}
}
