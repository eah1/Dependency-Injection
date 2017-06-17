
package simple;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class Container implements Injector{

    private Store storeF;
    private Store storeC;
    
    /**
     * Constructor de la classe Container. 
     * Iniciarem el Store de Factory i de Constant.
     */
    public Container(){
        this.storeF = new StoreFactory();
        this.storeC = new StoreConstant();
    }
    
    /**
     * Metode per registrar Constants.
     * @param name Clau en que es guardara al Map de Constants.
     * @param value Valor en que es guardara al Map de Constants.
     * @throws DependencyException 
     */
    @Override
    public void registerConstant(String name, Object value) 
            throws DependencyException {
        
        if (!this.storeC.checkElement(name)) {
            this.storeC.addElement(name, value);
        } else throw new DependencyException("Constant duplicada");
    
    }

    /**
     * Els parametres, creator i parameters, els ficarem en una Pair de Factory, 
     * Object[] que sera el nostre valor del Map de Factorys.
     * @param name Clau en que es guardara al Map de Factorys.
     * @param creator Factory que es guardara.
     * @param parameters Parametres relacionats en la Factory.
     * @throws DependencyException 
     */
    @Override
    public void registerFactory(String name, Factory creator, String... parameters) 
            throws DependencyException {
        
        if (!this.storeF.checkElement(name) && parameters.length > 0) {
            
            Pair<Factory, Object[]> factory;
            factory = new Pair(creator, (Object[])parameters);
            
            this.storeF.addElement(name, factory);
            
        } else throw new DependencyException("Factory duplicada");
        
    }

    /**
     * Metode per obtenir els valors de les Contants o de les Factorys.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte, que tindra el valor de la Contants o de les Factorys.
     * @throws DependencyException 
     */
    @Override
    public Object getObject(String name) 
            throws DependencyException {
        
        if (this.storeC.checkElement(name)) {
            return this.storeC.getElement(name);
        } else if (this.storeF.checkElement(name)) {
            
            Pair<Factory, Object[]> factory = 
                    (Pair<Factory, Object[]>) this.storeF.getElement(name);
            
            Object[] argv = this.getParameters(factory.getValue());
            
            return factory.getKey().create(argv);
            
        } else throw new DependencyException("Element no trobat");    
    
    }
    
    /**
     * Metode, per obtenir els valors dels parametres de les Factorys.
     * @param parameters Parametres relacionats en la Factory.
     * @return Array de Object, amb els valors dels parametres.
     * @throws DependencyException 
     */
    private Object[] getParameters(Object[] parameters) 
            throws DependencyException {
        
        Object[] argv = new Object[parameters.length];
        int i = 0;
        
        for (Object parameter : parameters) {

            if (this.storeC.checkElement((String) parameter)) {
                argv[i] = this.getObject((String) parameter);
                i++;
            } else throw new DependencyException("Error arguments");
            
        }
        
        return argv;
        
    }
    
}
