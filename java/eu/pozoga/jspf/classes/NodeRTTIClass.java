/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author sebastian
 */
public class NodeRTTIClass extends AbstractRTTIClass implements RTTIClass {

    ClassFilter filter;
    RTTIClass parent;
    RTTIClass root;

    public NodeRTTIClass(ClassFilter filter, RTTIClass parent, RTTIClass root) {
        this.filter = filter;
        this.parent = parent;
        this.root = root;
    }



    public Class getBaseClass() {
        return root.getBaseClass();
    }


    public RTTIClass getRTTIClassRoot() {
        return root;
    }


    public Annotation getAnnotation(Class baseClass) throws Throwable {
        if(!filter.isAnnotationFilter())
            return parent.getAnnotation(baseClass);
        for(Annotation ann : getAnnotations())
            if(baseClass.isInstance(ann))
                return ann;
        return null;
    }

    Reference<Collection<Annotation>> annotations;
    public Collection<Annotation> getAnnotations() throws Throwable {
        if(!filter.isAnnotationFilter())
            return parent.getAnnotations();
        Collection<Annotation> anns = annotations==null? null : annotations.get();
        if(anns==null){
            anns = new ArrayList<Annotation>();
            for(Annotation ann : parent.getAnnotations()){
                if(filter.annotationFilter(ann))
                    anns.add(ann);
            }
            annotations = new WeakReference<Collection<Annotation>>(anns);
        }
        return anns;
    }

    public Field getField(String name) throws Throwable {
        if(!filter.isFieldFilter())
            return parent.getField(name);
        for(Field field: getFields())
            if(field.getName().equals(name))
                return field;
        return null;
    }

    Reference<Collection<Field>> fields;
    public Collection<Field> getFields() throws Throwable {
        if(!filter.isFieldFilter())
            return parent.getFields();
        Collection<Field> collection = (fields==null)? null : fields.get();
        if(collection==null){
            collection = new ArrayList();
            for(Field e : parent.getFields()){
                if(filter.fieldFilter(e))
                    collection.add(e);
            }
            fields = new WeakReference<Collection<Field>>(collection);
        }
        return collection;
    }

    public Method getMethod(String name, Class[] types) throws Throwable {
        if(!filter.isMethodFilter())
            return parent.getMethod(name, types);
        for(Method method: getMethods())
            if(method.getName().equals(name) && Arrays.equals(method.getParameterTypes(),types))
                return method;
        return null;
    }

    Reference<Collection<Method>> methods;
    public Collection<Method> getMethods() throws Throwable {
        if(!filter.isMethodFilter())
            return parent.getMethods();
        Collection<Method> collection = (methods==null)? null : methods.get();
        if(collection==null){
            collection = new ArrayList<Method>();
            for(Method e : parent.getMethods()){
                if(filter.methodFilter(e))
                    collection.add(e);
            }
            methods =new WeakReference<Collection<Method>>(collection);
        }
        return collection;
    }


    /*
     * Select
     *
     */
    Map<ClassFilter, RTTIClass> selects = new WeakHashMap<ClassFilter, RTTIClass>();
    public RTTIClass select(ClassFilter filter) {
        RTTIClass node = selects.get(filter);
        if(node==null){
            node = new NodeRTTIClass(filter, this, root);
            selects.put(filter, node);
        }
        return node;
    }


}
