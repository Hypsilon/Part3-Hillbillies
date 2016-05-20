package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.UnitExpression;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * An AttackStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class AttackStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param expression
	 * 			The unit to attack.
	 * @param sourceLocation
	 */
	public AttackStatement(UnitExpression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/**
	 * Makes the assigned unit start attacking the other unit. If the other unit is null stop the assigned unit's task.
	 */
	@Override
	public void execute(Task task) {
		Unit victim = (Unit) this.getExpression().evaluate(task);
		if (victim == null) {
			task.getAssignedUnit().stopTask();
			return;
		}
		task.getAssignedUnit().attack(victim);
	}
	
	/**
	 * Returns true.
	 */
	@Basic
	public boolean isExecutableByUnit() {
		return true;
	}
}
