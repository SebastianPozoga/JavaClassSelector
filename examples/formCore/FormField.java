/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.formCore;
import java.lang.annotation.*;

/**
 *
 * @author sebastian
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormField {

    public String type() default "generate";
    public String label() default "";
    public boolean required() default true;
    public String[] when() default {} ;
    
}
