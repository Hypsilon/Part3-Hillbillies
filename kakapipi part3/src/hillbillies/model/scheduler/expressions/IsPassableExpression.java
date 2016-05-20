package hillbillies.model.scheduler.expressions;

import ogp.framework.util.ModelException;
import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * An IsPassableExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class IsPassableExpression extends BooleanExpression {
	
	/**
	 * Constructor for the IsPassableExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 * 			a position
	 */
	public IsPassableExpression(SourceLocation sourceLocation,
			PositionExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates the task: returns true if the position (subExpression) is passable, false otherwise.
	 * 
	 * @param task
	 */
	@Override
	public Boolean evaluate(Task task) {
		int[] pos = (int[]) this.getSubExpressions()[0].evaluate(task);
		try {
			return !task.getWorld().getCube(pos[0], pos[1], pos[2]).isSolid();
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return false;
	}
}
