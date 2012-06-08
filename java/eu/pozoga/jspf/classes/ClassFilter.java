/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author sebastian
 */
public interface ClassFilter {

    public boolean isAnnotationFilter();
    public boolean annotationFilter(Annotation annotation);

    public boolean isFieldFilter();
    public boolean fieldFilter(Field field);

    public boolean isMethodFilter();
    public boolean methodFilter(Method method);

    
}
