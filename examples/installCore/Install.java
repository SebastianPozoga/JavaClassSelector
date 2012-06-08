/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.jspf.installCore;

import eu.pozoga.jspf.actionCore.ActionManager;
import eu.pozoga.jspf.classes.Classes;
import eu.pozoga.jspf.classes.SimpleClassesFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author sebastian
 */
@ManagedBean
@ApplicationScoped
public class Install {

    public static boolean install() throws Throwable{
        ActionManager actionManager = ActionManager.getInstance();
        Set<Class> installScripts = Classes.getRTTIClasses().select(new SimpleClassesFilter(null, InstallScript.class)).getClasses();
        //Params map
        Map params = new HashMap();
        //preInstall
        for(Class scriptClass : installScripts){
            InstallInterface is = (InstallInterface) scriptClass.newInstance();
            if( is.preInstall() == false )
                throw new Exception("[Instal failed] Instal Script : "+is.getClass().getName()+" preinstall error");
        }
        actionManager.action("preInstall", params);
        //Install
        for(Class scriptClass : installScripts){
            InstallInterface is = (InstallInterface) scriptClass.newInstance();
            if( is.install() == false )
                throw new Exception("[Instal failed] Instal Script : "+is.getClass().getName()+" install error");
        }
        actionManager.action("install", params, null);
        //postInstall
        for(Class scriptClass : installScripts){
            InstallInterface is = (InstallInterface) scriptClass.newInstance();
            if( is.postInstall() == false )
                throw new Exception("[Instal failed] Instal Script : "+is.getClass().getName()+" install error");
        }
        actionManager.action("postInstall", params, null);
        //Install action
        return true;
    }

}
