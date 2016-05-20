package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * A PositionOfExpression for the game Hillbillies
 * @author HF corp.
 * @version 1.0
 *
 */
public class PositionOfExpression extends PositionExpression {

	/**
	 * The constructor for the PositionOfExpression
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public PositionOfExpression(SourceLocation sourceLocation,
			UnitExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Returns the position of the unit of this expression.
	 */
	@Override
	public int[] evaluate(Task task) {
		return ((Unit) this.getSubExpressions()[0].evaluate(task)).getPosition().toIntArray();
	}
}
