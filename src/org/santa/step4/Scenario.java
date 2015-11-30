package org.santa.step4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Scenario {
	
	private Santa santa;
	private List<Elf> elves;
	private List<Reindeer> reindeers;
	private boolean isDecember;
	

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
