package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A NotExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class NotExpression extends BooleanExpression {

	/**
	 * Constructor for the NotExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public NotExpression(SourceLocation sourceLocation,
			BooleanExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates this task: returns the not-expression.
	 * 
	 * @param task
	 */
	@Override
	public Boolean evaluate(Task task) {
		return ! (boolean)this.getSubExpressions()[0].evaluate(task);
	}
}
