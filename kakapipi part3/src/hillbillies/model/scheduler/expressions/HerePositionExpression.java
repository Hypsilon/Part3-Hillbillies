package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * An HerePositionExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class HerePositionExpression extends PositionExpression {

	/**
	 * Constructor for the HereExpression class.
	 * 
	 * @param sourceLocation
	 */
	public HerePositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Evaluates the task: returns the position of the assigned unit of this task.
	 * 
	 * @param task
	 * @return the position of the assigned unit of this task
	 */
	@Override
	public int[] evaluate(Task task) {
		return task.getAssignedUnit().getPosition().toIntArray();
	}
}
