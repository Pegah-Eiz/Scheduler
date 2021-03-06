/**
 * <p>Title: FCFSScheduler</p>
 * <p>Description: Component of the simulate operating system that encapsulates FCFS job scheduling.</p>
 * <p>Copyright: Copyright (c) 2015, 2004</p>
 * <p>Company: </p>
 * @author Matt Evett
 * @version 2.0
 */

import java.util.concurrent.ConcurrentLinkedQueue;

public class FCFSScheduler extends Scheduler {

  /*
   * TO_DO: your data structure to support a FCFS scheduler
   * and the abstract methods of Scheduler
   */
	ConcurrentLinkedQueue<Job> fcfsQueue = new ConcurrentLinkedQueue<Job>();
	@Override
	public void add(Job J) 
	{
		fcfsQueue.add(J);
		
		
	}

	@Override
	public void remove(Job J) 
	{
		fcfsQueue.poll();

	}

	@Override
	public boolean hasJobsQueued() 
	{
		if(fcfsQueue.isEmpty())	
		return false;
		else
			return true;
	}
 
  /**
   * If the ready queue is empty, return false.
   * Otherwise, start the next job in the queue, returning true.  If the queue is empty
   * return false.
   * Make the next job in the ready queue run. You should probably
   * invoke Thread.start() on it.
   */
	  public boolean makeRun()
	  {   
		  if (fcfsQueue.isEmpty())
			  return false;
		  System.out.println("TO_DO: makeRun not yet implemented");

		  currentlyRunningJob = fcfsQueue.poll();
		  currentlyRunningJob.start();
		     return true; // TO_DO ***SHOULDN'T ALWAYS RETURN TRUE***
	  }

  
  /**
   * blockTilThereIsAJob()  Invoked by OS simulator when it wants to get a new Job to
   * run.  Will block if the ready queue is empty until a Job is added to the queue.
   */
  public  void  blockTilThereIsAJob() 
  {
	  if (hasRunningJob()) 
		  return;
	  System.out.println("TO_DO: blockTilThereIsAJob not yet implemented");
	  /*
	   * Place code here that will cause the calling thread to block until the ready queue
	   * contains a Job
	   */
	  while (fcfsQueue.isEmpty())
	  {
		 try {
			 currentlyRunningJob.sleep(10);
			//currentlyRunningJob.wait();
		} catch (InterruptedException e) {

		}
		 
	  }
	  
	  //currentlyRunningJob.notify();
	  System.out.println("evidently there is now a job on readyQ");
  }


}
  

