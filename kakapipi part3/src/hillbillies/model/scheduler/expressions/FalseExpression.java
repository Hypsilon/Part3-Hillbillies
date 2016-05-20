package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A FalseExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class FalseExpression extends BooleanExpression {

	/**
	 * Constructor for the FalseExpression class.
	 * 
	 * @param sourceLocation
	 */
	public FalseExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Evaluate the task: return false.
	 * 
	 * @param task
	 * @return false
	 */
	@Override
	public Boolean evaluate(Task task) {
		return false;
	}

}
