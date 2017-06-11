
package complex;

import javafx.util.Pair;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public class Container<E> implements Injector<E>  {

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
     * @param <E>
     * @param name Clau en que es guardara al Map de Constants.
     * @param value Valor en que es guardara al Map de Constants.
     * @throws DependencyException 
     */
    @Override
    public <E> void registerConstant(Class<E> name, E value) 
            throws DependencyException {
        
        if (!this.storeC.checkElement(name)) {
            this.storeC.addElement(name, value);
        } else throw new DependencyException("Constant duplicada");
        
    }

    /**
     * Els parametres, creator i parameters, els ficarem en una Pair de Factory, 
     * Object[] que sera el nostre valor del Map de Factorys.
     * @param <E>
     * @param name Clau en que es guardara al Map de Factorys.
     * @param creator Factory que es guardara.
     * @param parameters Parametres relacionats en la Factory.
     * @throws DependencyException 
     */
    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, 
           Class<E>... parameters) throws DependencyException {
        
        E[] args = (E[]) parameters;
        
        if (!this.storeF.checkElement(name) && args.length > 0) {
            
            Pair<Factory<? extends E>, E> factory;
            factory = new Pair(creator, (Object[])args);
            
            this.storeF.addElement(name, factory);
            
        } else throw new DependencyException("Factory duplicada");
        
    }

    /**
     * Metode per obtenir els valors de les Contants o de les Factorys.
     * @param <E>
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte, que tindra el valor de la Contants o de les Factorys.
     * @throws DependencyException 
     */
    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        
        Object ob = null;
        
        if (this.storeC.checkElement(name)) {
            return (E) this.storeC.getElement(name);
        } else if (this.storeF.checkElement(name)) {
            
            Pair<Factory<? extends E>, E> factory = 
                    (Pair<Factory<? extends E>, E>) this.storeF.getElement(name);
            
            E[] argv = this.getParameters((E[]) factory.getValue());
            
            return factory.getKey().create(argv);
            
        } else throw new DependencyException("Element no trobat");
    }
    
    /**
     * Metode, per obtenir els valors dels parametres de les Factorys.
     * @param <E>
     * @param parameters Parametres relacionats en la Factory.
     * @return Array de Object, amb els valors dels parametres.
     * @throws DependencyException 
     */
    private <E> E[] getParameters(E[] parameters) 
            throws DependencyException {

        E[] argv = (E[]) new Object[parameters.length];
        Integer i = 0;
        
        for (E parameter : parameters) {
            
            if (this.storeC.checkElement((Class) parameter)) {
                argv[i] = (E) this.storeC.getElement((Class) parameter);
                i = i + 1;
            } else throw new DependencyException("Error arguments");
            
        }
        
        return argv;
        
    }
      
}
