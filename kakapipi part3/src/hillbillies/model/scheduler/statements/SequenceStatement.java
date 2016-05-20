package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A SequenceStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class SequenceStatement extends Statement {
	
	/**
	 * The statements contained in this SequenceStatement.
	 */
	private final List<Statement> statements;
	
	/**
	 * The constructor for this class.
	 * @param statements
	 * @param sourceLocation
	 */
	public SequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		super(null, sourceLocation);
		this.statements = statements;
	}
	
	/**
	 * 
	 */
	public void execute(Task task) {}

	/**
	 * @return the statements
	 */
	@Basic
	protected List<Statement> getStatements() {
		return statements;
	}
	
	/**
	 * Returns all BreakStatements in this.getStatements() in the form of a set.
	 */
	public Set<Statement> getBreakStatements() {
		Set<Statement> result = new HashSet<Statement>();
		for (Statement statement : this.getStatements())
			result.addAll(statement.getBreakStatements());
		return result;
	}
	
	/**
	 * Sets the next statement of the i-th statement in this.getStatements() equal to the (i+1)-th statement in
	 * this.getStatements() (for i = 0.. this.getStatements().size - 2). Sets the next statement of the last
	 * statement in this.getStatements() equal to next.  
	 */
	@Override
	public void setNextStatement(Statement next) {
		if (this.getStatements().isEmpty()) {
			nextStatement = next;
		} else {
			nextStatement = this.getStatements().get(0);
			for (int i = 0; i < this.getStatements().size() - 1; i++) {
				this.getStatements().get(i).setNextStatement(this.getStatements().get(i+1));
			}
			this.getStatements().get(this.getStatements().size() - 1).setNextStatement(next);
		}
	}
	
	/**
	 * Does exactly the same as setNextStatement(Statement) but with nextStatementInProgram instead
	 * of nextStatement.
	 */
	@Override
	public void setNextStatementInProgram(Statement stat) {
		if (this.getStatements().isEmpty()) {
			nextStatementInProgram = stat;
		} else {
			nextStatementInProgram = this.getStatements().get(0);
			for (int i = 0; i < this.getStatements().size() - 1; i++) {
				this.getStatements().get(i).setNextStatementInProgram(this.getStatements().get(i+1));
			}
			this.getStatements().get(this.getStatements().size() - 1).setNextStatementInProgram(stat);
		}
	}
}
