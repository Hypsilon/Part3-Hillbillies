package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * A FriendExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class FriendExpression extends UnitExpression {

	/**
	 * Constructor for the FriendExpression.
	 * 
	 * @param sourceLocation
	 */
	public FriendExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Evaluate the task: return a unit of the same faction (if it exists).
	 * 
	 * @param task
	 * @return a unit of the same faction, if it exists
	 */
	@Override
	public Unit evaluate(Task task) {
		Set<Unit> friends = new HashSet<Unit>(task.getAssignedUnit().getFaction().getUnits());
		friends.remove(task.getAssignedUnit());
		if (friends.isEmpty())
			return null;
		Iterator<Unit> ite = friends.iterator();
		Random rand = new Random();
		int stopIndex = rand.nextInt(friends.size());
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
	 * Return a textual representation of an friendly unit (if it exists).
	 * 
	 * @param task
	 * @return a textual representation of an friendly unit (if it exists)
	 */
	@Override
	public String getString(Task task) {
		if (this.evaluate(task) != null)
			return "any Friend";
		return "null";
	}

}
