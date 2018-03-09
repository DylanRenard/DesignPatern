package fr.ensim.dp.map.player.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import fr.ensim.dp.map.player.IPlayer;
import fr.ensim.dp.map.player.event.ChangeStateEvent;
import fr.ensim.dp.map.player.event.ChangeStateListener;
import fr.ensim.dp.util.LoggerUtil;

public class PlayerPanel extends JPanel implements ChangeStateListener {
	private static Logger log = LoggerUtil.getLogger();

	private static final long serialVersionUID = 1L;

	private IPlayer player;

	private JButton buttonPlayPause;

	private JButton buttonStop;

	private JButton buttonBackward;

	private JButton buttonForward;

	private JLabel label;

	public static ImageIcon iconStop = new ImageIcon(PlayerPanel.class.getResource("stop.png"));

	public static ImageIcon iconPlay = new ImageIcon(PlayerPanel.class.getResource("play.png"));

	public static ImageIcon iconPause = new ImageIcon(PlayerPanel.class.getResource("pause.png"));

	public static ImageIcon iconForward = new ImageIcon(PlayerPanel.class.getResource("forward.png"));

	public static ImageIcon iconBackward = new ImageIcon(PlayerPanel.class.getResource("backward.png"));

	/**
	 * Create the panel.
	 */
	public PlayerPanel(IPlayer player) {
		super();
		this.player = player;
		initUI();
	}

	private void initUI() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel("Play");

		// Panel player
		JPanel panelPlayer = new JPanel();
		panelPlayer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		buttonPlayPause = createButton(iconPlay);
		buttonBackward = createButton(iconForward);
		buttonStop = createButton(iconStop);
		buttonForward = createButton(iconBackward);
		panelPlayer.add(buttonPlayPause);
		panelPlayer.add(buttonBackward);
		panelPlayer.add(buttonStop);
		panelPlayer.add(buttonForward);

		add(label);
		add(panelPlayer);

		// Evenement
		player.addChangeStateListener(this);

		buttonPlayPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (player.isStatePause() || player.isStateStop()) {
						player.play();
					} else {
						player.pause();
					}
				} catch (IllegalStateException ex) {
					log.error("IllegalStateException --> " + ex.getMessage());
				}
			}
		});
		buttonStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.stop();
				} catch (IllegalStateException ex) {
					log.error("IllegalStateException --> " + ex.getMessage());
				}
			}
		});
		buttonBackward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.backward();
				} catch (IllegalStateException ex) {
					log.error("IllegalStateException --> " + ex.getMessage());
				}
			}
		});
		buttonForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					player.forward();
				} catch (IllegalStateException ex) {
					log.error("IllegalStateException --> " + ex.getMessage());
				}
			}
		});
	}

	private JButton createButton(ImageIcon icon) {
		Dimension dimButton = new Dimension(40, 40);

		JButton btn = new JButton();
		btn.setMaximumSize(dimButton);
		btn.setMinimumSize(dimButton);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setOpaque(false);
		btn.setIcon(icon);

		return btn;
	}

	@Override
	public void changedState(ChangeStateEvent e) {
		if (e.isEventStatePause()) {
			buttonPlayPause.setIcon(iconPlay);
			label.setText("Pause...");
		} else if (e.isEventStatePlay()) {
			buttonPlayPause.setIcon(iconPause);
			label.setText("Play...");
		} else if (e.isEventStateStop()) {
			buttonPlayPause.setIcon(iconPlay);
			label.setText("Stop...");
		} else if (e.isEventStateForward()) {
			label.setText("Forward...");
		} else if (e.isEventStateBackward()) {
			label.setText("Backward...");
		}
	}
}
