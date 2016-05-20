package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A PositionExpression for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 *
 */
public abstract class PositionExpression extends Expression<int[]> {

	/**
	 * The constructor for a composed PositionExpression
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public PositionExpression(SourceLocation sourceLocation,
			Expression<?>[] subExpressions) {
		super(sourceLocation, subExpressions);
	}
	
	/**
	 * The constructor for a non-composed PositionExpression
	 * @param sourceLocation
	 */
	public PositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	/**
	 * Returns the default textual representation of a PositionExpression.
	 */
	public String getString(Task task){
		int[] pos = evaluate(task);
		return Expression.positionToString(pos);
	}
}
