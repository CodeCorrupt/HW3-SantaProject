package org.santa.step4;

import java.util.Random;

public class Elf implements Runnable {
	
	final int ELVES_TO_WAKE = 1;
	final double ELF_ERROR = 0.01;

	enum ElfState {
		WORKING, TROUBLE, AT_SANTAS_DOOR
	};
	//The number associated with the Elf
	private int identifier;
	private ElfState state;
	private Thread myThread = null;
	private Scenario scenario = null;
	private Random rand = new Random();

	// Constructor
	public Elf(int identifier, Scenario scenario) {
		this.identifier = identifier;
		this.scenario = scenario;
		this.state = ElfState.WORKING;
	}

	public ElfState getState() {
		return state;
	}

	// Report about my state
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
				Thread.sleep(Scenario.DAY_LENGTH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ID:" + getThread().getId() + " has been inturrupted -- ELF");
				return;
			}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into trouble.
				if (rand.nextDouble() < ELF_ERROR) {
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
	
	public Thread getThread() {
		return myThread;
	}
	

	// Santa might call this function to fix the trouble
	public void setState(ElfState state) {
		this.state = state;
	}

}
