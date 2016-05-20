package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * An AnyExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class AnyExpression extends UnitExpression {

	/**
	 * Constructor for the AnyExpression class.
	 * 
	 * @param sourceLocation
	 */
	public AnyExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Evaluates the task: returns any unit that is not the assignedUnit.
	 * 
	 * @param task
	 * @return any unit that is not the assigned unit
	 */
	@Override
	public Unit evaluate(Task task) {
		Set<Unit> all = new HashSet<Unit>(task.getWorld().getUnits());
		all.remove(task.getAssignedUnit());
		if (all.isEmpty())
			return null;
		Iterator<Unit> ite = all.iterator();
		Random rand = new Random();
		int stopIndex = rand.nextInt(all.size());
		int i = 0;
		while (ite.hasNext()) {
			Unit next = ite.next();
			if (i == stopIndex)
				return next;
			i++;
		}
		//Should never happen
		return null;
	}

	/**
	 * Returns a textual representation of any unit that is not the assigned unit.
	 * 
	 * @param task
	 * @return a textual representation of any unit that is not the assigned unit
	 */
	@Override
	public String getString(Task task) {
		if (this.evaluate(task) != null)
			return "any Unit";
		return "null";
	}

}
