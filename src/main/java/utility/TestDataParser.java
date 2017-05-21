package utility;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.PropertyResourceBundle;

public class TestDataParser {
    public TestDataParser() {
    }

    public HashMap<String, String[]> getData() {
        HashMap<String, String[]> configMap = new HashMap<String, String[]>();
        try {
            FileInputStream fis = new FileInputStream("src/config.properties");
            PropertyResourceBundle resourceBundle = new PropertyResourceBundle(fis);
            Enumeration<String> keys = resourceBundle.getKeys();
            while (keys.hasMoreElements()) {
                String aKey = keys.nextElement();
                String aValue = resourceBundle.getString(aKey);
                configMap.put(aKey, aValue.split(","));
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configMap;
    }

}