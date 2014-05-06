
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 *        Modified by Elio 06/05/2014
 */
public class SimpleSemaphore {
    private boolean fair;

	/**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,boolean fair)
    { 
        // DONE - you fill in here
    	this.countPermits = permits;
    	// I'm not sure of this parameter utility ??
    	this.fair = fair;
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        // DONE - you fill in here
    	rLock.lock();
        try {
          while (this.countPermits == 0) {
        	  zeroPermits.await();
          }
          this.countPermits--;
        } finally {
          rLock.unlock();
        }
      } 

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // DONE - you fill in here
    	rLock.lock();
        try {
          while (this.countPermits == 0) {
        	  zeroPermits.awaitUninterruptibly();
          }
          --this.countPermits;
        } finally {
          rLock.unlock();
        }
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // DONE - you fill in here
    	rLock.lock();
    	try {
    		this.countPermits++;
    		zeroPermits.signal();
        } finally {
          rLock.unlock();
        }
    }

    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // DONE - you fill in here
    private Lock rLock = new ReentrantLock(this.fair);

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // DONE - you fill in here
    final Condition zeroPermits  = rLock.newCondition();

    /**
     * Define a count of the number of available permits.
     */
    // DONE - you fill in here
    private int countPermits = 0;
}
