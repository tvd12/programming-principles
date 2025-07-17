package org.youngmonkeys.dfs.properties;

import com.tvd12.properties.file.reader.MultiFileReader;
import com.tvd12.properties.file.util.PropertiesUtil;

import java.util.Properties;

public class EncryptedPropertiesReader {

    public static void main(String[] args) {
        Properties properties = new MultiFileReader()
            .read("application.properties");
        PropertiesUtil.setVariableValues(properties);
        System.out.println(properties);
    }
}
