package hillel.javapro.ewallet_dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjProperties {

    private static Properties getPropertiesFromFile(String fileName){
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(fileName);
        try {
            prop.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    public static Properties getDbProperties(){
        return getPropertiesFromFile("database.properties");
    }

}
