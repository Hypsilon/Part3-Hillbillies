package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.BooleanExpression;
import hillbillies.part3.programs.SourceLocation;

import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A WhileStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class WhileStatement extends Statement{

	private final Statement body;
	
	/**
	 * The constructor for this class.
	 * @param condition
	 * 			The condition of this while loop.
	 * @param body
	 * 			The body to execute in this while loop.
	 * @param sourceLocation
	 */
	public WhileStatement(BooleanExpression condition, Statement body, SourceLocation sourceLocation) {
		super(condition, sourceLocation);
		this.body = body;
	}
	
	/**
	 * 
	 */
	public void execute(Task task) {}

	/**
	 * @return the body
	 */
	@Basic
	protected Statement getBody() {
		return body;
	}

	/**
	 * Sets the next statement of this equal to body and the next statement of body equal to this.
	 * Also sets the next statement of all break statements inside this while loop equal to next.
	 */
	@Override
	public void setNextStatement(Statement next) {
		body.setNextStatement(this);
		this.nextStatement = next;
		Set<Statement> breakStatements = body.getBreakStatements();
		for (Statement breakStatement : breakStatements) 
			breakStatement.setNextStatement(next);
	}
	
	/**
	 * If the condition evaluates to true then return body else return nextStatement.
	 */
	@Override
	public Statement getNextStatement(Task task) {
		if ((boolean)(this.getExpression().evaluate(task)))
			return body;
		else
			return nextStatement;
	}
	
	/**
	 * Sets the next statement in program of this equal to body and that of body equal to stat.
	 */
	@Override
	public void setNextStatementInProgram(Statement stat) {
		this.nextStatementInProgram = body;
		body.setNextStatementInProgram(stat);
	}
}
