package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Boulder;
import hillbillies.model.world.Unit;
import hillbillies.model.world.Vector;
import hillbillies.part3.programs.SourceLocation;

import java.util.Set;

/**
 * An BoulderPositionExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class BoulderPositionExpression extends PositionExpression {

	/**
	 * Constructor for the BoulderPositionExpression class.
	 * 
	 * @param sourceLocation
	 */
	public BoulderPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Evaluates the task: returns the position of the nearest boulder.
	 * 
	 * @param task
	 * @return the position of the nearest boulder (if there is any)
	 */
	@Override
	public int[] evaluate(Task task) {
		Unit unit = task.getAssignedUnit();
		Set<Boulder> boulders = unit.getWorld().getBoulders();
		double minimumDistance = Double.MAX_VALUE;
		int[] minimumBoulder = null;
		for (Boulder boulder : boulders) {
			Vector deltaPos = unit.getPosition().subtract(boulder.getPosition());
			double distance = deltaPos.length();
			if (distance < minimumDistance) {
				minimumDistance = distance;
				minimumBoulder = boulder.getPosition().toIntArray();
			}
		}
		return minimumBoulder;
	}

	/**
	 * Returns the textual representation of the nearest boulder's position (if there is any).
	 * 
	 * @param task
	 * @return the textual representation of the nearest boulder's position
	 */
	@Override
	public String getString(Task task) {
		if (this.evaluate(task) != null)
			return Expression.positionToString((int[]) this.evaluate(task));
		return "no boulder in world";
	}

}
