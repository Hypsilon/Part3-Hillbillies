package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.PositionExpression;
import hillbillies.part3.programs.SourceLocation;
import ogp.framework.util.ModelException;

/**
 * A MoveToStatement class for the game Hillbillies
 * @author HF corp.
 * @version 1.0
 */
public class MoveToStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param expression
	 * 			The position to move to.
	 * @param sourceLocation
	 */
	public MoveToStatement(PositionExpression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/**
	 * Makes the assigned unit move towards the position in this.getExpression().
	 */
	@Override
	public void execute(Task task) {
		int[] position = (int[]) this.getExpression().evaluate(task);
		if (position == null) {
			System.out.println("Something went wrong (class MoveToStatement, method execute(Task))");
			task.getAssignedUnit().stopTask();
			return;
		}
		try {
			task.getAssignedUnit().moveTo(position[0], position[1], position[2]);
		} catch (ModelException e) {
		}
	}
	
	/**
	 * Returns true.
	 */
	public boolean isExecutableByUnit() {
		return true;
	}
}
