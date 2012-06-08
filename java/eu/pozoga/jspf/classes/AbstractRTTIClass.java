/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

import eu.pozoga.jspf._helper.StringHelper;
import java.lang.ref.Reference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author sebastian
 */
public abstract class AbstractRTTIClass implements RTTIClass{

    /**
     * Return true if class implements interface
     */
    public boolean isInstanceOf(Class c){
        for(Class clazz : getBaseClass().getInterfaces())
            if(c.equals(clazz)) return true;
        return false;
    }

    Reference<Collection<String>> beanFields;
    public Collection<String> getBeanFields() throws Throwable{
        Collection<String> collection = beanFields==null? null : beanFields.get();
        if(collection==null){
            collection = new ArrayList();
            for(Method method : getMethods()){
                if(method.getName().startsWith("get"))
                    collection.add( StringHelper.LowerFirstChar(method.getName().substring(3)));
            }
        }
        return collection;
    }

    /*Reference<Collection<Method>> beanFieldMethods;
    public Collection<Method> getBeanFieldMethods() throws Throwable{
        Collection<Method> collection = beanFieldMethods==null? null : beanFieldMethods.get();
        if(collection==null){
            collection = new ArrayList();
            for(Method method : getMethods()){
                if(method.getName().startsWith("get"))
                    collection.add(method);
            }
        }
        return collection;
    }*/
    
}
