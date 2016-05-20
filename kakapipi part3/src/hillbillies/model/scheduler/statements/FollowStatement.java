package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.UnitExpression;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * A FollowStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class FollowStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param expression
	 * 			The unit to follow.
	 * @param sourceLocation
	 */
	public FollowStatement(UnitExpression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/**
	 * Makes the assigned unit start following the other unit.
	 */
	@Override
	public void execute(Task task) {
		task.getAssignedUnit().setFollowedUnit((Unit) this.getExpression().evaluate(task));
	}

	/**
	 * Returns true.
	 */
	@Override
	public boolean isExecutableByUnit() {
		return true;
	}
}
