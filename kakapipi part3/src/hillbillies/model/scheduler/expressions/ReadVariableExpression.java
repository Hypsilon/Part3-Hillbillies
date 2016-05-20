package hillbillies.model.scheduler.expressions;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * A ReadVariableExpression for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class ReadVariableExpression extends Expression<Object> {

	/**
	 * The name of the variable.
	 */
	private final String variableName;
	
	/**
	 * Constructor for the ReadVariableExpression.
	 * @param variableName
	 * 			The name of this variable.
	 * @param sourceLocation
	 */
	public ReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	/**
	 * Returns task.getVariable(this.getVariableName()).evaluate(task) as long as the variable has been instantiated,
	 * returns null otherwise.
	 */
	@Override
	public Object evaluate(Task task) {
		if (task.getVariable(this.getVariableName()) == null) {
			System.out.println("Trying to use an object before instantiating, line: " + this.getSourceLocation().getLine());
			return null;
		}
		return task.getVariable(this.getVariableName()).evaluate(task);
	}

	/**
	 * Returns the string of the variable; 
	 * | return task.getVariable(variableName).getString(task)
	 */
	@Override
	public String getString(Task task) {
		return task.getVariable(variableName).getString(task);
	}

	/**
	 * @return the variableName
	 */
	@Basic
	public String getVariableName() {
		return variableName;
	}

}
