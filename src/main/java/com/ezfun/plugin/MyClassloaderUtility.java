package com.ezfun.plugin;

import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author SoySauce
 * @date 2019/5/15
 */
public class MyClassloaderUtility {

    public MyClassloaderUtility() {
    }

    public static ClassLoader getCustomClassloader(List<String> entries) {
        List<URL> urls = new ArrayList();
        if (entries != null) {
            Iterator var3 = entries.iterator();

            while (var3.hasNext()) {
                String classPathEntry = (String) var3.next();
                File file = new File(classPathEntry);
                if (!file.exists()) {
                    throw new RuntimeException(Messages.getString("RuntimeError.9", classPathEntry));
                }

                try {
                    urls.add(file.toURI().toURL());
                } catch (MalformedURLException var6) {
                    throw new RuntimeException(Messages.getString("RuntimeError.9", classPathEntry));
                }
            }
        }

        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        URLClassLoader ucl = new URLClassLoader((URL[]) urls.toArray(new URL[urls.size()]), parent);
        return ucl;
    }
}
