/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;

/**
 *
 * @author eah1
 */
public class Tuple {
    
    private final Factory  factory;
    private final Object[] parameters;
        
    public Tuple(Factory creator, Object[] parameters) {
        this.factory    = creator;
        this.parameters = parameters;
    }
        
    public Object getCreateFactory() throws DependencyException {
        return this.factory.create(parameters);
    }
        
}
