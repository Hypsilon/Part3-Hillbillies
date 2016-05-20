package hillbillies.tests.model;

import static org.junit.Assert.*;

import hillbillies.model.scheduler.expressions.Expression;
import hillbillies.model.scheduler.expressions.LiteralPositionExpression;
import hillbillies.model.scheduler.expressions.ThisExpression;
import hillbillies.model.scheduler.expressions.TrueExpression;
import hillbillies.model.scheduler.statements.PrintStatement;
import hillbillies.model.scheduler.statements.Statement;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.part3.programs.TaskFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskFactoryTest {
	
	private TaskFactory taskFactory;
	private Expression<?> position, unit, bool;
	private Statement statement;
	private static final SourceLocation srcLoc = new SourceLocation(-1, -1);
	
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
		this.position = new LiteralPositionExpression(srcLoc, 1, 2, 1);
		this.unit = new ThisExpression(srcLoc);
		this.bool = new TrueExpression(srcLoc);
		this.statement = new PrintStatement(position, srcLoc);
		this.taskFactory = new TaskFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void createWhileValid() {
		assertNotEquals(taskFactory.createWhile(bool, statement, srcLoc), null);
	}
	
	@Test
	public void createWhileInvalid() {
		assertEquals(taskFactory.createWhile(position, statement, srcLoc), null);
	}
	
	@Test
	public void createIfValid() {
		assertNotEquals(taskFactory.createIf(bool, statement, statement, srcLoc), null);
	}
	
	@Test
	public void createIfInvalid() {
		assertEquals(taskFactory.createIf(position, statement, statement, srcLoc), null);
	}
	
	@Test
	public void createMoveToValid() {
		assertNotEquals(taskFactory.createMoveTo(position, srcLoc), null);
	}
	
	@Test
	public void createMoveToInvalid() {
		assertEquals(taskFactory.createMoveTo(bool, srcLoc), null);
	}
	
	@Test
	public void createWorkValid() {
		assertNotEquals(taskFactory.createWork(position, srcLoc), null);
	}
	
	@Test
	public void createWorkInvalid() {
		assertEquals(taskFactory.createWork(bool, srcLoc), null);
	}
	
	@Test
	public void createFollowValid() {
		assertNotEquals(taskFactory.createFollow(unit, srcLoc), null);
	}
	
	@Test
	public void createFollowInvalid() {
		assertEquals(taskFactory.createFollow(bool, srcLoc), null);
	}
	
	@Test
	public void createAttackValid() {
		assertNotEquals(taskFactory.createAttack(unit, srcLoc), null);
	}
	
	@Test
	public void createAttackInvalid() {
		assertEquals(taskFactory.createAttack(bool, srcLoc), null);
	}
	
	@Test
	public void createIsSolidValid() {
		assertNotEquals(taskFactory.createIsSolid(position, srcLoc), null);
	}
	
	@Test
	public void createIsSolidInvalid() {
		assertEquals(taskFactory.createIsSolid(bool, srcLoc), null);
	}
	
	@Test
	public void createIsPassableValid() {
		assertNotEquals(taskFactory.createIsPassable(position, srcLoc), null);
	}
	
	@Test
	public void createIsPassableInvalid() {
		assertEquals(taskFactory.createIsPassable(bool, srcLoc), null);
	}
	
	@Test
	public void createIsFriendValid() {
		assertNotEquals(taskFactory.createIsFriend(unit, srcLoc), null);
	}
	
	@Test
	public void createIsFriendInvalid() {
		assertEquals(taskFactory.createIsFriend(bool, srcLoc), null);
	}
	
	@Test
	public void createIsEnemyValid() {
		assertNotEquals(taskFactory.createIsEnemy(unit, srcLoc), null);
	}
	
	@Test
	public void createIsEnemyInvalid() {
		assertEquals(taskFactory.createIsEnemy(bool, srcLoc), null);
	}
	
	@Test
	public void createIsAliveValid() {
		assertNotEquals(taskFactory.createIsAlive(unit, srcLoc), null);
	}
	
	@Test
	public void createIsAliveInvalid() {
		assertEquals(taskFactory.createIsAlive(bool, srcLoc), null);
	}
	
	@Test
	public void createCarriesItemValid() {
		assertNotEquals(taskFactory.createCarriesItem(unit, srcLoc), null);
	}
	
	@Test
	public void createCarriesItemInvalid() {
		assertEquals(taskFactory.createCarriesItem(bool, srcLoc), null);
	}
	
	@Test
	public void createNotValid() {
		assertNotEquals(taskFactory.createNot(bool, srcLoc), null);
	}
	
	@Test
	public void createNotInvalid() {
		assertEquals(taskFactory.createNot(unit, srcLoc), null);
	}
	
	@Test
	public void createAndValid() {
		assertNotEquals(taskFactory.createAnd(bool, bool, srcLoc), null);
	}
	
	@Test
	public void createAndInvalid() {
		assertEquals(taskFactory.createAnd(unit, position, srcLoc), null);
	}
	
	@Test
	public void createOrValid() {
		assertNotEquals(taskFactory.createOr(bool, bool, srcLoc), null);
	}
	
	@Test
	public void createOrInvalid() {
		assertEquals(taskFactory.createOr(unit, position, srcLoc), null);
	}
	
	@Test
	public void createNextToPositionValid() {
		assertNotEquals(taskFactory.createNextToPosition(position, srcLoc), null);
	}
	
	@Test
	public void createNextToPositionInvalid() {
		assertEquals(taskFactory.createNextToPosition(unit, srcLoc), null);
	}
	
	@Test
	public void createPositionOfValid() {
		assertNotEquals(taskFactory.createPositionOf(unit, srcLoc), null);
	}
	
	@Test
	public void createPositionOfInvalid() {
		assertEquals(taskFactory.createPositionOf(position, srcLoc), null);
	}
}
