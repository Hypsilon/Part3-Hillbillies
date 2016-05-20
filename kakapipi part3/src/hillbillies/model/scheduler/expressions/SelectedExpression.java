package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A SelectedExpression class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class SelectedExpression extends PositionExpression {

	/**
	 * The constructor for the SelectedExpression.
	 * @param sourceLocation
	 */
	public SelectedExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Returns the currently selected cube of this task.
	 */
	@Override
	public int[] evaluate(Task task) {
		return task.getSelected();
	}
}
