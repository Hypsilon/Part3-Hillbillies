package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A TrueExpression class for the game Hillbillies
 * @author Floris
 *
 */
public class TrueExpression extends BooleanExpression {

	/**
	 * The constructor for this class.
	 * @param sourceLocation
	 */
	public TrueExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Returns true.
	 */
	@Override
	public Boolean evaluate(Task task) {
		return true;
	}
}
