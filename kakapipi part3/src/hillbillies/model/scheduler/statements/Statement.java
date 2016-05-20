package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

import java.util.Collections;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A Statement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public abstract class Statement {
	
	private final SourceLocation sourceLocation; 
	
	private final Expression<?> expression;
	
	/**
	 * The next statement when executing the task.
	 */
	protected Statement nextStatement;
	/**
	 * The next statement in the program (txt file).
	 */
	protected Statement nextStatementInProgram;
	
	/**
	 * The constructor for this class.
	 * @param expression
	 * @param sourceLocation
	 * 
	 * @post	The expression is equal to the given expression.
	 * @post	The sourceLocation is equal to the given sourceLocation.
	 */
	public Statement(Expression<?> expression, SourceLocation sourceLocation) {
		this.expression = expression;
		this.sourceLocation = sourceLocation;
	}

	/**
	 * Executes this Statement.
	 * @param task
	 */
	public abstract void execute(Task task);
	
	/**
	 * @return the expression
	 */
	@Basic
	public Expression<?> getExpression() {
		return expression;
	}

	/**
	 * @return the sourceLocation
	 */
	@Basic
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	
	/**
	 * Returns true iff this statement is executable by a unit.
	 * @return
	 */
	public boolean isExecutableByUnit() {
		return false;
	}

	/**
	 * Returns all breakStatements on the scope of this statement in a set.
	 * @return
	 */
	public Set<Statement> getBreakStatements() {
		return Collections.emptySet();
	}
	
	/**
	 * Returns the next Statement.
	 * @return the nextStatement
	 */
	public Statement getNextStatement(Task task) {
		return nextStatement;
	}

	/**
	 * @param nextStatement the nextStatement to set
	 */
	@Basic
	public void setNextStatement(Statement nextStatement) {
		this.nextStatement = nextStatement;
	}

	/**
	 * @return the nextStatementInProgram
	 */
	@Basic
	public Statement getNextStatementInProgram() {
		return nextStatementInProgram;
	}

	/**
	 * @param nextStatementInProgram the nextStatementInProgram to set
	 */
	@Basic
	public void setNextStatementInProgram(Statement nextStatementInProgram) {
		this.nextStatementInProgram = nextStatementInProgram;
	}
}
