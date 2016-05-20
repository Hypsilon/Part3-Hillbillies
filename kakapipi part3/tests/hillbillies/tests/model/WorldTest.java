package hillbillies.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hillbillies.model.world.Boulder;
import hillbillies.model.world.Carryable;
import hillbillies.model.world.Carryable.CarryableType;
import hillbillies.model.world.CubeType;
import hillbillies.model.world.Log;
import hillbillies.model.world.Unit;
import hillbillies.model.world.Vector;
import hillbillies.model.world.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

import java.util.Set;

import ogp.framework.util.ModelException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void removeCubes_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Set<int[]> cubesToRemove = world.getCubesToRemove();
		world.removeCube(3,3,5);
		int[] cube = cubesToRemove.iterator().next();
		assertTrue(cube[0] == 3 && cube[1] == 3 && cube[2] == 5);
	}
	
	@Test
	public void addCarryable_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Boulder boulder = new Boulder(new Vector(10, 10, 11));
		world.addCarryable(boulder);
		assertTrue(world.getCarryables().contains(boulder));
	}
	
	@Test
	public void getLogs_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Log log = new Log(new Vector(3,3,7));
		world.addCarryable(log);
		assertTrue(world.getLogs().contains(log));
	}
	
	@Test
	public void getBoulders_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Boulder boulder = new Boulder(new Vector(3,3,7));
		world.addCarryable(boulder);
		assertTrue(world.getBoulders().contains(boulder));
	}
	
	@Test
	public void getCarryableAt_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Carryable log = new Log(new Vector(2,2,4));
		world.addCarryable(log);
		assertTrue(world.getCarryableAt(2, 2, 4) == log);
	}
	
	@Test
	public void getCarryableAtPositionOfType_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Carryable log = new Log(new Vector(2,2,4));
		world.addCarryable(log);
		assertTrue(world.getCarryableAtPositionOfType(2, 2, 4, CarryableType.LOG) == log);
	}
	
	@Test
	public void addUnit_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 0, "Job", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		world.addUnit(testUnit);
		assertTrue(world.getUnits().contains(testUnit));
	}
	
	@Test
	public void isWalkable_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		assertTrue(world.isWalkable(new int[]{0,0,0}));
	}
	
	@Test
	public void isWalkable_Invalid_Neighbours() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		assertFalse(world.isWalkable(new int[]{0,0,15}));
	}
	
	@Test
	public void getCube_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		try {
			assertTrue(world.getCube(0, 0, 0) == CubeType.AIR);
		} catch (ModelException e) {
			//Should never happen
		}
	}
	
	//Getters and setters
	
	@Test
	public void getSizeX_Valid() {
		//sizeY and sizeZ are the same as this one.
		World world = new World(new int[20][30][40], new DefaultTerrainChangeListener());
		assertTrue(world.getSizeX() == 20);
	}
	
	@Test
	public void getTerrainTypeInt_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		assertTrue(world.getTerrainType(0, 0, 0) == CubeType.AIR.getTypeInt());
	}
	
	@Test
	public void getTimeTillCaveIn_Valid() {
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		world.setTimeTillCaveIn(20);
		assertTrue(world.getTimeTillCaveIn() == 20);
	}
}
