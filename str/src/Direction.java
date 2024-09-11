import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the direction in which a car can travel on the bridge. It has two
 * possible values: NORTH and SOUTH.
 * 
 * @author Daniel Vázquez Rodríguez
 */
public enum Direction {
	/**
	 * Represents the northbound direction. Cars moving in this direction go from
	 * south to north.
	 */
	NORTH,

	/**
	 * Represents the southbound direction. Cars moving in this direction go from
	 * north to south.
	 */
	SOUTH;
	

	/**
	 * Generates and returns a random direction.
	 * 
	 * @return The randomly generated direction
	 */
	static Direction generateDirection() {
		return (ThreadLocalRandom.current().nextBoolean() ? Direction.NORTH : Direction.SOUTH);
	}

	/**
	 * Overrides the default {@code toString()} to provide a readable string
	 * representation of the direction.
	 * 
	 * @return The string representation of the direction
	 */
	@Override
	public String toString() {
		return (this == NORTH ? "north" : "south");
	}
}