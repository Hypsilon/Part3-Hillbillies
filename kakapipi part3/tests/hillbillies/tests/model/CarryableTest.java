package hillbillies.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hillbillies.model.world.Carryable;
import hillbillies.model.world.Log;
import hillbillies.model.world.Vector;
import hillbillies.model.world.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarryableTest {
	
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
	public void beingPickedUp_Valid() {
		Carryable carryable = new Log(new Vector(10, 10, 2));
		carryable.beingPickedUp();
		assertTrue(carryable.isPickedUp());
		
	}
	
	@Test
	public void beingDropped_Valid() {
		Carryable carryable = new Log(new Vector(10, 10, 2));
		carryable.beingDropped();
		assertFalse(carryable.isPickedUp());
		
	}
	
	@Test
	public void terminate_Valid() {
		Carryable carryable = new Log(new Vector(10, 10, 2));
		carryable.terminate();
		assertTrue(carryable.isTerminated());
	}
	
	@Test
	public void getPosition_Valid() {
		Carryable carryable = new Log(new Vector(10, 10, 2));
		try {
		carryable.setPosition(new Vector(11, 11, 3));
		} catch (ModelException e){
		}
		assertTrue(Util.fuzzyEquals(carryable.getPosition().getX(), 11) 
				&& Util.fuzzyEquals(carryable.getPosition().getY(), 11)
				&& Util.fuzzyEquals(carryable.getPosition().getZ(), 3));
	}
	
	@Test
	public void getWeight_Valid() {
		Carryable carryable = new Log(new Vector(10, 10, 2));
		carryable.setWeight(42);
		assertTrue(carryable.getWeight()== 42);
	}
	
	
	@Test
	public void getWorld_Valid() {
		Carryable carryable = new Log(new Vector(10, 10, 2));
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		carryable.setWorld(world);
		assertTrue(carryable.getWorld()== world);
	}
}
