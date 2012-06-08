/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.formCore;

import eu.pozoga.jspf.classes.Classes;
import eu.pozoga.jspf.classes.RTTIClass;
import eu.pozoga.jspf.classes.SimpleClassFilter;
import eu.pozoga.jspf.helper.StringHelper;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 *
 * @author sebastian
 */
//@ManagedBean
//@ApplicationScoped
public class FormManager {


    /*
     * Bean Field
     */
    public Collection<String> getBeanFields(Object o) throws Throwable{
        return getFormRTTIClass(o.getClass()).getBeanFields();
    }

    public Collection<String> getBeanFields(Class c) throws Throwable{
        return getFormRTTIClass(c).getBeanFields();
    }

    public Collection<String> getBeanFields(Class c, String when) throws Throwable{
        return getFormRTTIClass(c, when).getBeanFields();
    }


    public String getBeanFieldType(Object o, String fieldName) throws Throwable{
        return getBeanFieldType(o.getClass(), fieldName);
    }

    public String getBeanFieldType(Class c, String fieldName) throws Throwable{
        return getBeanFieldAnnotation(c, fieldName).type();
    }

    public FormField getBeanFieldAnnotation(Object o, String fieldName) throws Throwable{
        return getBeanFieldAnnotation(o.getClass(), fieldName);
    }

    public FormField getBeanFieldAnnotation(Class c, String fieldName) throws Throwable{
        Method getMethod = c.getMethod("get"+StringHelper.UpperFirstChar(fieldName), new Class[0]);
        if(getMethod==null)
            throw new Exception("The field by name '"+fieldName+"' isn't exist");
        FormField ann = getMethod.getAnnotation( FormField.class );
        if(ann==null)
            throw new Exception("The method isn't form field accessor");
        return ann;
    }


    FormClassFilter formClassFilter = new FormClassFilter();
    public RTTIClass getFormRTTIClass(Class c) throws Throwable{
        return Classes.getRTTIClasses().get(c).select(formClassFilter);
    }

    public RTTIClass getFormRTTIClass(Class c, String when) throws Throwable{
        return Classes.getRTTIClasses().get(c).select(new FormClassFilter(when));
    }

    /*
     * Get instance
     */
    private static FormManager instance;
    public static FormManager getInstance(){
        if(instance==null)
            instance=new FormManager();
        return instance;
    }
}
