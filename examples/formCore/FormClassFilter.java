/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.formCore;

import eu.pozoga.jspf.classes.ClassFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author sebastian
 */
public class FormClassFilter implements ClassFilter{

    String when;

    public FormClassFilter(String when) {
        this.when = when;
    }

    public FormClassFilter() {
    }




    public boolean isAnnotationFilter() {
        return false;
    }

    public boolean annotationFilter(Annotation annotation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isFieldFilter() {
        return false;
    }

    public boolean fieldFilter(Field field) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isMethodFilter() {
        return true;
    }

    public boolean methodFilter(Method method) {
        FormField fieldAnn = method.getAnnotation( FormField.class );
        if(fieldAnn==null)
            return false;
        if(fieldAnn.when().length==0)
            return true;
        if(when==null)
            return true;
        for(String w : fieldAnn.when()){
            if(w.equals(when))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FormClassFilter other = (FormClassFilter) obj;
        if ((this.when == null) ? (other.when != null) : !this.when.equals(other.when)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.when != null ? this.when.hashCode() : 0);
        return hash;
    }

    



}
