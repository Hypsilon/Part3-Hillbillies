package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.Set;

/**
 * A BreakStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class BreakStatement extends Statement {

	/**
	 * The constructor for this class.
	 * @param sourceLocation
	 */
	public BreakStatement(SourceLocation sourceLocation) {
		super(null, sourceLocation);
	}
	
	/**
	 * Returns a singleton {this}
	 */
	@Override
	public Set<Statement> getBreakStatements() {
		Set<Statement> result = new HashSet<Statement>();
		result.add(this);
		return result;
	}
	
	/**
	 * 
	 */
	@Override
	public void execute(Task task) {}
}
