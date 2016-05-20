package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

/**
 * A PrintStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class PrintStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param expression
	 * 			The expression to print.
	 * @param sourceLocation
	 */
	public PrintStatement(Expression<?> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	/**
	 * Prints this.getExpression().getString(task) to the console.
	 */
	@Override
	public void execute(Task task) {
		System.out.println(this.getExpression().getString(task));
	}
}
