package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;
/**
 * An AndExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class AndExpression extends BooleanExpression {

	/**
	 * Constructor for the AndExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 * 			An array of two booleanExpressions.
	 */
	public AndExpression(SourceLocation sourceLocation,
			BooleanExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates the task: returns the and-conjunction of two subExpressions.
	 * 
	 * @param task
	 * @return the and-conjunction of the two subExpressions
	 */
	@Override
	public Boolean evaluate(Task task) {
		return (boolean)this.getSubExpressions()[0].evaluate(task) && 
				(boolean)this.getSubExpressions()[1].evaluate(task);
	}
}
