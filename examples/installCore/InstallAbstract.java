/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.installCore;

/**
 *
 * @author sebastian
 */
public class InstallAbstract implements InstallInterface{

    public boolean preInstall() throws Throwable {
        return true;
    }

    public boolean install() throws Throwable {
        return true;
    }

    public boolean postInstall() throws Throwable {
        return true;
    }

}
