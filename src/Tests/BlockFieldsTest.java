package Tests;

import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import appUI.BlockFields;
import gamelogic.GameLogic;

/**
 * 
 * @author emre aydemir
 * @version 0.1 Test zu der BlockFieldsTest-Klasse.
 */

public class BlockFieldsTest {

	private BlockFields blocker = new BlockFields();
	private GameLogic logic = new GameLogic();
	private Scanner input = new Scanner(System.in);

	@Test
	public static void testIfInputIsValid() {

	}
	// So sollten die Testmethoden zur Klasse BlockFields aussehen:

	/**
	 * *hier Methodenbeschreibung einfuegen*
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
	 * *hier Methodenbeschreibung einfuegen*
	 */
	@Ignore
	@Test
	public static void testPlaceBlock() {

	}
}
