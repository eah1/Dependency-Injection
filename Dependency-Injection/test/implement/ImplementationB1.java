package implement;

import interfaces.InterfaceB;
import interfaces.InterfaceD;

/**
 *
 * @author eah1
 */

public class ImplementationB1 implements InterfaceB {
    
    public InterfaceD d;	
    
    public  ImplementationB1( InterfaceD d ){
        
        this.d = d;
        
    }	
    
}
