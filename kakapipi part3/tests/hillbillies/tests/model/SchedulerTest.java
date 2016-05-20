package hillbillies.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hillbillies.model.scheduler.Scheduler;
import hillbillies.model.scheduler.Task;
import hillbillies.model.world.Faction;
import hillbillies.model.world.Unit;
import hillbillies.model.world.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.programs.TaskParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ogp.framework.util.ModelException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SchedulerTest {
	
	private Facade facade;
	
	private World world;
	private Unit unit;
	private Scheduler scheduler;
	private Faction faction;

	@SuppressWarnings("unused")
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.facade = new Facade();
		
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][2][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		this.world = facade.createWorld(types, new DefaultTerrainChangeListener());
		this.unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		this.facade.addUnit(unit, world);
		this.faction = facade.getFaction(unit);

		this.scheduler = facade.getScheduler(faction);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void addTaskValid() {
		List<Task> tasks;
		try {
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					Collections.singletonList(new int[] { 1, 2, 1 }));
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(1, tasks.size());
			Task task = tasks.get(0);

			scheduler.addTask(task);
			
			assertFalse(scheduler.getTasks().isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeTaskValid() {
		List<Task> tasks;
		try {
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					Collections.singletonList(new int[] { 1, 2, 1 }));
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(1, tasks.size());
			Task task = tasks.get(0);

			scheduler.addTask(task);
			
			scheduler.removeTask(task);
			
			assertTrue(scheduler.getTasks().isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addAllTasksValid() {
		List<Task> tasks;
		try {
			List<int[]> selectedCubes = new ArrayList<int[]>();
			selectedCubes.add(new int[]{1, 2, 1});
			selectedCubes.add(new int[]{0, 1, 0});
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					selectedCubes);
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(2, tasks.size());
						
			scheduler.addTasks(tasks);
			
			assertEquals(scheduler.getTasks().size(), 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeAllTasksValid() {
		List<Task> tasks;
		try {
			List<int[]> selectedCubes = new ArrayList<int[]>();
			selectedCubes.add(new int[]{1, 2, 1});
			selectedCubes.add(new int[]{0, 1, 0});
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					selectedCubes);
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(2, tasks.size());

			scheduler.addTasks(tasks);
			
			scheduler.removeTasks(tasks);
			
			assertTrue(scheduler.getTasks().isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void containsTaskValid() {
		List<Task> tasks;
		try {
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					Collections.singletonList(new int[] { 1, 2, 1 }));
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(1, tasks.size());

			scheduler.addTasks(tasks);
			
			assertTrue(scheduler.containsTask(tasks.get(0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void containsAllTasksValid() {
		List<Task> tasks;
		try {
			List<int[]> selectedCubes = new ArrayList<int[]>();
			selectedCubes.add(new int[]{1, 2, 1});
			selectedCubes.add(new int[]{0, 1, 0});
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					selectedCubes);
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly two tasks
			assertEquals(2, tasks.size());
			
			scheduler.addTasks(tasks);
			
			assertTrue(scheduler.containsTasks(tasks));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void setScheduledForUnitValid() {
		List<Task> tasks;
		try {
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					Collections.singletonList(new int[] { 1, 2, 1 }));
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one tasks
			assertEquals(1, tasks.size());
			
			scheduler.addTasks(tasks);
			try {
				scheduler.setScheduledForUnit(unit, tasks.get(0));
			} catch (ModelException e) {
				//Should never happen
			}
			
			assertTrue(unit.getTask() == tasks.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeTaskFromUnitValid() {
		List<Task> tasks;
		try {
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					Collections.singletonList(new int[] { 1, 2, 1 }));
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one tasks
			assertEquals(1, tasks.size());
			
			scheduler.addTasks(tasks);
			try {
				scheduler.setScheduledForUnit(unit, tasks.get(0));
			} catch (ModelException e) {
				//Should never happen
			}
			
			Scheduler.removeTaskFromUnit(unit);
			
			assertTrue(unit.getTask() == null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean positivePriority(Task task) {
		return task.getPriority() >= 0;
	}
	
	@Test
	public void getTasksByConditionValid() {
		List<Task> tasks;
		try {
			List<int[]> selectedCubes = new ArrayList<int[]>();
			selectedCubes.add(new int[]{1, 2, 1});
			selectedCubes.add(new int[]{0, 1, 0});
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					selectedCubes);
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly two tasks
			assertEquals(2, tasks.size());
			
			tasks.get(1).setPriority(1000000);
			tasks.get(0).setPriority(-10000000);
			
			scheduler.addTasks(tasks);
			
			try {
				Set<Task> taskPosPrior = scheduler.getTasksByCondition(SchedulerTest.class.getMethod("positivePriority", new Class[]{Task.class}));
				assertEquals(taskPosPrior.size(), 1);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (ModelException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getHighestPriorityTaskValid() {
		List<Task> tasks;
		try {
			List<int[]> selectedCubes = new ArrayList<int[]>();
			selectedCubes.add(new int[]{1, 2, 1});
			selectedCubes.add(new int[]{0, 1, 0});
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					selectedCubes);
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(2, tasks.size());

			tasks.get(0).setPriority(100);
			tasks.get(1).setPriority(20);
			
			scheduler.addTasks(tasks);
			try {
				scheduler.setScheduledForUnit(unit, tasks.get(0));
			} catch (ModelException e) {
				//Should never happen
			}
			
			Task t = scheduler.getHighestPriorityTask();
			
			assertTrue(t == tasks.get(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getPriorityIterator() {
		List<Task> tasks;
		try {
			List<int[]> selectedCubes = new ArrayList<int[]>();
			selectedCubes.add(new int[]{1, 2, 1});
			selectedCubes.add(new int[]{0, 1, 0});
			
			tasks = TaskParser.parseTasksFromFile(
					"resources/tasks/ownsimple.txt", facade.createTaskFactory(),
					selectedCubes);
		
			// tasks are created
			assertNotNull(tasks);
			// there's exactly one task
			assertEquals(2, tasks.size());

			tasks.get(0).setPriority(100);
			tasks.get(1).setPriority(20);
			
			scheduler.addTasks(tasks);
			
			Iterator<Task> ite = scheduler.getPriorityIterator();
			
			assertTrue(ite.next() == tasks.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
