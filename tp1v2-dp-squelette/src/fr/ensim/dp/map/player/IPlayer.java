package fr.ensim.dp.map.player;

import fr.ensim.dp.map.player.event.ChangeStateListener;

public interface IPlayer {

  void play();

  void stop();

  void pause();

  void forward();

  void backward();

  void firechangeState(IStatePlayer state);

  void addChangeStateListener(ChangeStateListener listener);

  void removeChangeStateListener(ChangeStateListener listener);

  IStatePlayer getState();

  boolean isStatePlay();

  boolean isStatePause();

  boolean isStateStop();

}
