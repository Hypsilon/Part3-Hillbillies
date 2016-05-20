package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * An EndOfTaskStatement for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class EndOfTaskStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param sourceLocation
	 */
	public EndOfTaskStatement(SourceLocation sourceLocation) {
		super(null, sourceLocation);
	}

	/**
	 * Terminates the task.
	 */
	@Override
	public void execute(Task task) {
		task.terminate();
	}
}
