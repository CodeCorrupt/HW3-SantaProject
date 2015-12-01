package org.santa.step4;

import java.util.Random;

public class Elf implements Runnable {

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
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ID:" + getThread().getId() + " has been inturrupted -- ELF");
				return;
			}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into trouble.
				if (rand.nextDouble() < Scenario.ELF_ERROR) {
					state = ElfState.TROUBLE;
				}
				break;
			}
			case TROUBLE:
				// FIXME: if possible, move to Santa's door
				
				// Queue up to visit Santa 
				// 			** Only three can get past here.
				try {
					scenario.semElvesInTrouble.acquire();
				} catch (InterruptedException e) {
					return;
				}
				
				// Wait for there to be three
				try {
					scenario.semElvesWaiting.acquire();
				} catch (InterruptedException e) {
					return;
				}
				
				// Go to the door
				state = ElfState.AT_SANTAS_DOOR;
				
				break;
			case AT_SANTAS_DOOR:
				// FIXME: if feasible, wake up Santa
				scenario.getSanta().wakeByElves();
				
				// Wait for santa to wake up and help.
				try {
					scenario.semElvesGettingHelp.acquire();
				} catch (InterruptedException e) {
					return;
				}
				/*
				 * There are thread timing issues that cauase it to look as though elves are being helped before they go to the door. 
				 * If you enable the print statement below you can see that elves are always helped in batches of 3. 
				 */
				//System.out.println("Got Help");
				// Go back to work
				state = ElfState.WORKING;
				
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
