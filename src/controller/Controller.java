package controller;

import main.Constants;
import model.Truck;

public class Controller {

	private Truck truck;
	private double phi;

	private static Controller controller;

	public Controller() {
		this.truck = new Truck();
		this.phi = Constants.PHI_INITIAL;
		System.out.println(this.truck.toString());
	}

	// Singleton
	public static Controller getInstance() {
		if (controller == null)
			controller = new Controller();
		return controller;
	}

	public Truck getTruck() {
		return truck;
	}

	public double getPhi() {
		return phi;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}

	public void setX(double x) {
		this.truck.setX(x);
	}

	public void setY(double y) {
		this.truck.setY(y);
	}

	public boolean parked() {
		if (this.truck.getX() == 50.0 && this.phi == 90.0) {
			return true;
		} else {
			return false;
		}
	}
}
