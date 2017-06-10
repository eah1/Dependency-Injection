
package complex;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import common.DependencyException;

import interfaces.InterfaceA;
import interfaces.InterfaceB;
import interfaces.InterfaceC;
import interfaces.InterfaceD;

import factorys_2.FactoryA1;
import factorys_2.FactoryB1;
import factorys_2.FactoryC1;
import factorys_2.FactoryD1;

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
    
    private ImplementationA1 a1;
    private ImplementationB1 b1;
    private ImplementationC1 c1;
    private ImplementationD1 d1;
    
    private InterfaceA a;
    private InterfaceB b;
    private InterfaceC c;
    private InterfaceD d;
    
    public ContainerTest() {
        
    }
    
    @Before
    public void setUp() {
        this.injector = new Container(); 
    }
    
    @Test
    public void testRegisterConstant() throws Exception {
        
        System.out.println("registerConstant");

        this.injector.registerConstant(String.class, "Hola");
        assertEquals("Hola", (String) this.injector.getObject(String.class));
        
    }
    
    @Test
    public void testRegisterFactoryA1() throws DependencyException {
        
        System.out.println("registerFactoryA1");
        
        this.injector.registerConstant(InterfaceB.class, this.b);
        this.injector.registerConstant(InterfaceC.class, this.c);
        
        this.injector.registerFactory(InterfaceA.class, new FactoryA1(), 
            InterfaceB.class, InterfaceC.class);
        
        this.a = (InterfaceA) this.injector.getObject(InterfaceA.class);
        assertThat(this.a, is(instanceOf(ImplementationA1.class)));
                
        this.a1 = (ImplementationA1) this.a;
        assertThat(this.a1.b, is(this.b));
        assertThat(this.a1.c, is(this.c));
        
    }
    
    @Test
    public void testRegisterFactoryB1() throws DependencyException {
        
        System.out.println("registerFactoryB1");
        
        this.injector.registerConstant(InterfaceD.class, this.d);
        
        this.injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        
        this.b = (InterfaceB) this.injector.getObject(InterfaceB.class);
        assertThat(this.b, is(instanceOf(ImplementationB1.class)));
        
        this.b1 = (ImplementationB1) this.b;
        assertThat(this.b1.d, is(this.d));
        
    }

    @Test
    public void testRegisterFactoryC1() throws DependencyException {
        
        System.out.println("registerFactoryC1");
        
        this.injector.registerConstant(String.class, "Hola");
        
        this.injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        
        this.c = (InterfaceC) this.injector.getObject(InterfaceC.class);
        assertThat(this.c, is(instanceOf(ImplementationC1.class)));
        
        this.c1 = (ImplementationC1) this.c;
        assertThat(this.c1.s, is("Hola"));
        
    }
    
    @Test
    public void testRegisterFactoryD1() throws DependencyException {
        
        System.out.println("registerFactoryD1");
        
        this.injector.registerConstant(Integer.class, 26);
        
        this.injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        
        this.d = (InterfaceD) this.injector.getObject(InterfaceD.class);
        assertThat(this.d, is(instanceOf(ImplementationD1.class)));
        
        this.d1 = (ImplementationD1) this.d;
        assertThat(this.d1.i, is(26));
        
    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterConstantRepead() throws DependencyException {
        
        System.out.println("testErrorRegisterConstantRepead");
        
        this.injector.registerConstant(String.class, "Hola");
        this.injector.registerConstant(String.class, "Hola");

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryRepead() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryRepead");
        
        this.injector.registerConstant(String.class, "Hola");
        
        this.injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        this.injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryParameters1() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryParameters1");
  
        this.injector.registerFactory(InterfaceC.class, new FactoryC1());

    }
     
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryParameters2() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryParameters2");
  
        this.injector.registerConstant(InterfaceB.class, this.b);
        this.injector.registerConstant(InterfaceC.class, this.c);
        
        this.injector.registerFactory(InterfaceA.class, new FactoryA1(), 
            InterfaceB.class);
        
        this.a = (InterfaceA) this.injector.getObject(InterfaceA.class);
        
    }
    
}
