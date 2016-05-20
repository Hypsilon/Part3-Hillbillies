package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * An IsFriendExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class IsFriendExpression extends BooleanExpression {

	/**
	 * Constructor for the IsFriendExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public IsFriendExpression(SourceLocation sourceLocation,
			UnitExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates the task: returns true if the other unit has the same faction, false otherwise.
	 * 
	 * @param task
	 */
	@Override
	public Boolean evaluate(Task task) {
		Unit unit = task.getAssignedUnit();
		Unit otherUnit = (Unit) this.getSubExpressions()[0].evaluate(task);
		return unit.getFaction() == otherUnit.getFaction();
	}
}
