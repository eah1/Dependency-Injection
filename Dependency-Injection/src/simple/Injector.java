
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public interface Injector {
    
    /**
     * Metode per registrar Constants.
     * @param name Nom en que es guardara la Constant.
     * @param value Valor que tindra la Constant.
     * @throws DependencyException 
     */
    public void registerConstant(String name, Object value) 
        throws DependencyException;
    
    /**
     * Metode per registrar Factorys.
     * @param name Nom en que es guardara la Factory.
     * @param creator Factory que tenim que guardar.
     * @param parameters Parametres que tindra la Factory.
     * @throws DependencyException 
     */
    public void registerFactory(String name, Factory creator, String... parameters)
        throws DependencyException;
    
    /**
     * Metode per obtenir els valors de les Contants o de les Factorys.
     * @param name Nom que fara de referencia per fer la busqueda.
     * @return Objecte, que tindra el valor de la Contants o de les Factorys.
     * @throws DependencyException 
     */
    public Object getObject(String name)
        throws DependencyException;
    
}
