
package common;

/**
 *
 * @author eah1
 */
public class DependencyException extends Exception{
    
    /**
     * 
     * @param cause 
     */
    public DependencyException(Exception cause){
        super(cause);
    }
    
    /**
     * 
     * @param message 
     */
    public DependencyException(String message){
        super(message);
    }
    
}
