
public class TestMain {

    public static void main ( String args[] ) throws Exception {
        new JobLogger(true, true, true,
                true, true, true);
        JobLogger.LogMessage("este mensaje se escribe en todos", true, false, false);
        JobLogger.LogMessage("este warning se escribe en todos", false, true, false);
        JobLogger.LogMessage("este error se escribe en todos", false, false, true);

    }

}