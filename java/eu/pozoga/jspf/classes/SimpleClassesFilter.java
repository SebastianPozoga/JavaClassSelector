/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.classes;

/**
 *
 * @author sebastian
 */
public class SimpleClassesFilter implements ClassesFilter{

    protected Class typeBase;
    protected Class typeAnnotation;

    public SimpleClassesFilter(Class typeBase, Class typeAnnotation) {
        this.typeBase = typeBase;
        this.typeAnnotation = typeAnnotation;
    }

    public boolean filter(RTTIClass clazz) {
        if(typeAnnotation!=null && clazz.getBaseClass().getAnnotation(typeAnnotation)==null)
            return false;
        if(typeBase!=null && !clazz.isInstanceOf(typeBase))
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
        final SimpleClassesFilter other = (SimpleClassesFilter) obj;
        if (this.typeBase != other.typeBase && (this.typeBase == null || !this.typeBase.equals(other.typeBase))) {
            return false;
        }
        if (this.typeAnnotation != other.typeAnnotation && (this.typeAnnotation == null || !this.typeAnnotation.equals(other.typeAnnotation))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.typeBase != null ? this.typeBase.hashCode() : 0);
        hash = 97 * hash + (this.typeAnnotation != null ? this.typeAnnotation.hashCode() : 0);
        return hash;
    }

    

}
