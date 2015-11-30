package org.santa.step4;


public class Main {

	public static final int DAY_LENGTH = 100; //in milliseconds
	//Final variables
	public static final int TOTAL_DAYS = 500;
	public static final int KILL_DAY = 370;
	public static final int NUM_ELVES = 10;
	public static final int NUM_REINDEER = 0;//9;

	public static void main(String args[]) {
		
		Scenario scenario = new Scenario();
		// Initialize Santa
		scenario.setSanta( new Santa(scenario) );
		Thread th = new Thread(scenario.getSanta());
		th.start();
		System.out.println(th.getId());
		// Initialize the elves
		for (int i = 0; i != NUM_ELVES; i++) {
			Elf elf = new Elf(i + 1, scenario);
			scenario.getElves().add(elf);
			th = new Thread(elf);
			th.start();
		}
		// Initialize the reindeer
		for (int i = 0; i != NUM_REINDEER; i++) {
			Reindeer reindeer = new Reindeer(i + 1, scenario);
			scenario.getReindeers().add(reindeer);
			th = new Thread(reindeer);
			th.start();
		}
		
		// now, start the passing of time
		for (int day = 0; day < TOTAL_DAYS; day++) {
			// wait a day
			try {
				Thread.sleep(DAY_LENGTH);
			} catch (InterruptedException e) {
				System.out.println("********** Main thread Interrupted **********");
				return;
			}
			System.out.println("***********  Day " + (day+1) + " *************************");
			
			//Kill all if kill day
			if ( day+1 == KILL_DAY ) {
				System.out.println("It's Kill DAY!!!!");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Killing Santa");
				scenario.getSanta().getThread().interrupt();
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Killing Elves");
				for (Elf elf : scenario.getElves()) {
					elf.getThread().interrupt();
				}
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Killing Reindeer");
				for (Reindeer reindeer : scenario.getReindeers()) {
					reindeer.getThread().interrupt();
				}
			}
			
			// turn on December
			if (day > (365 - 31)) {
				scenario.setDecember(true);
			}
			
			// print out the state:
			scenario.getSanta().report();
			for (Elf elf : scenario.getElves()) {
				elf.report();
			}
			for (Reindeer reindeer : scenario.getReindeers()) {
				reindeer.report();
			}
		}
	}

}
