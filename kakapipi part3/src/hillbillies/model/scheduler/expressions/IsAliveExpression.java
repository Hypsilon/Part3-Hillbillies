package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * An IsAliveExpresion class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class IsAliveExpression extends BooleanExpression {

	/**
	 * Constructor for the IsAliveExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public IsAliveExpression(SourceLocation sourceLocation,
			UnitExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates the task: returns false if the other unit is terminated, true otherwise.
	 * 
	 * @param task
	 */
	@Override
	public Boolean evaluate(Task task) {
		Unit otherUnit = (Unit) this.getSubExpressions()[0].evaluate(task);
		return !otherUnit.isTerminated();
	}

}
