package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Log;
import hillbillies.model.world.Unit;
import hillbillies.model.world.Vector;
import hillbillies.part3.programs.SourceLocation;

import java.util.Set;

/**
 * A LogPositionExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class LogPositionExpression extends PositionExpression {

	/**
	 * Constructor for the LogPositionExpression class.
	 * 
	 * @param sourceLocation
	 */
	public LogPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Returns the log closest to the current unit executing this task.
	 * If there are no logs in the world, returns null.
	 * 
	 * @param unit
	 */
	@Override
	public int[] evaluate(Task task) {
		Unit unit = task.getAssignedUnit();
		Set<Log> logs = unit.getWorld().getLogs();
		double minimumDistance = Double.MAX_VALUE;
		int[] minimumLog = null;
		for (Log log : logs) {
			Vector deltaPos = unit.getPosition().subtract(log.getPosition());
			double distance = deltaPos.length();
			if (distance < minimumDistance) {
				minimumDistance = distance;
				minimumLog = log.getPosition().toIntArray();
			}
		}
		return minimumLog;
	}

	/**
	 * Returns the textual repreentation of the position of the nearest log (if there is any).
	 * 
	 * @param task
	 */
	@Override
	public String getString(Task task) {
		if (this.evaluate(task) != null)
			return Expression.positionToString(this.evaluate(task));
		return "no log in world";
	}

}
