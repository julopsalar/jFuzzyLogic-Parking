package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import net.sourceforge.jFuzzyLogic.FIS;
import view.Simulation;

public class Program {

	public static void main(String[] args) {

		// Initializing visual simulation
		JPanel simulation = new Simulation();

		JFrame frame = new JFrame("Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(simulation);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Local variables for the algorithm
		double x = Constants.X_INITIAL;
		double y = Constants.Y_INITIAL;
		double phi = Constants.PHI_INITIAL;
		double theta, cos_phi, sin_phi;

		// Loading and initializing Fuzzy Inference System
		String fileName = "src/fcl/Parking.fcl";
		FIS fis = null;
		try {
			fis = FIS.load(fileName, true);
		} catch (Exception e) {
			System.err.println("ERROR: File " + fileName + " (checkformat)");
		} // Error while loading?
		if (fis == null) {
			System.err.println("ERROR: Missing file: " + fileName + " (check    path)");
			return;
		}

		// Set inputs
		fis.setVariable("x", x);
		fis.setVariable("phi", phi);

		do {

			// Comprobacion de rangos
			if (x < 0 || x > 100 || phi < -100 || phi > 280) {
				System.out.println("ERROR VARIABLE/S FUERA DE RANGO");
				return;
			}

			// Evaluate
			fis.evaluate();

			theta = Constants.roundDouble(fis.getVariable("theta").getValue(), 1);

			// Actualizamos el valor
			fis.setVariable("phi", Constants.roundDouble(phi + theta, 1));
			phi = fis.getVariable("phi").getValue();

			Controller.getInstance().setPhi(phi);

			cos_phi = Math.cos(Math.toRadians(phi));
			sin_phi = Math.sin(Math.toRadians(phi));

			fis.setVariable("x", Constants.roundDouble(x + Constants.INCREASE * cos_phi, 1));
			x = fis.getVariable("x").getValue();

			Controller.getInstance().setX(x);

			// Modify y increase by 0.5...
			y += Constants.roundDouble(Constants.INCREASE * sin_phi * 1, 1);
			System.out.println("X = " + x + "\tPHI = " + phi + "\tY = " + y);
			Controller.getInstance().setY(y);

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}

		} while (x != Constants.X_FINAL || phi != Constants.PHI_FINAL);

		if (x == Constants.X_FINAL && phi == Constants.PHI_FINAL) {
			System.out.println("FINISHED SUCCESSFULLY");
			System.out.println("\tX = " + x);
			System.out.println("\tPHI = " + phi);
		}

	}

}
