package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * An Expression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 *
 * @param <T> the type of the expression
 */
public abstract class Expression<T> {
	
	private final SourceLocation sourceLocation;
	private final Expression<?>[] subExpressions;
	
	/**
	 * Constructor for the Expression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public Expression(SourceLocation sourceLocation, Expression<?>[] subExpressions) {
		this.sourceLocation = sourceLocation;
		this.subExpressions = subExpressions;
	}
	
	/**
	 * Constructor of the Expression class used for non-composed expressions.
	 * 
	 * @param sourceLocation
	 */
	public Expression(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
		this.subExpressions = null;
	}
	
	/**
	 * Evaluate the task.
	 * 
	 * @param task
	 */
	public abstract T evaluate(Task task);

	/**
	 * Return the textual representation of this expression.
	 * 
	 * @param task
	 */
	public abstract String getString(Task task);
	
	/**
	 * Return the string representation of the position.
	 * 
	 * @param pos
	 * @return string representation of the position
	 */
	protected static String positionToString(int[] pos) {
		return "(" + pos[0] + ", " + pos[1] + ", " + pos[2] + ")";
	}
	
	/**
	 * @return the sourceLocation
	 */
	@Basic
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	
	/**
	 * @return the subExpressions
	 */
	@Basic
	protected Expression<?>[] getSubExpressions() {
		return subExpressions;
	}
}
