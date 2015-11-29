package org.santa.step4;

import org.santa.step4.Elf.ElfState;

public class Santa implements Runnable {
	
	enum SantaState {
		SLEEPING, READY_FOR_CHRISTMAS, WOKEN_UP_BY_ELVES, WOKEN_UP_BY_REINDEER
	};
	
	Scenario scenario = null;
	
	Thread myThread = null;
	
	private SantaState state;

	public Santa(Scenario scenario) {
		this.state = SantaState.SLEEPING;
		this.scenario = scenario;
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Santa : " + state);
	}

	@Override
	public void run() {
		System.out.println("Santa running with thread ID: " + Thread.currentThread().getId());
		myThread = Thread.currentThread();
		while (!getThread().isInterrupted()) {
			// wait a day...
			try {
				Thread.sleep(Main.DAY_LENGTH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ID:" + getThread().getId() + " has been inturrupted -- Santa");
				return;
			}
			switch (state) {
				case SLEEPING: // if sleeping, continue to sleep
					break;
				case WOKEN_UP_BY_ELVES:
					// FIXME: help the elves who are at the door and go back to
					// Help all evles at the door
					for (Elf elf : scenario.getElves()) {
						if ( elf.getState() == ElfState.AT_SANTAS_DOOR )
							elf.setState(ElfState.WORKING);
					}
					// sleep
					state = SantaState.SLEEPING;
					break;
				case WOKEN_UP_BY_REINDEER:
					// FIXME: assemble the reindeer to the sleigh then change state
					// to ready
					break;
				case READY_FOR_CHRISTMAS: // nothing more to be done
					break;
			}
		}
	}
	
	public void wakeUpByElves() {
		state = SantaState.WOKEN_UP_BY_ELVES;
		return;
	}
	
	public Thread getThread() {
		return myThread;
	}

}
