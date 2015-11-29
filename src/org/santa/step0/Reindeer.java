package org.santa.step0;

import java.util.Random;

public class Reindeer implements Runnable {

	public enum ReindeerState {
		AT_BEACH, AT_WARMING_SHED, AT_THE_SLEIGH
	};

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
		while (true) {
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

}
