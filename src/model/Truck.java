package model;

import main.Constants;

public class Truck {
	private double x;
	private double y;

	public Truck() {
		setX(Constants.X_INITIAL);

		setY(Constants.Y_INITIAL);
	}

	public double getX() {
		return x;
	}

	public void setX(double x1) {
		this.x = x1;
	}

	public double getY() {
		return y;
	}

	public void setY(double y1) {
		this.y = y1;
	}

	@Override
	public String toString() {
		return "X: " + x + ";  Y: " + y;
	}

}
