/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * RTTI Class (Run-Time Type Information Class)
 *
 * @author sebastian
 */
public class MainRTTIClass extends AbstractRTTIClass{

    protected Class baseClass;


    
    public MainRTTIClass(Class baseClass) {
        this.baseClass = baseClass;
    }



    /**
     * Get the value of baseClass
     *
     * @return the value of baseClass
     */
    public Class getBaseClass() {
        return baseClass;
    }

    /**
     * Set the value of baseClass
     *
     * @param baseClass new value of baseClass
     * /
    public void setBaseClass(Class baseClass) {
        this.baseClass = baseClass;
    }*/



    public Annotation getAnnotation(Class baseClass) {
        return getBaseClass().getAnnotation(baseClass);
    }

    public Collection<Annotation> getAnnotations() {
        return Arrays.asList( getBaseClass().getAnnotations() );
    }

    public Field getField(String name) throws NoSuchFieldException {
        return getBaseClass().getField(name);
    }

    public Collection<Field> getFields() {
        return Arrays.asList( getBaseClass().getFields() );
    }

    public Method getMethod(String name, Class[] types) throws NoSuchMethodException {
        return getBaseClass().getMethod(name, types);
    }

    public Collection<Method> getMethods() {
        return Arrays.asList( getBaseClass().getMethods() );
    }

    public RTTIClass getRTTIClassRoot() {
        return this;
    }

    /*
     * Select
     *
     */
    Map<ClassFilter, RTTIClass> selects = new WeakHashMap<ClassFilter, RTTIClass>();
    public RTTIClass select(ClassFilter filter) {
        RTTIClass node = selects.get(filter);
        if(node==null){
            node = new NodeRTTIClass(filter, this, this);
            selects.put(filter, node);
        }
        return node;
    }




    /**
     * Return true if class implements interface
     */
     /*public boolean isInstanceOf(Class c){
        for(Class clazz : getBaseClass().getInterfaces())
            if(c.equals(clazz)) return true;
        return false;
    }

   Reference<String[]> beanFields;
    public String[] getBeanFields() throws Throwable{
        String[] array = beanFields==null? null : beanFields.get();
        if(array==null){
            List<String> list = new ArrayList();
            for(Method method : getMethods()){
                if(method.getName().startsWith("get"))
                    list.add( StringHelper.LowerFirstChar(method.getName().substring(3)));
            }
            array = (String[]) list.toArray();
        }
        return array;
    }*/
}
