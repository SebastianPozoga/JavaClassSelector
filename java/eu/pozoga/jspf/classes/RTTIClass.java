/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * RTTI Class (Run-Time Type Information Class)
 *
 * @author sebastian
 */
public interface RTTIClass {

    /**
     * Get the value of baseClass
     *
     * @return the value of baseClass
     */
    public Class getBaseClass();

    /**
     * Set the value of baseClass
     *
     * @param baseClass new value of baseClass
     */
    //public void setBaseClass(Class baseClass);


    /**
     * Return true if class implements interface
     */
    public boolean isInstanceOf(Class c);


    public RTTIClass getRTTIClassRoot();


    public Annotation getAnnotation(Class baseClass) throws Throwable;
    public Collection<Annotation> getAnnotations() throws Throwable;

    public Field getField(String name) throws Throwable;
    public Collection<Field> getFields() throws Throwable;

    public Method getMethod(String name, Class[] types) throws Throwable;
    public Collection<Method> getMethods() throws Throwable;


    /*
     * Info
     */
    public Collection<String> getBeanFields() throws Throwable;
    //public Collection<Method> getBeanFieldMethods() throws Throwable;


    /*
     * Select
     */
    public RTTIClass select(ClassFilter filter);

    
}
