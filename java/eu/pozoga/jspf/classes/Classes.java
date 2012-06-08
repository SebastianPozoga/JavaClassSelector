/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.jspf.classes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/*
 * Bild omment:
 *
 * Klasa powinna reprezenować wszystkie klasy
 */


/**
 *
 * @author sebastian
 */
public class Classes {

    /**
     * Return all RTTI object of classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @return The classes
     */
    private static RTTIClassesCollection classesCollection = null;
    public static RTTIClassesCollection getRTTIClasses() throws Exception {
        if (classesCollection == null) {
            classesCollection = new RTTIClassesCollection();
            for(Class clazz : getClasses(""))
                classesCollection.add(clazz);
        }
        return classesCollection;
    }




    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    protected static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException, Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    protected static List<Class> getClasses() throws ClassNotFoundException, IOException, Exception {
        return getClasses("");
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException, Exception {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                String findPackage = ((packageName!=null && !packageName.equals("")) ? packageName+ "." :packageName)  + file.getName();
                classes.addAll(findClasses(file, findPackage));
            } else if (file.getName().endsWith(".class")) {
                try{
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }catch(Throwable ex){
                    throw new Exception("No load class: "+packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                }
            }
        }
        return classes;
    }


    /*
     * Factory
     * /
    private static Classes instance;
    public static Classes getInstance(){
        if(instance==null)
            instance = new Classes();
        return instance;
    }*/
}
