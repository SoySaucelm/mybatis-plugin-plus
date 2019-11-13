package com.github.soysaucelm.plugin;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SoySauce
 * @since 2019/5/15
 */
public class GeneratorUtil {

    public static void main(String[] args) {
        generatorShell();
    }

    private static void generatorShell() {
        List<String> warnings = new ArrayList<String>();
        try {
            boolean overwrite = true;
            ConfigurationParser cp = new ConfigurationParser(warnings);
//            File configFile = new File(System.getProperty("user.dir").concat("/src/main/resources/generator.xml"));
//            Configuration config = cp.parseConfiguration(configFile);

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("generator.xml");
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            ProgressCallback progressCallback = new VerboseProgressCallback();
            /**
             *  打印信息与是否覆盖
             *   if (callback == null) {
             *   callback = new NullProgressCallback();
             *   }
             *   想要看到冗余信息就要创建一个VerboseProgressCallback，它是NullProgressCallback的子类。
             *   new DefaultShellCallback(overwrite)时，如果是false，第二次运行工具生成文件将不覆盖原文件，此处建议设置为true。
             */
//            myBatisGenerator.generate(null);
            myBatisGenerator.generate(progressCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}
