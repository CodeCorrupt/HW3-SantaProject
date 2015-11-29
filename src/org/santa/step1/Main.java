package org.santa.step1;


public class Main {

	public static void main(String args[]) {
		
		//Final variables
		final int TOTAL_DAYS = 500;
		final int KILL_DAY = 370;
		final int NUM_ELVES = 10;
		final int NUM_REINDEER = 9;
		
		Scenario scenario = new Scenario();
		// create the participants
		// Santa
		scenario.setSanta( new Santa(scenario) );
		Thread th = new Thread(scenario.getSanta());
		th.start();
		System.out.println(th.getId());
		// The elves: in this case: 10
		for (int i = 0; i != NUM_ELVES; i++) {
			Elf elf = new Elf(i + 1, scenario);
			scenario.getElves().add(elf);
			th = new Thread(elf);
			th.start();
		}
		// The reindeer: in this case: 9
		for (int i = 0; i != NUM_REINDEER; i++) {
			Reindeer reindeer = new Reindeer(i + 1, scenario);
			scenario.getReindeers().add(reindeer);
			th = new Thread(reindeer);
			th.start();
		}
		// now, start the passing of time
		for (int day = 0; day < TOTAL_DAYS; day++) {
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
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("********** Main thread Interrupted **********");
				//e.printStackTrace();
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
