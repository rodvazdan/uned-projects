import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The {@code Bridge} class represents a bridge with a single lane where cars
 * can cross in different directions. It manages the crossing of cars according
 * to a specified protocol.
 * 
 * @author Daniel Vázquez Rodríguez
 */
public class Bridge {
	private static int threadCount;

	private final Object lock = new Object();

	private final int MIN_TIMEOUT = 1000;

	private final int MAX_TIMEOUT = 3000;

	private final int CAR_CROSSING_LIMIT = 3;

	private int carsCrossedFromNorth = 0;

	private int carsCrossedFromSouth = 0;

	private Queue<Car> northToSouthQueue = new LinkedList<>();

	private Queue<Car> southToNorthQueue = new LinkedList<>();

	private boolean isBridgeAvailable = true;

	private Direction priorityDirection;
	
	
	private static void parseInput(String[] args) {
		if (args.length != 1) {
			if (args.length == 0) {
				System.err.print("An integer argument was expected");
			} else {
				System.err.print("Too many arguments");
			}
			System.exit(-1);
		} else {
			try {
				threadCount = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.print("Not a valid argument");
				System.exit(-1);
			}
		}
	}
	
	private int generateTimeout() {
		return ThreadLocalRandom.current().nextInt(MIN_TIMEOUT, MAX_TIMEOUT + 1);
	}
	
	private void incrementCarCount(Car car) {
		if (car.getDirection() == Direction.NORTH) {
			carsCrossedFromNorth++;
		} else {
			carsCrossedFromSouth++;
		}
	}

	private void addCarToQueue(Car car) {
		(car.getDirection() == Direction.NORTH ? northToSouthQueue : southToNorthQueue).add(car);

	}

	private void removeCarFromQueue(Car car) {
		(car.getDirection() == Direction.NORTH ? northToSouthQueue : southToNorthQueue).poll();

	}
	
	private void determineNextDirection() {
		priorityDirection = switch (priorityDirection) {
		case NORTH -> {
			if (carsCrossedFromNorth < CAR_CROSSING_LIMIT && !northToSouthQueue.isEmpty()) {
				yield Direction.NORTH;
			} else {
				carsCrossedFromNorth = 0;
				yield Direction.SOUTH;
			}
		}
		case SOUTH -> {
			if (carsCrossedFromSouth < CAR_CROSSING_LIMIT && !southToNorthQueue.isEmpty()) {
				yield Direction.SOUTH;
			} else {
				carsCrossedFromSouth = 0;
				yield Direction.NORTH;
			}
		}
		};
	}
	
	private static void createThreadPool(Bridge bridge, int threadCount) {
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new Car(bridge, Direction.generateDirection()));
			thread.start();
		}
	}
	
	/**
	 * Allows a car to cross the bridge if it is available, otherwise adds the car
	 * to the appropiate queue and waits until the bridge becomes available for the
	 * car's direction.
	 * 
	 * @param car  The car attempting to cross the bridge.
	 * @throws InterruptedException if the car is interrupted while waiting
	 */
	public void crossBridgeIfAvailable(Car car) throws InterruptedException {
		System.out.println(car.toString() + " reached the bridge from the " + car.getDirection());

		if (!isBridgeAvailable) {
			synchronized (lock) {
				addCarToQueue(car);
				
				System.out.println(car.toString() + " has entered the " + car.getDirection() + "ern queue");

				while (!isBridgeAvailable || priorityDirection != car.getDirection()) {
					System.out.println(car.toString() + " is waiting");
					lock.wait();
				}
				
				removeCarFromQueue(car);
				
				System.out.println(car.toString() + " has left the " + car.getDirection() + "ern queue and can cross now");
			}
		}

		crossBridge(car);
	}
	
	private void crossBridge(Car car) throws InterruptedException {
		synchronized (lock) {
			isBridgeAvailable = false;
			
			priorityDirection = car.getDirection();
			
			System.out.println(car.toString() + " is crossing the bridge...");
			
			Thread.sleep(generateTimeout());
			
			System.out.println(car.toString() + " has crossed the bridge");
			
			incrementCarCount(car);
			
			isBridgeAvailable = true;
			
			determineNextDirection();
			
			lock.notifyAll();
		}
	}
	
	/**
	 * Initializes the bridge and creates a thread pool of cars.
	 */
	public static void main(String[] args) {
		parseInput(args);
		createThreadPool(new Bridge(), threadCount);
	}
}