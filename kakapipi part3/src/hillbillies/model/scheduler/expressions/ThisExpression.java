package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * A ThisExpression class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class ThisExpression extends UnitExpression {

	/**
	 * The constructor for this class.
	 * @param sourceLocation
	 */
	public ThisExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Returns the assigned unit of this task.
	 */
	@Override
	public Unit evaluate(Task task) {
		return task.getAssignedUnit();
	}

	/**
	 * Returns "this" if the assigned unit is not null, otherwise returns "no assigned unit"
	 */
	@Override
	public String getString(Task task) {
		if (this.evaluate(task) == null)
			return "no assigned unit";
		return "this";
	}

}
