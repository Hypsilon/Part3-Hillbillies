package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Unit;
import hillbillies.part3.programs.SourceLocation;

/**
 * An CarriesItemExpression class for the game Hillbillies.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class CarriesItemExpression extends BooleanExpression {
	
	/**
	 * Constructor for the CarriesItemExpression class.
	 * 
	 * @param sourceLocation
	 * @param subExpressions a unit
	 */
	public CarriesItemExpression(SourceLocation sourceLocation,
			UnitExpression[] subExpressions) {
		super(sourceLocation, subExpressions);
	}

	/**
	 * Evaluates the task: returns whethers a unit is carrying an item.
	 * 
	 * @param task
	 * @return true if the other unit is carrying a carryabe, false otherwise
	 */
	@Override
	public Boolean evaluate(Task task) {
		Unit otherUnit = (Unit) this.getSubExpressions()[0].evaluate(task);
		return otherUnit.isCarrying();
	}
}
