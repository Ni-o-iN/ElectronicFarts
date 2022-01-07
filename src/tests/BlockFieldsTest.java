package tests;

import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import appUI.BlockFields;
import gamelogic.GameLogic;

/**
 * 
 * @author emre aydemir
 * @version 0.1 Tests for the BlockFields-Class
 */

public class BlockFieldsTest {

	private BlockFields blocker = new BlockFields();
	private GameLogic logic = new GameLogic();
	private Scanner input = new Scanner(System.in);

	
	
	/**
	 * *Tests if the user input is a valid input*
	 */
	@Test
	public static void testIfInputIsValid() {

	}
	// So sollten die Testmethoden zur Klasse BlockFields aussehen:

	/**
	 * *Tests if the BlockFields initialisation method works*
	 */
	@Ignore
	@Test
	public static void testInitBlockFields() {
		/*
		 * hier sollte u.A. getestet werden, ob Felder geblockt werden koennen, ob
		 * falsche Eingaben akzeptiert werden usw...
		 */

	}

	/**
	 * *Tests if placing blocks works*
	 */
	@Ignore
	@Test
	public static void testPlaceBlock() {

	}
}
