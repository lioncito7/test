package com.simulator.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.simulator.service.SimulatorService;

public class SimulatorTest {

	SimulatorService simulatorService = new SimulatorService();
	
	String input;
	String[] inputs;
	
	@Test
	public void invalidNumberParameterShouldReturnErrorMessage()  {
		input ="Simulator";
		inputs = input.split(" ");
		 assertEquals(false, simulatorService.validateInputs(inputs));
	}
	@Test
	public void amountNotNumberShouldReturnErrorMessage()  {
		input ="Simulator Market.csv 2we";
		inputs = input.split(" ");
		assertEquals(false, simulatorService.validateInputs(inputs));
	}

	@Test
	public void amountLessThan1000ShouldReturnErrorMessage()  {
		input ="Simulator Market.csv 900";
		inputs = input.split(" ");
		assertEquals(false, simulatorService.validateInputs(inputs));
	}
	
	@Test
	public void amountMoreThan15000ShouldReturnErrorMessage()  {
		input ="Simulator Market.csv 16000";
		inputs = input.split(" ");
		assertEquals(false, simulatorService.validateInputs(inputs));
	}
	
	@Test
	public void amountNot100IncrementShouldReturnErrorMessage()  {
		input ="Simulator Market.csv 1550";
		inputs = input.split(" ");
		assertEquals(false, simulatorService.validateInputs(inputs));
	}
	
	@Test
	public void wrongFileNameShouldReturnErrorMessage()  {
		input ="Simulator Marwer.csv 1500";
		inputs = input.split(" ");
		assertEquals(false, simulatorService.validateInputs(inputs));
	}
}