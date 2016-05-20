package hillbillies.model.scheduler;

import hillbillies.model.scheduler.expressions.Expression;
import hillbillies.model.scheduler.expressions.ReadVariableExpression;
import hillbillies.model.scheduler.statements.AssignmentStatement;
import hillbillies.model.scheduler.statements.BeginOfTaskStatement;
import hillbillies.model.scheduler.statements.EndOfTaskStatement;
import hillbillies.model.scheduler.statements.Statement;
import hillbillies.model.world.Faction;
import hillbillies.model.world.Unit;
import hillbillies.model.world.World;
import hillbillies.part3.programs.SourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ogp.framework.util.ModelException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * A Task class for the game Hillbillies.
 * 
 * @invar 	The name of this task is not null.
 * 			| this.getName() != null
 * @invar	The beginStatement of this task is not null.
 * 			| this.begin != null
 * @invar	There is always a statement currently executing.
 * 			| this.currentlyExecuting != null
 * 
 * @author HF corp.
 * @version 1.0
 */
public class Task {
	
	private final List<Statement> activities;
	private Statement begin;
	private Statement currentlyExecuting;
	private int priority;
	private String name;
	private Unit assignedUnit;
	private World world;
	private int[] selected;
	private boolean terminated;
	
	private Map<String, Expression<?>> variables;
	
	/**
	 * Constructor for the task class.
	 * 
	 * @param priority 	
	 * 				The priority of the task.
	 * @param name	
	 * 				The name of the task.
	 * @param activities
	 * 				The list of activities this task has to execute.
	 * @param selected
	 * 				The currently selected cube.
	 * 
	 * @effect	Sets the priority.
	 * 			| setPriority(priority)
	 * @effect	Sets the name.
	 * 			| setName(name)
	 * @effect	Sets the selected.
	 * 			| setSelected(selected)
	 * @post	Creates the beginStatement, sets it to currently executing and sets its next Statement 
	 * 			equal to the first Statement of activities.
	 * 			| setCurrentlyExecuting(begin)
	 * 			| begin.setNextStatement(activities.get(0))
	 * @post	The activities next statement are all instantiated.
	 * 			| for int i = 0 to activities.size() - 1, i++ do
	 * 			|	activities.get(i).setNextStatement(activities.get(i+1))
	 * 			|	activities.get(i).setNextStatementInProgram(activities.get(i+1))
	 * @post	Creates the end statement and sets the final activity's next statement equal to this end statement.
	 * 			| activities.get(activities.size() - 1).getNextStatement() == endStatement
	 */
	public Task(int priority, String name, ArrayList<Statement> activities, int[] selected){
		this.activities = activities;
		setPriority(priority);
		setName(name);
		setSelected(selected);
		variables = new HashMap<String, Expression<?>>();
		
		SourceLocation beginLocation = new SourceLocation(-1, 1);
		begin = new BeginOfTaskStatement(beginLocation);
		setCurrentlyExecuting(begin);
		begin.setNextStatement(activities.get(0));
		begin.setNextStatementInProgram(activities.get(0));
		
		for (int i = 0; i < activities.size() - 1; i++) {
			activities.get(i).setNextStatement(activities.get(i+1));
			activities.get(i).setNextStatementInProgram(activities.get(i+1));
		}
		
		SourceLocation endLocation = new SourceLocation(-2, 1);
		EndOfTaskStatement endStatement = new EndOfTaskStatement(endLocation);
		activities.get(activities.size() - 1).setNextStatement(endStatement);
		endStatement.setNextStatement(null);
		endStatement.setNextStatementInProgram(null);
	}
	
	/**
	 * Checks whether this task is wellformed. Returns false if there are breaks outside of while loops or if there
	 * are variables that are used before assigning them a value, returns true otherwise.
	 * This method does not account for reassignment of variables and the scope of variables, this is done
	 * when the task is being executed.
	 * 
	 * @return
	 * 			If there are break statements that are not contained in a while loop return false.
	 * 			| for Statement activity in this.getActivities() do
	 * 			|	for Statement breakStatement in activity.getBreakStatements() do
	 * 			|		wellFormed = false
	 * 			
	 * 			If variables are used before assigning them a value return false.
	 * 			| vars = emptySet<String>
	 * 			| Statement curr = begin
	 * 			| while curr.getNextStatementInProgram() != null do
	 * 			|	curr = curr.getNextStatementInProgram()
	 * 			|	if curr instanceof AssignmentStatement then
	 * 			|		vars.add(curr.getVariableName())
	 * 			|	if curr.getExpression() instanceof ReadVariablExpression then
	 * 			|		if not vars.contains(curr.getExpression().getVariableName())
	 * 			|			wellFormed = false
	 * 
	 */
	public boolean isWellFormed() {
		boolean wellFormed = true;
		
		for (Statement activity : this.getActivities()) {
			Set<Statement> breaks = activity.getBreakStatements();
			for (Statement breakStatement : breaks) {
				System.out.println("Break statement outside of a while loop at line: " + breakStatement.getSourceLocation().getLine());
				wellFormed = false;
			}
		}
		
		Statement curr = begin;
		Set<String> vars = new HashSet<String>();
		while (curr.getNextStatementInProgram() != null) {
			curr = curr.getNextStatementInProgram();
			
			if (curr instanceof AssignmentStatement)
				vars.add(((AssignmentStatement) curr).getVariableName());
			
			if (curr.getExpression() instanceof ReadVariableExpression) {
				if (!vars.contains(((ReadVariableExpression) curr.getExpression()).getVariableName())) {
					System.out.println("Using a variable before assigning it a value at line: " + curr.getExpression().getSourceLocation().getLine());
					wellFormed = false;
				}
			}
		}
		
		return wellFormed;
	}
	
	/**
	 * Returns a set with all schedulers containing this task.
	 * @return
	 * 			| for faction in this.getWorld().getFactions() do
	 * 			|	if faction.getScheduler().containsTask(this) then
	 * 			|		result.add(faction.getScheduler())
	 * 			| return result
	 * @throws ModelException
	 * 			If this task does not have a world yet.
	 * 			| if this.getWorld() == null 
	 */
	public Set<Scheduler> getAllSchedulers() throws ModelException {
		if (this.getWorld() == null)
			throw new ModelException();
		Set<Scheduler> result = new HashSet<Scheduler>();
		for (Faction faction : this.getWorld().getFactions()) {
			if (faction.getScheduler().containsTask(this))
				result.add(faction.getScheduler());
		}
		return result;
	}
	
	/**
	 * Resets the task.
	 * 
	 * @effect 	Sets the beginStatement to currentlyExecuting.
	 * 			| setCurrentlyExecuting(begin)
	 * @effect	Clears the variables.
	 * 			| variables.clear()
	 * @effect 	Lower the priority of the task.
	 * 			| setPriority(this.getPriority()-2)
	 * @effect	Set the assigned unit of this task to null.
	 * 			| setAssignedUnit(nul)
	 */
	public void reset() {
		setCurrentlyExecuting(begin);
		
		variables.clear();
		
		setPriority(this.getPriority() - 2);
		
		setAssignedUnit(null);
	}
	
	/**
	 * Terminates the task.
	 * 
	 * @post 	Sets terminated to true and removes the task from all schedulers.
	 * 			| terminated = true
	 * 			| for faction in this.getWorld().getFactions() do
	 * 			|	faction.getScheduler().removeTask(this)
	 */
	public void terminate() {
		terminated = true;
		for (Faction faction : this.getWorld().getFactions()) {
			faction.getScheduler().removeTask(this);
		}
	}
	
	/**
	 * Returns true if the task is currently being executed by a unit, false otherwise.
	 * 
	 * @return	True if the task is being executed by a unit, false otherwise.
	 * 			| return assignedUnit != null
	 */
	public boolean beingExecuted(){
		return assignedUnit != null;
	}

	/**
	 * Execute the next activity that is executable by a unit.
	 * 
	 * @effect	While the nextStatement of the currentlyExecuting statement exists, set the currentlyExecuting
	 * 			statement to the nextStatement. If it is executable by a unit, return.
	 * 			| while (this.getCurrentlyExecuting().getNextStatement(this) != null) do
	 * 			| 	setCurrentlyExecuting(this.getCurrentlyExecuting().getNextStatement(this))
	 * 			|	this.getCurrentlyExecuting().execute(this)
	 * 			|	if (this.getCurrentlyExecuting().isExecutableByUnit()) then return
	 */
	public void ExecuteNextActivity() {
		while (this.getCurrentlyExecuting().getNextStatement(this) != null) {
			
			setCurrentlyExecuting(this.getCurrentlyExecuting().getNextStatement(this));
			
			this.getCurrentlyExecuting().execute(this);
			if (this.getCurrentlyExecuting().isExecutableByUnit()) 
				return;
		}
	}
	
	/**
	 * Adds the variable to the variables.
	 * 
	 * @param stat
	 * 			The statement.
	 * @param value
	 * 			The value of the variable.
	 * @effect	If the variable already exists in variables, return.
	 * 			| if (variables.containsKey(stat.getVariableName())) then return
	 * @effect	If the variable doesn't alreay exist in variables, put it in variables.
	 * 			| variables.put(stat.getVariableName(), value)
	 */
	public void addVariable(AssignmentStatement stat, Expression<?> value) {
		if (variables.containsKey(stat.getVariableName())) {
			System.out.println("Trying to reassign a value to a variable, line: " + stat.getSourceLocation().getLine());
			return;
		}
		variables.put(stat.getVariableName(), value);
	}
	
	/**
	 * Returns the name of the variable.
	 * 
	 * @param name
	 * 			The name of the of the variable.
	 * @return 	the name of the variable
	 * 			| return variables.get(name)
	 */
	@Basic
	public Expression<?> getVariable(String name) {
		return variables.get(name);
	}
	

	/**
	 * @return the priority
	 */
	@Basic
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	@Basic
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the name
	 */
	@Basic
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@Basic
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the assignedUnit
	 */
	@Basic
	public Unit getAssignedUnit() {
		return assignedUnit;
	}

	/**
	 * @param assignedUnit the assignedUnit to set
	 */
	@Basic
	public void setAssignedUnit(Unit assignedUnit) {
		this.assignedUnit = assignedUnit;
	}

	/**
	 * @return the selected
	 */
	@Basic
	public int[] getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	@Basic
	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	/**
	 * @return the currentlyExecuting
	 */
	@Basic
	public Statement getCurrentlyExecuting() {
		return currentlyExecuting;
	}

	/**
	 * @param currentlyExecuting the currentlyExecuting to set
	 */
	@Basic
	public void setCurrentlyExecuting(Statement currentlyExecuting) {
		this.currentlyExecuting = currentlyExecuting;
	}

	/**
	 * @return the terminated
	 */
	@Basic
	public boolean isTerminated() {
		return terminated;
	}

	/**
	 * @param world the world to set
	 */
	@Basic
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * @return the world
	 */
	@Basic
	public World getWorld() {
		return world;
	}

	/**
	 * @return the activities
	 */
	@Basic
	public List<Statement> getActivities() {
		return activities;
	}
}
