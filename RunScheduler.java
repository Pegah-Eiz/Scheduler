/*
 * This program simulates the job scheduler inside a kernel using threads. The input is a text file containing 
 * jobs to be scheduled (numbered 1-4), job delay in milliseconds, and the job length in terms of milliseconds.
 * The output is a simulation of what the scheduler inside the kernel is doing along with 
 * a Gannt chart for the jobs. 
 */

import java.io.*;
import java.util.*;

/**
 * <p>Title: RunScheduler</p>
 * <p>Description: The driver class for the scheduler project.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Eastern Michigan University</p>
 * @author Matt Evett
 * @version 1.0
 * @author Clark Peters
 * @version 2.0
 */

import java.util.ArrayList;

/**
 * <p>Title: RunScheduler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015, 2004 by Matt Evett</p>
 * @author Matt Evett
 * @version 2.0
 * The main driver for the project.
 */

public class RunScheduler {

  private final static String INPUT_FILE_NAME = "scheduleInput.txt"; // filename

  public static void main(String[] args) {
    // set up for operating system simulator
    Thread thisThread = Thread.currentThread();
    thisThread.setPriority(Thread.MAX_PRIORITY);
    SystemSimulator kernel = new SystemSimulator(new FCFSScheduler());
    ArrayList<String> jobs = new ArrayList<String>();

    // Here you need to parse the input file, scheduleInput.txt, to create the jobs ArrayList.
    // For testing purposes, though, I'll just hardwire a three-value list.  The
    // RunScheduler that you submit should NOT have the following three lines.
    getJobsFromFile( jobs );
    
    WorkFactory sinecure = new WorkFactory();
    Submittor sub = new Submittor( jobs, kernel, sinecure);
    kernel.setPriority(8);
    kernel.start(); // Thread executing this instruction has higher priority, so will continue to hold cpu
    sub.setPriority(7); 
    sub.start();
    // As the driver exits, os has the highest priority, so should get the cpu....
  }
  
  /**
   * Copies content of the input file into an array.  Each line of input
   * becomes one element of the array.
   */
  public static void getJobsFromFile( ArrayList<String> listOfLines )
  {
	  Scanner fileIn=null;  //(Initialization keeps compiler happy)
      try { // open file
          fileIn = new Scanner(new FileInputStream(INPUT_FILE_NAME));
      } catch (FileNotFoundException e) {
          System.out.println("Input file "+INPUT_FILE_NAME+" not found. ");
          System.exit(1);
      } 
      while( fileIn.hasNextLine() ) // iterate through each file line
    	  listOfLines.add( fileIn.nextLine() ); // Send line to ArrayList
      fileIn.close(); // close file
  }
}
  
