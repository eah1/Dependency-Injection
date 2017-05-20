package implement;

import interfaces.InterfaceA;
import interfaces.InterfaceB;
import interfaces.InterfaceC;

/**
 *
 * @author eah1
 */

public class ImplementationA1 implements InterfaceA {
    
    public InterfaceB b;
    public InterfaceC c;
    
    public ImplementationA1( InterfaceB b, InterfaceC c ) {
        
        this.b = b;
        this.c = c;
        
    }
    
}
