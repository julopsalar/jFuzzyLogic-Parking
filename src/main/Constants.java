package main;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {

	// Auxiliar function to round a double to n decimal digits
	public static double roundDouble(double d, int n) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(n, RoundingMode.UP);
		return bd.doubleValue();
	}

	// Goal State (x, phi) = (50, 90)
	public static final double X_FINAL = 50;
	public static final double PHI_FINAL = 90;

	// Initial PHI
	public static final double PHI_INITIAL = 150;
	// Initial X point
	public static final double X_INITIAL = 25.00;
	// Initial Y point
	public static final double Y_INITIAL = 20.00;

	// Truck's dimensions
	public static final int TRUCK_WIDTH = 10;
	public static final int TRUCK_HEIGHT = 20;

	// Values maximum increase
	public static final int INCREASE = 10;

	// Frame size
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 700;
}
