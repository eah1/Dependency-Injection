
package simple;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import common.DependencyException;

import interfaces.InterfaceA;
import interfaces.InterfaceB;
import interfaces.InterfaceC;
import interfaces.InterfaceD;

import factorys_1.FactoryA1;
import factorys_1.FactoryB1;
import factorys_1.FactoryC1;
import factorys_1.FactoryD1;

import implement.ImplementationA1;
import implement.ImplementationB1;
import implement.ImplementationC1;
import implement.ImplementationD1;

/**
 *
 * @author eah1
 */
public class ContainerTest {
    
    private Injector injector;
    
    private InterfaceA a;
    private InterfaceB b;
    private InterfaceC c;
    private InterfaceD d;
    
    private ImplementationA1 a1;
    private ImplementationB1 b1;
    private ImplementationC1 c1;
    private ImplementationD1 d1;

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
    public void testRegisterFactoryA1() throws Exception {
        
        System.out.println("registerFactoryA1");
        
        this.injector.registerConstant("B", this.b);
        this.injector.registerConstant("C", this.c);
        
        this.injector.registerFactory("A", new FactoryA1(), "B", "C");
        
        this.a = (InterfaceA) this.injector.getObject("A");
        assertThat(this.a, is(instanceOf(ImplementationA1.class)));
        
        this.a1 = (ImplementationA1) this.a;
        assertThat(this.a1.b, is(this.b));
        assertThat(this.a1.c, is(this.c));
        
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
    public void testRegisterFactoryC1() throws Exception {
        
        System.out.println("registerFactoryC1");
        
        this.injector.registerConstant("S", "Patata");
        
        this.injector.registerFactory("C", new	FactoryC1(), "S");
        
        this.c = (InterfaceC) this.injector.getObject("C");
        assertThat(this.c, is(instanceOf(ImplementationC1.class)));
        
        this.c1 = (ImplementationC1) this.c;
        assertThat(c1.s, is("Patata"));
        
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
        
        this.injector.registerConstant("I", 42);
        
        this.injector.registerFactory("D", new	FactoryD1(), "I");
        this.injector.registerFactory("D", new	FactoryD1(), "I");

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryParameters1() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryParameters1");
  
        this.injector.registerFactory("D", new	FactoryD1(), "");
        
        this.d = (InterfaceD) this.injector.getObject("D");

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryParameters2() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryParameters2");
        
        this.injector.registerConstant("B", this.b);
        this.injector.registerConstant("C", this.c);
        
        this.injector.registerFactory("A", new FactoryA1(), "B", "");
        
        this.a = (InterfaceA) this.injector.getObject("A");
        
    }
    
}
