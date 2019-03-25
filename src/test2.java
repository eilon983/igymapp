

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class test2 {
    static Logger log = Logger.getLogger(test2.class);
    public static void main(String[] args) {
        String s = "  CREATE TABLE LOGS\n" +
                "   (USER_ID VARCHAR(30)    NOT NULL,\n" +
                "    LOG_DATE   DATE           NOT NULL,\n" +
                "    LOGGER  VARCHAR(45)    NOT NULL,\n" +
                "    LEVEL   VARCHAR(12)    NOT NULL,\n" +
                "    MESSAGE VARCHAR(1000)  NOT NULL\n" +
                "   );";
        PropertyConfigurator.configure("src/log4j.properties");
        log.debug("Test debug");
        log.info("Test info");
    }
}