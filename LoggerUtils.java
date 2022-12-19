package dbmethod;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

public class LoggerUtils {

	    private static final String KEY_FORMAT = "%s_%s";

	    private static Map<String, LoggerUtils> loggers = new HashMap<String, LoggerUtils>();

	    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd-HH:mm";

	    public static final String YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";

	    public static final String YEAR_MONTH_DAY_HOUR_MIN_FORMAT = "yyyy-MM-dd_HH-mm";

	    private static final SimpleFormatter FORMATTER = new SimpleFormatter();

	    public static LoggerUtils getLogger(String name) {
	        return getLogger(name, name + ".txt");
	    }

	    public static LoggerUtils getLogger(String name, String fileName) {
	        return getLogger(name, fileName, null);
	    }

	    public static LoggerUtils getLogger(String name, String fileName, String dir) {
	        String keyName = String.format(KEY_FORMAT, name,
	                getCurrentDateFormatted(YEAR_MONTH_DAY_FORMAT));
	        if (!loggers.containsKey(keyName)) {
	            closeHandlers(name);
	            try {
	                return setUpLogger(keyName, fileName, dir);
	            } catch (SecurityException | IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return loggers.get(keyName);
	    }

	    private static LoggerUtils setUpLogger(String name, String fileName, String dir)
	                                                                                throws SecurityException,
	                                                                                IOException {
	        LoggerUtils logger = LoggerUtils.getLogger(name);
	        File fileDir;
	        if (StringUtils.isOnlyWhitespaceOrEmpty(dir)) {
	            fileDir = new File(new File("D:\\temp"), name);
	        } else {
	            fileDir = new File(dir);
	        }
	        if (!fileDir.exists()) {
	            fileDir.mkdirs();
	        }
	        File logFile = new File(fileDir, String.format("%s", fileName));
	        FileHandler handler = new FileHandler(logFile.getAbsolutePath());
	        handler.setFormatter(FORMATTER);
	        logger.addHandler(handler);
	        loggers.put(name, logger);

	        return logger;
	    }

	    public static String getCurrentDateFormatted(String format) {
	        return DateFormatUtils.format(new Date(), format);
	    }

	    public static Object getCurrentDateFormatted() {
	        return getCurrentDateFormatted(DEFAULT_DATE_FORMAT);
	    }

	    private static void closeHandlers(String name) {
	        Iterator<Entry<String, LoggerUtils>> loggerEntries = loggers.entrySet().iterator();
	        while (loggerEntries.hasNext()) {
	            Entry<String, LoggerUtils> loggerEntry = loggerEntries.next();
	            String key = loggerEntry.getKey();
	            if (key.split("_")[0].equals(name)) {
	                LoggerUtils logger = loggers.remove(key);
	                Handler[] handlers = logger.getHandlers();
	                for (Handler handler : handlers) {
	                    try {
	                        handler.close();
	                    } catch (Exception e) {
	                    }
	                }
	            }
	        }
	    }
}
