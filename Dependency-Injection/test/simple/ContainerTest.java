
package simple;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import common.DependencyException;

import interfaces.InterfaceB;
import interfaces.InterfaceD;

import factorys_1.FactoryB1;
import factorys_1.FactoryD1;

import implement.ImplementationB1;
import implement.ImplementationD1;

/**
 *
 * @author eah1
 */
public class ContainerTest {
    
    private Injector injector;
    
    private InterfaceB b;
    private InterfaceD d;
    
    private ImplementationD1 d1;
    private ImplementationB1 b1;
    
    public ContainerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.injector = new Container(); 
    }

    @Test
    public void testRegisterConstant() throws DependencyException{
        
        System.out.println("registerConstant");
        
        String name = "I";
        int i = 5;
        
        this.injector.registerConstant(name, i);
        assertEquals(i, (int) this.injector.getObject("I"));
        
    }
    
    @Test
    public void testRegisterFactoryB1() throws Exception {
        
        System.out.println("registerFactoryB1");
        
        this.injector.registerConstant("D", this.d);
        this.injector.registerFactory("B", new	FactoryB1(), "D");
        
        this.b = (InterfaceB) this.injector.getObject("B");
        assertThat(this.b, is(instanceOf(ImplementationB1.class)));
        
        this.b1 = (ImplementationB1) this.b;
        assertThat(this.b1.d, is(this.d));
        
    }
    
    @Test
    public void testRegisterFactoryD1() throws Exception {
        
        System.out.println("registerFactoryD1");
        
        this.injector.registerConstant("I", 42);
        this.injector.registerFactory("D", new	FactoryD1(), "I");
        this.d = (InterfaceD) this.injector.getObject("D");
        assertThat(this.d, is(instanceOf(ImplementationD1.class)));
        this.d1 = (ImplementationD1) this.d;
        assertThat(d1.i, is(42));
        
    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterConstantRepead() throws DependencyException {
        
        System.out.println("testErrorRegisterConstantRepead");
        
        this.injector.registerConstant("I", 5);
        this.injector.registerConstant("I", 5);

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryRepead() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryRepead");
        String parameters[] = {"I"};
        
        this.injector.registerConstant("I", 42);
        this.injector.registerFactory("D", new	FactoryD1(), parameters);
        this.injector.registerFactory("D", new	FactoryD1(), parameters);

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryParameters() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryParameters");
        String parameters[] = {};
  
        this.injector.registerFactory("D", new	FactoryD1(), parameters);

    }
  
}
