
package complex;

import common.DependencyException;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import interfaces.InterfaceA;
import interfaces.InterfaceC;
import interfaces.InterfaceB;

import factorys_2.FactoryA1;
import factorys_2.FactoryC1;

import implement.ImplementationA1;
import implement.ImplementationC1;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author eah1
 */
public class ContainerTest {
    
    private Injector injector;
    
    private ImplementationA1 a1;
    private ImplementationC1 c1;
    
    private InterfaceA a;
    private InterfaceB b;
    private InterfaceC c;
    
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
        //Object parameters[] = {InterfaceB.class, InterfaceC.class};
        
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
    public void testRegisterFactoryC1() throws DependencyException {
        
        System.out.println("registerFactoryC1");
        //Object parameters[] = {String.class};
        
        this.injector.registerConstant(String.class, "Hola");
        this.injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        
        this.c = (InterfaceC) this.injector.getObject(InterfaceC.class);
        assertThat(this.c, is(instanceOf(ImplementationC1.class)));
        
        this.c1 = (ImplementationC1) this.c;
        assertThat(this.c1.s, is("Hola"));
        
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
        Object parameters[] = {String.class};
        
        this.injector.registerConstant(String.class, "Hola");
        this.injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        this.injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);

    }
    
    @Test( expected = DependencyException.class )
    public void testErrorRegisterFactoryParameters() throws DependencyException {
        
        System.out.println("testErrorRegisterFactoryParameters");
        Object parameters[] = {};
  
        this.injector.registerFactory(InterfaceC.class, new FactoryC1());

    }
      
}
