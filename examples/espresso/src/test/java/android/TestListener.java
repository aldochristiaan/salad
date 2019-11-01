package android;

import id.aldochristiaan.salad.util.LogUtil;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static android.AndroidFactory.resetApp;
import static android.AndroidFactory.takeScreenshot;

public class TestListener implements TestWatcher, AfterAllCallback {

    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED;
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LogUtil.warn("Test Disabled for test " + context.getDisplayName() + ": with reason :- " + reason.orElse("No reason"));
        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LogUtil.info("Test Successful for test : " + context.getDisplayName());
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LogUtil.warn("Test Aborted for test : " + context.getDisplayName());
        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LogUtil.error("Test Failed for test : " + context.getDisplayName());
        testResultsStatus.add(TestResultStatus.FAILED);
        takeScreenshot(context.getDisplayName());
        resetApp();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        LogUtil.info("Test result summary for " + context.getDisplayName() + " " + summary.toString());
    }
}
