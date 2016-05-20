package hillbillies.model.scheduler.statements;

import hillbillies.model.scheduler.Task;
import hillbillies.model.scheduler.expressions.BooleanExpression;
import hillbillies.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * An IfStatement class for the game Hillbillies.
 * @author HF corp.
 * @version 1.0
 */
public class IfStatement extends Statement {

	private final Statement ifBody, elseBody;
	
	/**
	 * The constructor of this class.
	 * @param expression
	 * 			A BooleanExpression; the condition for the IfStatement.
	 * @param ifBody
	 * 			The statement that should be executed when the condition evaluates to true.
	 * @param elseBody
	 * 			The statement that should be executed when the condition evaluates to false.
	 * @param sourceLocation
	 */
	public IfStatement(BooleanExpression expression,
			Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	/**
	 * 
	 */
	public void execute(Task task) {}

	/**
	 * @return the ifBody
	 */
	@Basic
	protected Statement getIfBody() {
		return ifBody;
	}

	/**
	 * @return the elseBody
	 */
	@Basic
	protected Statement getElseBody() {
		return elseBody;
	}
	
	/**
	 * Returns all breakStatements in both the ifBody and the elseBody as a set.
	 */
	@Override
	public Set<Statement> getBreakStatements() {
		Set<Statement> result = new HashSet<Statement>();
		result.addAll(ifBody.getBreakStatements());
		if (elseBody != null)
			result.addAll(elseBody.getBreakStatements());
		return result;
	}
	
	/**
	 * Sets the next statement of the ifBody equal to next, the next statement of elseBody equal to next and
	 * the next statement of this equal to elseBody (as long as elseBody != null)
	 * If elseBody is null then set the nextStatement of this equal to next.
	 */
	@Override
	public void setNextStatement(Statement next) {
		ifBody.setNextStatement(next);
		if (elseBody != null) {
			this.nextStatement = elseBody;
			elseBody.setNextStatement(next);
		} else
			this.nextStatement = next;
	}
	
	/**
	 * Returns the next statement to execute. If the condition evaluates to true returns ifBody
	 * else returns nextStatement (which is equal to elseBody if it is not null).
	 */
	@Override
	public Statement getNextStatement(Task task) {
		if ((boolean)(this.getExpression().evaluate(task)))
			return ifBody;
		else {
			return nextStatement;
		}
	}
	
	/**
	 * Sets the next statement of this equal to the ifBody.
	 * Sets the next statement of ifBody equal to elseBody. (as long as elseBody != null)
	 * Sets the next statement of elseBody equal to stat. (as long as elseBody != null)
	 * If elseBody == null then set the next statement of ifBody equal to stat.
	 */
	@Override
	public void setNextStatementInProgram(Statement stat) {
		this.nextStatementInProgram = ifBody;
		if (elseBody != null) {
			ifBody.setNextStatementInProgram(elseBody);
			elseBody.setNextStatementInProgram(stat);
		} else {
			ifBody.setNextStatementInProgram(stat);
		}
	}
}
