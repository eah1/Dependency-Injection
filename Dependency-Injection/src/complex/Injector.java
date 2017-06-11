
package complex;

import common.DependencyException;

/**
 *
 * @author eah1
 * @param <E>
 */
public interface Injector<E> {
    
    /**
     * Metode per registrar Constants.
     * @param <E>
     * @param name Nom en que es guardara la Constant.
     * @param value Valor que tindra la Constant.
     * @throws DependencyException 
     */
    public <E> void registerConstant(Class<E> name, E value) 
        throws DependencyException;
    
    /**
     * Metode per registrar Factorys.
     * @param <E>
     * @param name Nom en que es guardara la Factory.
     * @param creator Factory que tenim que guardar.
     * @param parameters Parametres que tindra la Factory.
     * @throws DependencyException 
     */
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, 
            Class<E> ... parameters) throws DependencyException;
    
    /**
     * Metode per obtenir els valors de les Contants o de les Factorys.
     * @param <E>
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte, que tindra el valor de la Contants o de les Factorys.
     * @throws DependencyException 
     */
    public <E> E getObject(Class<E> name)
        throws DependencyException;
    
}
