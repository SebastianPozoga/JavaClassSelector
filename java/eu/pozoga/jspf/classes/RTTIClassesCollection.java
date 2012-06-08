/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 *
 * @author sebastian
 */

public class RTTIClassesCollection extends HashMap<Class, RTTIClass> {


    public Set<Class> getClasses(){
        return keySet();
    }

    public void add(Class clazz){
        put(clazz, new MainRTTIClass(clazz));
    }

    public void add(RTTIClass rttiClass){
        put(rttiClass.getBaseClass(), rttiClass);
    }

    public Collection<RTTIClass> get() {
        return values();
    }



    /*
     * select
     */
    private Map<ClassesFilter, RTTIClassesCollection> selectCache = new WeakHashMap<ClassesFilter, RTTIClassesCollection>();
    public RTTIClassesCollection select(ClassesFilter filter){
        RTTIClassesCollection rttiCollection = selectCache.get(filter);
        if(rttiCollection==null){
            rttiCollection = new RTTIClassesCollection();
            for(RTTIClass rttiClass : get())
                if(filter.filter(rttiClass))
                    rttiCollection.add(rttiClass);
        }
        return rttiCollection;
    }

}
