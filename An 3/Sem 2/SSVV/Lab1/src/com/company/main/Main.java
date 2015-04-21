package com.company.main;

import com.company.persistance.Repository;
import com.company.controller.doctorController;

public class Main {
	
	public static void main(String[] args) 
	{
	
	Repository rep = new Repository("pat.txt", "cons.txt");
	doctorController dc = new doctorController(rep);		// it's on!
	
	}
	

}