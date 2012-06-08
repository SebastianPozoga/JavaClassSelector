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
public class SimpleClassFilter implements ClassFilter{


    protected Class fieldType;
    protected Class fieldAnnotation;

    protected Class methodReturnType;
    protected Class methodAnnotation;

    /*protected Class beanFieldType;
    protected Class beanFieldAnnotation;*/


    public SimpleClassFilter() {
    }

    public SimpleClassFilter(Class fieldType, Class fieldAnnotation, Class methodReturnType, Class methodAnnotation) {
        this.fieldType = fieldType;
        this.fieldAnnotation = fieldAnnotation;
        this.methodReturnType = methodReturnType;
        this.methodAnnotation = methodAnnotation;
    }

    public SimpleClassFilter setFieldAnnotation(Class fieldAnnotation) {
        this.fieldAnnotation = fieldAnnotation;
        return this;
    }

    public SimpleClassFilter setFieldType(Class fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public SimpleClassFilter setMethodAnnotation(Class methodAnnotation) {
        this.methodAnnotation = methodAnnotation;
        return this;
    }

    public SimpleClassFilter setMethodReturnType(Class methodReturnType) {
        this.methodReturnType = methodReturnType;
        return this;
    }

    public Class getFieldAnnotation() {
        return fieldAnnotation;
    }

    public Class getFieldType() {
        return fieldType;
    }

    public Class getMethodAnnotation() {
        return methodAnnotation;
    }

    public Class getMethodReturnType() {
        return methodReturnType;
    }

    /*
     * Config
     */

    public boolean isAnnotationFilter() {
        return false;
    }

    public boolean annotationFilter(Annotation annotation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isFieldFilter() {
        return fieldAnnotation!=null || fieldType!=null;
    }

    public boolean fieldFilter(Field field) {
        if(fieldAnnotation!=null && field.getAnnotation(fieldAnnotation)==null)
            return false;
        if(fieldType!=null && !field.getType().equals(fieldType))
            return false;
        return true;
    }

    public boolean isMethodFilter() {
        return methodAnnotation!=null || methodReturnType!=null;
    }

    public boolean methodFilter(Method method) {
        if(methodAnnotation!=null && method.getAnnotation(methodAnnotation)==null)
            return false;
        if(methodReturnType!=null && !method.getReturnType().equals(methodReturnType))
            return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleClassFilter other = (SimpleClassFilter) obj;
        if (this.fieldType != other.fieldType && (this.fieldType == null || !this.fieldType.equals(other.fieldType))) {
            return false;
        }
        if (this.fieldAnnotation != other.fieldAnnotation && (this.fieldAnnotation == null || !this.fieldAnnotation.equals(other.fieldAnnotation))) {
            return false;
        }
        if (this.methodReturnType != other.methodReturnType && (this.methodReturnType == null || !this.methodReturnType.equals(other.methodReturnType))) {
            return false;
        }
        if (this.methodAnnotation != other.methodAnnotation && (this.methodAnnotation == null || !this.methodAnnotation.equals(other.methodAnnotation))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.fieldType != null ? this.fieldType.hashCode() : 0);
        hash = 97 * hash + (this.fieldAnnotation != null ? this.fieldAnnotation.hashCode() : 0);
        hash = 97 * hash + (this.methodReturnType != null ? this.methodReturnType.hashCode() : 0);
        hash = 97 * hash + (this.methodAnnotation != null ? this.methodAnnotation.hashCode() : 0);
        return hash;
    }

}
