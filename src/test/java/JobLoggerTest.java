import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;

import static org.mockito.Mockito.when;

public class JobLoggerTest {

    @Test
    public void logMessage() throws Exception {
        new JobLogger(true, true, true,
                true, true, true);
        JobLogger.LogMessage("este mensaje se escribe en todos", true, false, false);
        JobLogger.LogMessage("este warning se escribe en todos", false, true, false);
        JobLogger.LogMessage("este error se escribe en todos", false, false, true);
    }

    @Test
    public void logMessageFile() throws Exception {
        new JobLogger(true, false, false,
                true, true, true);
        JobLogger.LogMessage("este mensaje se escribe en el archivo", true, false, false);
        JobLogger.LogMessage("este warning se escribe en el archivo", false, true, false);
        JobLogger.LogMessage("este error se escribe en el archivo", false, false, true);
    }

    @Test
    public void logMessageConsole() throws Exception {
        new JobLogger(false, true, false,
                true, true, true);
        JobLogger.LogMessage("este mensaje se escribe en la consola", true, false, false);
        JobLogger.LogMessage("este warning se escribe en la consola", false, true, false);
        JobLogger.LogMessage("este error se escribe en la consola", false, false, true);
    }

    @Test
    public void logMessageBD() throws Exception {
        new JobLogger(false, false, true,
                true, true, true);
        JobLogger.LogMessage("este mensaje se escribe en la bd", true, false, false);
        JobLogger.LogMessage("este warning se escribe en la bd", false, true, false);
        JobLogger.LogMessage("este error se escribe en la bd", false, false, true);
    }
}