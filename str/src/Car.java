/**
 * The {@code Car} class represents a car that is going to cross the bridge.
 * Each car has a unique identifier and a direction in which it intends to
 * travel. It implements the {@code Runnable} interface to support concurrent
 * execution.
 * 
 * @author Daniel Vázquez Rodríguez
 */
public class Car implements Runnable {
	private static int count = 0;

	private final Bridge bridge;

	private final int carId;

	private final Direction carDirection;
	

	/**
	 * Constructs a car object with the specified bridge and direction.
	 * 
	 * @param bridge       The bridge on which the car will be crossing.
	 * @param carDirection The direction in which the car intends to travel.
	 */
	public Car(Bridge bridge, Direction carDirection) {
		this.bridge = bridge;
		this.carDirection = carDirection;
		this.carId = count++;
	}

	/**
	 * Returns the direction in which the car intends to travel.
	 * 
	 * @return The direction of the car
	 */
	public Direction getDirection() {
		return carDirection;
	}

	/**
	 * Executes the logic for the car's behavior when crossing the bridge. It
	 * attempts to cross the bridge by invoking the {@code crossBridgeIfAvailable}
	 * method of the bridge object. If the car is interrupted while waiting to cross
	 * the bridge, it handles the interruption.
	 */
	@Override
	public void run() {
		try {
			bridge.crossBridgeIfAvailable(this);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Failed to cross the bridge.");
		}
	}

	/**
	 * Returns a string representation of the car.
	 * 
	 * @return A string representation of the car in the format "Car#id"
	 */
	@Override
	public String toString() {
		return ("Car#" + carId);
	}
}