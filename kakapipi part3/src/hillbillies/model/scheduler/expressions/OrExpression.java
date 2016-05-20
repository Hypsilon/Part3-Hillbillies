package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * An OrExpression class for the game Hillbilies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class OrExpression extends BooleanExpression {

	/**
	 * Constructor for the OrExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 * 			An array of two BooleanExpressions.
	 */
	public OrExpression(SourceLocation sourceLocation,
			BooleanExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluate the task: returns the or-conjuntion of the two subExpressions.
	 * 
	 * @param task
	 */
	@Override
	public Boolean evaluate(Task task) {
		return (boolean)this.getSubExpressions()[0].evaluate(task) ||
				(boolean)this.getSubExpressions()[1].evaluate(task);
	}
}
