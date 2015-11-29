package org.santa.step4;

import java.util.Random;

public class Reindeer implements Runnable {

	public enum ReindeerState {
		AT_BEACH, AT_WARMING_SHED, AT_THE_SLEIGH
	};
	
	Thread myThread = null;

	private ReindeerState state;
	private Scenario scenario;
	private Random rand = new Random();

	/**
	 * The identifier associated with the reindeer
	 */
	private int identifier;

	public Reindeer(int number, Scenario scenario) {
		this.identifier = number;
		this.scenario = scenario;
		this.state = ReindeerState.AT_BEACH;
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Reindeer " + identifier + " : " + state);
	};

	@Override
	public void run() {
		System.out.println("Reindeer " + this.identifier + " running with thread ID: " + Thread.currentThread().getId());
		myThread = Thread.currentThread();
		while (true) {
			// wait a day
			try {
				Thread.sleep(Main.DAY_LENGTH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ID:" + getThread().getId() + " has been inturrupted -- Reindeer");
				return;
			}
			// see what we need to do:
			switch (state) {
			case AT_BEACH: { // if it is December, the reindeer might think
								// about returning from the beach
				if (scenario.isDecember()) {
					if (rand.nextDouble() < 0.1) {
						state = ReindeerState.AT_WARMING_SHED;
					}
				}
				break;
			}
			case AT_WARMING_SHED:
				// if all the reindeer are home, wake up santa
				break;
			case AT_THE_SLEIGH:
				// keep pulling
				break;
			}
		}
	}
	
	public Thread getThread() {
		return myThread;
	}

}
