package org.santa.step2;

import java.util.Random;

public class Elf implements Runnable {
	
	final int ELVES_TO_WAKE = 1;
	final int ELVES_FOR_DOOR = 3;
	final double ELF_ERROR = 0.01;

	enum ElfState {
		WORKING, TROUBLE, AT_SANTAS_DOOR
	};
	
	Thread myThread = null;
	Scenario scenario = null;

	private ElfState state;
	/**
	 * The number associated with the Elf
	 */
	private int identifier;
	private Random rand = new Random();

	public Elf(int identifier, Scenario scenario) {
		this.identifier = identifier;
		this.scenario = scenario;
		this.state = ElfState.WORKING;
	}

	public ElfState getState() {
		return state;
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Elf " + identifier + " : " + state);
	}

	@Override
	public void run() {
		System.out.println("Elf " + this.identifier + " running with thread ID: " + Thread.currentThread().getId());
		myThread = Thread.currentThread();
		while (true) {
			// wait a day
			try {
				Thread.sleep(Main.DAY_LENGTH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ID:" + getThread().getId() + " has been inturrupted -- ELF");
				return;
			}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into
				// trouble.
				if (rand.nextDouble() < ELF_ERROR) {
					state = ElfState.TROUBLE;
				}
				break;
			}
			case TROUBLE:
				// FIXME: if possible, move to Santa's door
				if ( (elvesInTrouble() >= ELVES_FOR_DOOR) && (elvesAtDoor() == 0)) {
					//Send those elves to the door
					for (Elf elf : scenario.getElves()) {
						if ( elf.getState() == ElfState.TROUBLE )
							elf.setState(ElfState.AT_SANTAS_DOOR);
					}
				}
				break;
			case AT_SANTAS_DOOR:
				// FIXME: if feasible, wake up Santa
				//Check for elves at door
				if ( elvesAtDoor() >= ELVES_TO_WAKE ) {
					scenario.getSanta().wakeUpByElves();
				}
				break;
			}
		}
	}
	
	public Thread getThread() {
		return myThread;
	}
	
	public int elvesInTrouble() {
		int inTrouble = 0;
		for (Elf elf : scenario.getElves()) {
			if ( elf.getState() == ElfState.TROUBLE )
				inTrouble++;
		}
		return inTrouble;
	}
	
	public  int elvesAtDoor() {
		int atDoor = 0;
		for (Elf elf : scenario.getElves()) {
			if ( elf.getState() == ElfState.AT_SANTAS_DOOR )
				atDoor++;
		}
		return atDoor;
	}

	/**
	 * Santa might call this function to fix the trouble
	 * 
	 * @param state
	 */
	public void setState(ElfState state) {
		this.state = state;
	}

}
