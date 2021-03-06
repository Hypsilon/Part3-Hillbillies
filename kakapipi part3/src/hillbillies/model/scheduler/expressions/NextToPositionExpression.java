package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A NextToPositionExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class NextToPositionExpression extends PositionExpression {

	/**
	 * Constructor for the NextToPosition class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public NextToPositionExpression(SourceLocation sourceLocation,
			PositionExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Returns a walkable cube position next to the given cube.
	 * Returns null if there are no walkable cubes next to this cube. 
	 * 
	 * @param taks
	 */
	@Override
	public int[] evaluate(Task task) {
		int[] pos = (int[]) this.getSubExpressions()[0].evaluate(task);
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					if (x == 0 && y == 0)
						continue;
					if (task.getWorld().isWalkable(new int[]{pos[0] + x, pos[1] + y, pos[2] + z}))
						return new int[]{pos[0] + x, pos[1] + y, pos[2] + z};
				}
			}
		}
		return null;
	}

	/**
	 * Returns the textual representation of a walkable cube position next to the given cube (if there are 
	 * walkable cubes next to the given cube.
	 * 
	 * @param task
	 */
	@Override
	public String getString(Task task) {
		int[] pos = this.evaluate(task);
		if (pos == null)
			return "no walkable position next to " + this.getSubExpressions()[0].getString(task);
		return Expression.positionToString(pos);
	}
}
