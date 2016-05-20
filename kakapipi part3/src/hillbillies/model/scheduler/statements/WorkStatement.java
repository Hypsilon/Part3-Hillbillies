package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;

/**
 * A WorkStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class WorkStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param expression
	 * 			The position to work at.
	 * @param sourceLocation
	 */
	public WorkStatement(PositionExpression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/**
	 * Makes the assigned unit work at the position in expression.
	 */
	@Override
	public void execute(Task task) {
		int[] position = (int[]) this.getExpression().evaluate(task);
		if (position == null) {
			task.getAssignedUnit().stopTask();
			return;
		}
		task.getAssignedUnit().startWork(position[0], position[1], position[2]);
	}
	
	/**
	 * Returns true.
	 */
	public boolean isExecutableByUnit() {
		return true;
	}
}
