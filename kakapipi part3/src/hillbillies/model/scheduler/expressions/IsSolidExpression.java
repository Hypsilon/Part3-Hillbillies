package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;
import ogp.framework.util.ModelException;

/**
 * An IsSolidExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class IsSolidExpression extends BooleanExpression {

	/**
	 * Constructor for the IsSoidExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 * 			a position
	 */
	public IsSolidExpression(SourceLocation sourceLocation,
			PositionExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates the task: returns true if the position (subExpression) is solid, false otherwise.
	 * 
	 * @param task
	 */
	@Override
	public Boolean evaluate(Task task) {
		int[] pos = (int[]) this.getSubExpressions()[0].evaluate(task);
		try {
			return task.getWorld().getCube(pos[0], pos[1], pos[2]).isSolid();
		} catch (ModelException e) {
			System.out.println("Something went wrong in IsSolidExpression");
			e.printStackTrace();
		}
		return false;
	}
}
