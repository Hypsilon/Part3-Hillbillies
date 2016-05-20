package hillbillies.tests.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hillbillies.model.world.Faction;
import hillbillies.model.world.Unit;
import hillbillies.model.world.Unit.State;
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

/**
 * A Test class to test the Unit class.
 * 
 * @author HF corp.
 * @version 1.0
 */
public class UnitTest {
	
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
	public void startSprint_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Jezus", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setState(State.WALKING);
		testUnit.startSprint();
		assertTrue(testUnit.isSprinting());
	}
	
	@Test
	public void stopSprint_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Job", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setState(State.WALKING);
		testUnit.startSprint();
		testUnit.stopSprint();
		assertFalse(testUnit.isSprinting());
	}
	
	@Test
	public void terminate_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Goliath", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.terminate();
		assertTrue(testUnit.getState() == State.NOTHING && testUnit.isTerminated());
	}
	
	@Test
	public void moveToAdjacent_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(0, 0, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
			World world = new World(new int[4][4][4], new DefaultTerrainChangeListener());
			world.addUnit(testUnit);
			testUnit.moveToAdjacent(1, 1, 0);
		} catch (ModelException e) {
			//not supposed to happen
		}
		assertTrue(testUnit.getState() == State.WALKING);
	}
	
	@Test
	public void moveToAdjacent_Invalid_UnwalkableCube() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
			World world = new World(new int[4][4][4], new DefaultTerrainChangeListener());
			world.addUnit(testUnit);
			testUnit.moveToAdjacent(1, 1, 1);
		} catch (ModelException e) {
			//not supposed to happen
		}
		assertFalse(testUnit.getState() == State.WALKING);
	}
	
	@Test
	public void moveTo_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
			World world = new World(new int[10][10][10], new DefaultTerrainChangeListener());
			world.addUnit(testUnit);
			testUnit.moveTo(8, 7, 0);
		} catch (ModelException e) {
			//not supposed to happen
		}
		assertTrue(testUnit.isHasEndGoal());
	}
	
	@Test
	public void moveTo_Invalid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
			World world = new World(new int[10][10][10], new DefaultTerrainChangeListener());
			world.addUnit(testUnit);
			testUnit.moveTo(8, 7, 5);
		} catch (ModelException e) {
			//not supposed to happen
		}
		assertFalse(testUnit.isHasEndGoal());
	}
	
	@Test
	public void attack_Valid() {
		Unit unit1 = null;
		Unit unit2 = null;
		try {
			unit1 = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
			unit2 = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//Should never happen
		}
		World world = new World(new int[2][2][2], new DefaultTerrainChangeListener());
		world.addUnit(unit1);
		world.addUnit(unit2);
		unit1.attack(unit2);
		assertTrue(unit1.getState() == State.ATTACKING);
	}
	
	@Test
	public void attack_InvalidFaction() {
		Unit unit1 = null;
		Unit unit2 = null;
		try {
			unit1 = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
			unit2 = new Unit(1, 1, 0, "Pff zoveel namen", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//Should never happen
		}
		World world = new World(new int[2][2][2], new DefaultTerrainChangeListener());
		world.addUnit(unit1);
		world.addUnit(unit2);
		unit1.setFaction(unit2.getFaction());
		unit1.attack(unit2);
		assertFalse(unit1.getState() == State.ATTACKING);
	}
	
	@Test
	public void attack_InvalidStatetime() {
		Unit testAttacker = null;
		Unit testDefender = null;
		try {
			testAttacker = new Unit(10, 10, 10, "Mattheus", 50, 50, 50, 50, false);
			testDefender = new Unit(11, 9, 10, "Judas", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen
		}
		testAttacker.setStatetime(10);
		testAttacker.attack(testDefender);
		assertFalse(testAttacker.getState() == State.ATTACKING);
	}
	
	@Test
	public void attack_InvalidTooFarAway() {
		Unit testAttacker = null;
		Unit testDefender = null;
		try {
			testAttacker = new Unit(10, 10, 10, "Noah", 50, 50, 50, 50, false);
			testDefender = new Unit(20, 25, 17, "Jona", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen
		}
		testAttacker.attack(testDefender);
		assertFalse(testAttacker.getState() == State.ATTACKING);
	}
	
	@Test
	public void startWork_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Ezechiel", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.startWork(10, 11, 10);
		assertTrue(testUnit.getState() == State.WORKING);
	}
	
	@Test
	public void startWork_InvalidStatetime() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Johannes", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setStatetime(3.14d);
		testUnit.startWork(10, 11, 10);
		assertFalse(testUnit.getState() == State.WORKING);
	}
	
	@Test
	public void startWork_InvalidState() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Jozef", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setState(State.DANCING);
		testUnit.startWork(10, 11, 10);
		assertFalse(testUnit.getState() == State.WORKING);
	}
	
	@Test
	public void startRest_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Abraham", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.startRest();
		assertTrue(testUnit.getState() == State.RESTING);
	}
	
	@Test
	public void startRest_Invalid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Isaak", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setStatetime(42);
		testUnit.startRest();
		assertFalse(testUnit.getState() == State.RESTING);
	}
	
	
	/**
	 * isValidPosition when called without a world.
	 */
	@Test
	public void isValidPositon_Valid() {
		assertTrue(World.isValidPosition(new Vector(13, 14, 15), null));
	}
	
	/**
	 * isValidPosition when called without a world.
	 */
	@Test
	public void isValidPositon_Invalid() {
		assertFalse(World.isValidPosition(new Vector(-5, 14, 15), null));
	}
	
	@Test
	public void isValidName_Valid() {
		assertTrue(Unit.isValidName("Lucifer"));
	}
	
	@Test
	public void isValidName_InvalidCharacter() {
		assertFalse(Unit.isValidName("Daniël"));
	}
	
	@Test
	public void isValidName_InvalidUppercase() {
		assertFalse(Unit.isValidName("schaap"));
	}
	
	@Test
	public void isValidName_InvalidLength() {
		assertFalse(Unit.isValidName("H"));
	}
		
	//Next are all the test methods for getters and setters.
	
	@Test
	public void getPosition_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Jakob", 50, 50, 50, 50, false);
			testUnit.setPosition(new Vector(12, 13, 14));
		} catch (ModelException e) {
			//not supposed to happen.
		}
		double x = testUnit.getPosition().getX();
		double y = testUnit.getPosition().getY();
		double z = testUnit.getPosition().getZ();
		assertTrue(Util.fuzzyEquals(x, 12) && Util.fuzzyEquals(y, 13) && Util.fuzzyEquals(z, 14));
	}
	
	@Test
	public void getName_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Milka", 50, 50, 50, 50, false);
			testUnit.setName("Milkanogmeer");
		} catch (ModelException e) {
			//not supposed to happen.
		}
		assertTrue(testUnit.getName().equals("Milkanogmeer"));
	}
	
	@Test
	public void isDefaultBehaviorEnabled_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Nachor", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setDefaultBehaviorEnabled(false);
		assertFalse(testUnit.isDefaultBehaviorEnabled());
	}
	
	@Test
	public void getState_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Sarah", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setState(State.DANCING);
		assertTrue(testUnit.getState().equals(State.DANCING));
	}
	
	@Test
	public void getOrientation_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Samuel", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setOrientation((1 + Math.sqrt(5)) / 2);
		assertTrue(Util.fuzzyEquals(testUnit.getOrientation(), (1 + Math.sqrt(5)) / 2));
	}
	
	@Test
	public void getWeight_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Abel", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setWeight(75);
		assertTrue(testUnit.getWeight() == 75);
	}
	
	@Test
	public void getStrength_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Simon", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setStrength(25);
		assertTrue(testUnit.getStrength() == 25);
	}
	
	@Test
	public void getAgility_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Maria", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setAgility(25);
		assertTrue(testUnit.getAgility() == 25);
	}
	
	@Test
	public void getToughness_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Lea", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setToughness(25);
		assertTrue(testUnit.getToughness() == 25);
	}
	
	@Test
	public void getCurrentHealth_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Efraim", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setCurrentHealth(testUnit.getHealth() / 2);
		assertTrue(Util.fuzzyEquals(testUnit.getCurrentHealth(), testUnit.getHealth() / 2));
	}
	
	@Test
	public void getCurrentStamina_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Adam", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setCurrentStamina(testUnit.getStamina() / 2);
		assertTrue(Util.fuzzyEquals(testUnit.getCurrentStamina(), testUnit.getStamina() / 2));
	}
	
	@Test
	public void isSprinting_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Ruth", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setState(State.WALKING);
		testUnit.setSprinting(true);
		assertTrue(testUnit.isSprinting());
	}
	
	@Test
	public void getStatetime_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Benjamin", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setStatetime(4);
		assertTrue(Util.fuzzyEquals(testUnit.getStatetime(), 4.0d));
	}
	
	@Test
	public void getEndGoal_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Emmanuel", 50, 50, 50, 50, false);
			testUnit.setEndGoal(new Vector(12, 13, 14));
		} catch (ModelException e) {
			//not supposed to happen.
		}
		double x = testUnit.getEndGoal().getX();
		double y = testUnit.getEndGoal().getY();
		double z = testUnit.getEndGoal().getZ();
		assertTrue(Util.fuzzyEquals(x, 12) && Util.fuzzyEquals(y, 13) && Util.fuzzyEquals(z, 14));
	}
	
	@Test
	public void getStart_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Salome", 50, 50, 50, 50, false);
			testUnit.setStart(new Vector(12, 13, 14));
		} catch (ModelException e) {
			//not supposed to happen.
		}
		double x = testUnit.getStart().getX();
		double y = testUnit.getStart().getY();
		double z = testUnit.getStart().getZ();
		assertTrue(Util.fuzzyEquals(x, 12) && Util.fuzzyEquals(y, 13) && Util.fuzzyEquals(z, 14));
	}
	
	@Test
	public void getCurrentGoal_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Samson", 50, 50, 50, 50, false);
			testUnit.setCurrentGoal(new Vector(12, 13, 14));
		} catch (ModelException e) {
			//not supposed to happen.
		}
		double x = testUnit.getCurrentGoal().getX();
		double y = testUnit.getCurrentGoal().getY();
		double z = testUnit.getCurrentGoal().getZ();
		assertTrue(Util.fuzzyEquals(x, 12) && Util.fuzzyEquals(y, 13) && Util.fuzzyEquals(z, 14));
	}
	
	@Test
	public void isHasEndGoal_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Sem", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setHasEndGoal(true);
		assertTrue(testUnit.isHasEndGoal());
	}
	
	@Test
	public void getBusytime_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Thomas", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setBusytime(4.0d);
		assertTrue(Util.fuzzyEquals(testUnit.getBusytime(), 4.0d));
	}
	
	@Test
	public void isHasToRest_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Matthias ", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setHasToRest(true);
		assertTrue(testUnit.isHasToRest());
	}
	
	@Test
	public void getTimeToRest_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Elia", 50, 50, 50, 50, false);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		testUnit.setTimeToRest(4.0d);
		assertTrue(Util.fuzzyEquals(testUnit.getTimeToRest(), 4.0d));
	}
	
	@Test
	public void getVelocity_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 10, "Ismael", 50, 50, 50, 50, false);
			testUnit.setVelocity(new Vector(12, 13, 14));
		} catch (ModelException e) {
			//not supposed to happen.
		}
		double x = testUnit.getVelocity().getX();
		double y = testUnit.getVelocity().getY();
		double z = testUnit.getVelocity().getZ();
		assertTrue(Util.fuzzyEquals(x, 12) && Util.fuzzyEquals(y, 13) && Util.fuzzyEquals(z, 14));
	}
	
	@Test
	public void getWorld_Valid() {
		Unit testUnit = null;
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		try {
			testUnit = new Unit(10, 10, 0, "Nog meer naampjes", 50, 50, 50, 50, false);
			world.addUnit(testUnit);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		assertTrue(testUnit.getWorld().equals(world));
	}
	
	@Test
	public void getFaction_Valid() {
		Unit testUnit = null;
		World world = new World(new int[20][20][20], new DefaultTerrainChangeListener());
		Faction f = null;
		try {
			testUnit = new Unit(10, 10, 0, "Nog meer naampjes", 50, 50, 50, 50, false);
			world.addUnit(testUnit);
			f = world.getFactions().iterator().next();
		} catch (ModelException e) {
			//not supposed to happen.
		}
		assertTrue(testUnit.getFaction().equals(f));
	}
	
	@Test
	public void getWorkPosition_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 0, "Nog meer naampjes", 50, 50, 50, 50, false);
			testUnit.setWorkPosition(new int[]{10, 10, 0});
		} catch (ModelException e) {
			//not supposed to happen.
		}
		int[] workPosition = testUnit.getWorkPosition();
		assertTrue(workPosition[0] == 10 && workPosition[1] == 10 && workPosition[2] == 0);
	}
	
	@Test
	public void isResetPath_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 0, "Nog meer naampjes", 50, 50, 50, 50, false);
			testUnit.setResetPath(true);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		assertTrue(testUnit.isResetPath());
	}
	
	@Test
	public void getExperienceToNextLevel_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 0, "Nog meer naampjes", 50, 50, 50, 50, false);
			testUnit.setExperienceToNextLevel(23);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		assertTrue(testUnit.getExperienceToNextLevel() == 23);
	}
	
	@Test
	public void getExperience_Valid() {
		Unit testUnit = null;
		try {
			testUnit = new Unit(10, 10, 0, "Nog meer naampjes", 50, 50, 50, 50, false);
			testUnit.setExperience(666);
		} catch (ModelException e) {
			//not supposed to happen.
		}
		assertTrue(testUnit.getExperience() == 666);
	}
}
