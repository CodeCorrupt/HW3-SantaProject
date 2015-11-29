package org.santa.step0;


public class Main {

	public static void main(String args[]) {
		Scenario scenario = new Scenario();
		// create the participants
		// Santa
		scenario.setSanta( new Santa(scenario) );
		Thread th = new Thread(scenario.getSanta());
		th.start();
		// The elves: in this case: 10
		for (int i = 0; i != 10; i++) {
			Elf elf = new Elf(i + 1, scenario);
			scenario.getElves().add(elf);
			th = new Thread(elf);
			th.start();
		}
		// The reindeer: in this case: 9
		for (int i = 0; i != 9; i++) {
			Reindeer reindeer = new Reindeer(i + 1, scenario);
			scenario.getReindeers().add(reindeer);
			th = new Thread(reindeer);
			th.start();
		}
		// now, start the passing of time
		for (int day = 1; day < 500; day++) {
			// wait a day
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// turn on December
			if (day > (365 - 31)) {
				scenario.setDecember(true);
			}
			// print out the state:
			System.out.println("***********  Day " + day
					+ " *************************");
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
