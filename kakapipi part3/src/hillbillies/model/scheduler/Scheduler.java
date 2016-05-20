package hillbillies.model.scheduler;

import hillbillies.model.world.Faction;
import hillbillies.model.world.Unit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ogp.framework.util.ModelException;
import be.kuleuven.cs.som.annotate.Basic;

/**
* A Scheduler class for the game Hillbillies.
* 
* @invar	The scheduler belongs to a certain faction.
* 			| this.getFaction() != null
* 
* @author HF corp.
* @version 1.0
*/
public class Scheduler {
	
	
	private Faction faction;
	private List<Task> tasks;
	
		/**
		 * Constructor for the Scheduler class.
		 * 
		 * @param faction
		 * 			The faction of this scheduler.
		 * @effect	This constructor creates a new scheduler of the given faction.
		 * 			| this(faction)
		 * @post	This scheduler gets a set of tasks, which is ordered using TaskComparator.
		 * 			| tasks = new TreeSet<Task>(new TaskComparator()) 
		 */
	public Scheduler(Faction faction){
		setFaction(faction);
		tasks = new ArrayList<Task>();
	}
	
	 /**
	  * Removes the task t from the scheduler.
	  * 
	  * @param t	The task to remove from the scheduler.
	  * @post 		The task is removed from the scheduler.
	  * 			| tasks.remove(t)
	  */
	public void removeTask(Task t){
		this.getTasks().remove(t);
		if (t.beingExecuted()) {
			if (t.getAssignedUnit().getFaction() == this.getFaction()) 
				t.getAssignedUnit().removeTask();
		}
	}
	
	/**
	 * Removes tasks from the scheduler.
	 * 
	 * @param tasks	The tasks to remove from the scheduler.
	 * @effect		The tasks are removed from the scheduler.
	 * 				|for task in tasks
	 * 				| 	removeTask(t)
	 */
	public void removeTasks(Collection<Task> tasks) {
		for (Task t : tasks) 
			removeTask(t);
	}
	
	/**
	 * Adds the task t to the scheduler.
	 * 
	 * @param t		The task to add to the scheduler.
	 * @post		The task is added to the scheduler.
	 * 				| tasks.add(t)
	 * @effect 		Sets the world of the scheduler to the world of the scheduler's faction.
	 * 				| t.setWorld(this.getFaction().getWorld())
	 */
	public void addTask(Task t) {
		boolean added = false;
		for (int i = 0; i < this.getTasks().size(); i++) {
			if (this.getTasks().get(i).getPriority() <= t.getPriority()) {
				this.getTasks().add(i, t);
				added = true;
				break;
			}
		}
		if (!added) {
			this.getTasks().add(this.getTasks().size(), t);
		}
		t.setWorld(this.getFaction().getWorld());
	}
	
	/**
	 * Adds all of the given tasks to tasks.
	 * @param tasks
	 * @effect	
	 * 			| for Task t in tasks
	 * 			|	addTask(t)
	 */
	public void addTasks(Collection<Task> tasks) {
		for (Task t : tasks) 
			addTask(t);
	}
	
	/**
	 * Replaces the original task with the replacement task in the scheduler.
	 * 
	 * @param original		The task to be removed from the scheduler.
	 * @param replacement	The task that will replace the removed task.
	 * @effect				The original task is removed from the scheduler and replaced by the replacement task.
	 * 						| removeTask(original)
	 * 						| addTask(replacement)
	 * 
	 */
	public void replaceTask(Task original, Task replacement){
		removeTask(original);
		addTask(replacement);
	}
	
	/**
	 * Checks whether the scheduler contains a task t. Returns true if this is the case and false when it is not so.
	 * 
	 * @param t		The task t that is checked to be in the scheduler.
	 * @return		If the scheduler contains this task t, return true. If this is not the case, return false.
	 * 				| if t in this.getTasks() then return true
	 * 				| else then return false
	 */
	public boolean containsTask(Task t){
		return this.getTasks().contains(t);
	}
	
	/**
	 * Checks whether the scheduler contains tasks. Returns true if this is the case and false when it is not so.
	 * 
	 * @param tasks 	The tasks that are checked to be in the scheduler.
	 * @return			If the scheduler contains the tasks, return true. If this is not the case, return false.
	 * 					| for t in tasks
	 * 					|	if not scheduler.contains(t) then return false
	 * 					| return true
	 */
	public boolean containsTasks(Collection<Task> tasks) {
		for (Task t : tasks) {
			if (!containsTask(t))
				return false;
		}
		return true;
	}
	
	/**
	 * Gives the task t to the given unit. 
	 * 
	 * @param unit	The unit that will be given the task.
	 * @param t		The task that will be given to the unit.
	 * @throws ModelException 
	 * 				If the given task is not a part of this scheduler.
	 * 				| if not this.containsTask(t)
	 * @effect		The unit is assigned task t.
	 * 				| unit.setTask(t)
	 * @effect		The task is assigned to the unit.
	 * 				| t.setAssignedUnit(unit)
	 * 			
	 */
	public void setScheduledForUnit(Unit unit, Task t) throws ModelException {
		if (!this.containsTask(t))
			throw new ModelException();	
		unit.setTask(t);
		t.setAssignedUnit(unit);
	}
	
	/**
	 * Stops the task of the given unit.
	 * 
	 * @param unit	
	 * @effect		If the unit has a task, the task is stopped. If the unit does not have a task, return.
	 * 				| unit.stopTask()
	 */
	public static void removeTaskFromUnit(Unit unit) {
		unit.stopTask();
	}
	
	
	/**
	 * Returns all task that adhere to the given condition.
	 * 
	 * @param cond
	 * 			The condition.
	 * @return
	 * 			Returns all tasks that adhere to the given condition.
	 * 			| result = EmptySet<Task>
	 * 			| for Task t in this.getTasks() do
	 * 			|	if cond.invoke(cond.getDeclaringClass(), t) then result.add(t)
	 * 			| return result
	 * @throws ModelException
	 * 			If the given Method is not a boolean.
	 * 			| if cond.getReturnType() != boolean.class
	 * 			If the given Method does not have exactly one parameter.
	 * 			| if cond.getParamterCount() != 1
	 * 			If the parameter is not of type Task
	 * 			| if cond.getParameterTypes()[0] != Task.class
	 */
	public Set<Task> getTasksByCondition(Method cond) throws ModelException {
		if (cond.getReturnType() != boolean.class)
			throw new ModelException();
		if (cond.getParameterCount() != 1)
			throw new ModelException();
		if (cond.getParameterTypes()[0] != Task.class)
			throw new ModelException();
		
		Set<Task> result = new HashSet<Task>();
		for (Task t : this.getTasks()) {
			try {
				if ((boolean) cond.invoke(cond.getDeclaringClass(), t))
					result.add(t);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Returns the highest priority task that is not being executed, otherwise returns null.
	 * 
	 * @return		Returns the task of the scheduler that has the highest priority and that is not being executed.
	 * 				Returns null if the scheduler has no tasks (or has only tasks that are already being executed). 
	 * 				| if this.getTasks().isEmpty() the return null
	 * 				| Iterator<Task> ite = getPriorityIterator();
	 * 				| while ite.hasNext() do
	 * 				|	Task t = ite.next()
	 * 				|	if not t.beingExecuted() then return t
	 * 				| return null
	 */
	public Task getHighestPriorityTask(){
		if (this.getTasks().isEmpty())
			return null;
		Iterator<Task> ite = getPriorityIterator();
		while (ite.hasNext()) {
			Task t = ite.next();
			if (!t.beingExecuted())
				return t;
		}
		return null;
	}
	
	/**
	 * Return an iterator returning all tasks in descending priority.
	 * 
	 * @return	an iterator returning all tasks in descending priority
	 * 			| this.getTasks().descenddingIterator()
	 */
	public Iterator<Task> getPriorityIterator() {
		return this.getTasks().iterator();
	}
	
	/**
	 * @return the faction
	 */
	@Basic
	public Faction getFaction() {
		return faction;
	}
	/**
	 * @param faction the faction to set
	 */
	@Basic
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	/**
	 * @return the tasks
	 */
	@Basic
	public List<Task> getTasks() {
		return tasks;
	}
}
