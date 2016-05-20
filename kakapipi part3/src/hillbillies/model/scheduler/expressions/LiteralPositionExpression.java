package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * A LiteralPositionExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class LiteralPositionExpression extends PositionExpression {

	private final int[] position;
	
	/**
	 * Constructor for the LiteralPositioinExpression class.
	 * 
	 * @param sourceLocation
	 * @param x
	 * @param y
	 * @param z
	 */
	public LiteralPositionExpression(SourceLocation sourceLocation, int x, int y, int z) {
		super(sourceLocation);
		this.position = new int[]{x, y, z};
	}

	/**
	 * Evaluate the task: return the position.
	 * 
	 * @param task
	 */
	@Override
	public int[] evaluate(Task task) {
		return position;
	}

	/**
	 * @return the position
	 */
	@Basic
	protected int[] getPosition() {
		return position;
	}

}
