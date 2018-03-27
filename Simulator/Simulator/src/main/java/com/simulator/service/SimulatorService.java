package com.simulator.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.simulator.util.Constant;

@Service
public class SimulatorService {

	public boolean validateInputs(String[] input) {
		
		int amount;

		if(input.length !=3) {
			System.out.println("Not Valid number of parameters");
			return false;
		}
		try {
			amount = new Integer(input[2]).intValue();
		}catch(Exception e){
			System.out.println("Amount should be number");
			return false;
		}
		
		
		if(amount < Constant.MIN_AMOUNT || amount >Constant.MAX_AMOUNT) {
			System.out.println("Not Valid Amount:"+amount);
			return false;
		}
		
		if(amount % 100 !=0) {
			System.out.println("Not Valid Amount:"+amount);
			return false;			
		}
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(input[1]));			
		}catch(Exception e) {
			System.out.println("File Not Found");
			return false;
		}

		return true;
	}
	

	public void processSimulation(String fileName,String input) throws Exception {
		
		int amount = new Integer(input).intValue();
		double interest = getBestInterest(fileName,amount);
        if(interest == -1) {
       	    System.out.println("Is not possible to provide a quote at this time.");
        }else{
    		double  monthlyInterest = Math.pow(interest+1,1.0/12)-1;
    		double monthly = amount* (Math.pow(monthlyInterest +1, Constant.NUMBER_MONTHS)*monthlyInterest)/(Math.pow(monthlyInterest +1, Constant.NUMBER_MONTHS)-1);
    		double totalRepayment = monthly*Constant.NUMBER_MONTHS;
    		
    		System.out.println("Requested amount:"+amount);
    		System.out.println("Rate:"+String.format("%.1f", interest*100));
    		System.out.println("Monthly repayment:"+String.format("%.2f", monthly));
    		System.out.println("Total repayment:"+String.format("%.2f", totalRepayment));        		
        }
		

	}
	
	public double getBestInterest(String fileName,int amount) throws Exception {
		
		BufferedReader br = null;
		double min = -1;
		boolean interestAssigned = false;
		
		try {
			 String line;
			
			 br = new BufferedReader(new FileReader(fileName));
			 String[] lender;
			 int counter = 0;

	         while ((line = br.readLine()) != null) {
	        	 ++counter;
	        	 lender = line.split(",");

	             if(counter == 1 ) {
	            	 continue;
	             }
	             if(new Integer(lender[2]).intValue() >= amount) {
	            	 
	            	 if(!interestAssigned) {
	            		 min = new Double(lender[1]).doubleValue();
	            		 interestAssigned = true;
	            	 }else{
	            		 if(new Double(lender[1]).doubleValue() < min) {
	            			 min = new Double(lender[1]).doubleValue();
	            		 }
	            	 }
	             }
	         }	
	         

	         
        } catch (Exception e) {
            throw new Exception();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) { 
                }
            }
        }
		
        return min;
	}	
}
