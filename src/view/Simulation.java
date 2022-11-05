package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;
import main.Constants;
import model.Truck;

@SuppressWarnings("serial")
public class Simulation extends JPanel {

	/**
	 * Create the Panel
	 */
	public Simulation() {
		// It will reload every 50 milliseconds
		Timer timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if truck is not parked
				if (!Controller.getInstance().parked()) {
					repaint();
				} else {
					// Stop the timer if parked
					((Timer) e.getSource()).stop();
				}
			}
		});
		timer.start();

	}

	// Setting Panel Size
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setColor(Color.BLACK);
		// X limits
		g2d.drawLine(50, 50, 50, Constants.FRAME_HEIGHT - 50);
		g2d.drawLine(550, 50, 550, Constants.FRAME_HEIGHT - 50);

		// Y limits
		g2d.drawLine(50, 50, Constants.FRAME_WIDTH - 50, 50);
		g2d.drawLine(50, Constants.FRAME_HEIGHT - 50, Constants.FRAME_WIDTH - 50, Constants.FRAME_HEIGHT - 50);

		Truck t = Controller.getInstance().getTruck();
		// Truck and goal
		g2d.setColor(Color.RED);

		Graphics2D g2 = (Graphics2D) g.create();
		Rectangle r = new Rectangle((int) (5 * t.getX()), (int) (50 + t.getY()), 5 * Constants.TRUCK_HEIGHT,
				5 * Constants.TRUCK_WIDTH);
		g2.setColor(Color.RED);
		g2.rotate(Math.toRadians(Controller.getInstance().getPhi()), r.x + r.width / 2, r.y + r.height / 2);
		g2.draw(r);
		g2.fill(r);


		g2d.drawLine(Constants.FRAME_WIDTH / 2, 50, Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT - 50);

		g2d.dispose();
	}

}
