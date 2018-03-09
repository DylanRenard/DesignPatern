package fr.ensim.dp.map.player.event;

import java.util.EventListener;

public interface ChangeStateListener extends EventListener {

	public void changedState(ChangeStateEvent e);
}
