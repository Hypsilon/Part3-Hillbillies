package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.CubeType;
import hillbillies.model.world.Unit;
import hillbillies.model.world.Vector;
import hillbillies.part3.programs.SourceLocation;

import java.util.Set;

/**
 * A WorkshopExpression class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class WorkshopPositionExpression extends PositionExpression {

	/**
	 * The constructor for this class.
	 * @param sourceLocation
	 */
	public WorkshopPositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Returns the position of the nearest workshop to the assigned unit.
	 */
	@Override
	public int[] evaluate(Task task) {
		Unit unit = task.getAssignedUnit();
		Set<int[]> workshops = unit.getWorld().getCubesOfType(CubeType.WORKSHOP);
		double minimumDistance = Double.MAX_VALUE;
		int[] minimumWorkshop = null;
		for (int[] pos : workshops) {
			Vector deltaPos = unit.getPosition().subtract(new Vector(pos[0], pos[1], pos[2]));
			double distance = deltaPos.length();
			if (distance < minimumDistance) {
				minimumDistance = distance;
				minimumWorkshop = pos;
			}
		}
		return minimumWorkshop;
	}

	/**
	 * Returns a textual representation of the position of the nearest workshop to the assigned unit.
	 * Returns "no workshop in world" if there are no workshops in the world. 
	 */
	@Override
	public String getString(Task task) {
		if (this.evaluate(task) != null)
			return Expression.positionToString(this.evaluate(task));
		return "no workshop in world";
	}

}
