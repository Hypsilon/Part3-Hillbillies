package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A BooleanExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public abstract class BooleanExpression extends Expression<Boolean> {

	/**
	 * Constructor for the BooleanExpression class.
	 * 
	 * @param sourceLocation
	 */
	public BooleanExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Constructor for the BooleanExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 * 				Any subExpressions of the BooleanExpression.
	 */
	public BooleanExpression(SourceLocation sourceLocation, Expression<?>[] subExpressions) {
		super(sourceLocation, subExpressions);
	}
	
	/**
	 * Returns "true" if this expression evaluates to true, "false" if that is not the case.
	 * 
	 * @param task 
	 */
	@Override
	public String getString(Task task) {
		//Since evaluate always returns a boolean, this returns "true" or "false".
		return String.valueOf(this.evaluate(task));
	}
}
