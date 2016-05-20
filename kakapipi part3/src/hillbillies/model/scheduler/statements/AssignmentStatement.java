package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * An AssignmentStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 *
 */
public class AssignmentStatement extends Statement {

	/**
	 * The name of the variable.
	 */
	private final String variableName;
	
	/**
	 * The constructor for this class.
	 * @param expression
	 * 			The value of the variable.
	 * @param name
	 * 			The name of the variable.
	 * @param sourceLocation
	 */
	public AssignmentStatement(Expression<?> expression, String name, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		this.variableName = name;
	}

	/**
	 * Create a new variable (in task) with this.getVariableName() as name and the this.getExpression() as value. 
	 */
	@Override
	public void execute(Task task) {
		task.addVariable(this, this.getExpression());
	}

	/**
	 * @return the variableName
	 */
	@Basic
	public String getVariableName() {
		return variableName;
	}

}
