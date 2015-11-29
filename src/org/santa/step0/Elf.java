package org.santa.step0;

import java.util.Random;

public class Elf implements Runnable {

	enum ElfState {
		WORKING, TROUBLE, AT_SANTAS_DOOR
	};

	private ElfState state;
	/**
	 * The number associated with the Elf
	 */
	private int identifier;
	private Random rand = new Random();
	private Scenario scenario;

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
		while (true) {
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into
				// trouble.
				if (rand.nextDouble() < 0.01) {
					state = ElfState.TROUBLE;
				}
				break;
			}
			case TROUBLE:
				// FIXME: if possible, move to Santa's door
				break;
			case AT_SANTAS_DOOR:
				// FIXME: if feasible, wake up Santa
				break;
			}
		}
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
