package hillbillies.model.scheduler.expressions;

import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * A UnitExpression class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public abstract class UnitExpression extends Expression<Unit> {

	/**
	 * The constructor for a composed UnitExpression.
	 * @param sourceLocation
	 * @param subExpressions
	 */
	public UnitExpression(SourceLocation sourceLocation,
			Expression<?>[] subExpressions) {
		super(sourceLocation, subExpressions);
	}
	
	/**
	 * The constructor for a non-composed UnitExpression.
	 * @param sourceLocation
	 */
	public UnitExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
}
