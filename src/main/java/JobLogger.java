import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.util.Date;

public class JobLogger {

    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    private static Logger logger = Logger.getLogger(JobLogger.class);

    public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                     boolean logMessageParam, boolean logWarningParam, boolean logErrorParam) {
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
    }

    public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
        messageText.trim();
        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        int t = 0;
        if (message && logMessage) {
            t = 1;
        }

        if (error && logError) {
            t = 2;
        }

        if (warning && logWarning) {
            t = 3;
        }

        String l = "[" + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())+ "]";

        if (error && logError) {
            l = l + " error: " + messageText;
        }

        if (warning && logWarning) {
            l = l + " warning: " + messageText;
        }

        if (message && logMessage) {
            l = l + " message: " + messageText;
        }

        if(logToFile) {
            logger.log(Level.INFO, l);
        }

        if(logToConsole) {
            System.out.println(l);
        }

        if(logToDatabase) {
            Database db = new Database();
            db.setDBfilename("messages.db");
            db.createConnection();
            db.createLogTable();
            db.insertMessage(t, l);
            db.closeConnectionBD();
        }
    }
}