package org.santa.step4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Scenario {
	//Final variables
	public static final int DAY_LENGTH = 100; //in milliseconds
	public static final int TOTAL_DAYS = 500;
	public static final int KILL_DAY = 370;
	public static final int NUM_ELVES = 10;
	public static final int NUM_REINDEER = 0;//9;
	
	private Santa santa;
	private List<Elf> elves;
	private List<Reindeer> reindeers;
	private boolean isDecember;
	
	//Semaphore
	private Semaphore semElvesInTrouble = new Semaphore(3, true);
	

	// Initialize the lists etc
	public Scenario() {
		isDecember = false;
		santa = null;
		elves = new ArrayList<>();
		reindeers = new ArrayList<>();
		// Initialize Semaphores
		
	}

	public List<Elf> getElves() {
		return elves;
	}

	public List<Reindeer> getReindeers() {
		return reindeers;
	}

	public Santa getSanta() {
		return santa;
	}

	public boolean isDecember() {
		return isDecember;
	}

	public void setDecember(boolean isDecember) {
		this.isDecember = isDecember;
	}

	public void setSanta(Santa santa) {
		this.santa = santa;
	}

}
