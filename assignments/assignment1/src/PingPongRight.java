
// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;

/**
 * @class PingPongRight
 *
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public static int mMaxIterations = 10;
    
    /**
     * Latch that will be decremented each time a thread exits.
     */
    public static CountDownLatch latch = new CountDownLatch(2); // XTODO - You fill in here
    
    /**
     * @class PlayPingPongThread
     *
     * @brief This class implements the ping/pong processing algorithm
     *         using the SimpleSemaphore to alternate printing "ping"
     *         and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread
    {
        private SimpleSemaphore semaphore01;
        private SimpleSemaphore semaphore02;
		private int count;
		/**
         * Constructor initializes the data member.
         * @param semPing 
         * @param semPong 
         * @param countDown 
         */
    	
        public PlayPingPongThread (String msg, SimpleSemaphore semPing, SimpleSemaphore semPong, int count )
        {
            // DONE - You fill in here.
        	this.printMessage = msg;
        	this.semaphore01 = semPing;
        	this.semaphore02 = semPong;
        	this.count = count;
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run () 
        {
            // DONE - You fill in here.
        	for (int i = 1 ; i <= this.count ; i++) {
        	try {     		
        		this.semaphore01.acquire();
        		display(this.printMessage+"("+i+")");
        		this.semaphore02.release();
			} catch (InterruptedException e) {
				// XTODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        	latch.countDown();
        }
        private void display(String msg){
        	System.out.println(msg);
        }
        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // DONE - You fill in here.
        private String printMessage;

        /**
         * The two SimpleSemaphores use to alternate pings and pongs.
         */
        // DONE - You fill in here.
        // Semaphores are defined within the main 
    }

    /**
     * The main() entry point method into PingPongRight program. 
     */
    public static void main(String[] args) {
        try {         
            // Create the ping and pong SimpleSemaphores that control
            // alternation between threads.
        
        	SimpleSemaphore semPing = new SimpleSemaphore(1,true);
        	SimpleSemaphore semPong = new SimpleSemaphore(0,true);
        	

            // DONE - You fill in here.

            System.out.println("Ready...Set...Go!");

            // Create the ping and pong threads, passing in the string
            // to print and the appropriate SimpleSemaphores.
            
            PlayPingPongThread ping =
                new PlayPingPongThread("Ping!",semPing,semPong, mMaxIterations);
            PlayPingPongThread pong =
                new PlayPingPongThread("Pong!",semPong,semPing, mMaxIterations);
            
            // Initiate the ping and pong threads, which will call the
            // run() hook method.
            ping.start();
            pong.start();

            // Use barrier synchronization to wait for both threads to
            // finish.
            
            // DONE - replace replace the following line with a
            // CountDownLatch barrier synchronizer call that waits for
            // both threads to finish.
            latch.await();
            throw new java.lang.InterruptedException();
        } 
        catch (java.lang.InterruptedException e)
            {}

        System.out.println("Done!");
    }
}
