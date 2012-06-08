/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.installCore;

/**
 *
 * @author sebastian
 */
public interface InstallInterface {

    /**
     * Script run before the install. It can load data,, control integrate data
     * @return
     * @throws Throwable
     */
    public boolean preInstall() throws Throwable;

    /**
     * Install.
     * @return
     * @throws Throwable
     */
    public boolean install() throws Throwable;

    /**
     * Script run after install.
     * @return
     * @throws Throwable
     */
    public boolean postInstall() throws Throwable;
    
}
