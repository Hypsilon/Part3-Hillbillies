package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

/**
 * A BeginOfTaskStatement for the game Hillbillies.
 * A BeginOfTaskStatement is created at the start of every Task.
 * @author HF corp.
 * @version 1.0
 */
public class BeginOfTaskStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param sourceLocation
	 */
	public BeginOfTaskStatement(SourceLocation sourceLocation) {
		super(null, sourceLocation);
	}

	/**
	 * 
	 */
	@Override
	public void execute(Task task) {}
}
