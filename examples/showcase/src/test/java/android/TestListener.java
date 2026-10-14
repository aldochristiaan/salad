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

/** Logs per-test results and captures a screenshot + app reset when a test fails. */
public class TestListener implements TestWatcher, AfterAllCallback {

    private final List<TestResultStatus> statuses = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LogUtil.warn("Test disabled: " + context.getDisplayName() + " (" + reason.orElse("no reason") + ")");
        statuses.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LogUtil.info("Test passed: " + context.getDisplayName());
        statuses.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LogUtil.warn("Test aborted: " + context.getDisplayName());
        statuses.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LogUtil.error("Test failed: " + context.getDisplayName());
        statuses.add(TestResultStatus.FAILED);
        if (AndroidFactory.getAndroidDriver() != null) {
            AndroidFactory.takeScreenshot(context.getDisplayName().replaceAll("[^a-zA-Z0-9._-]", "_"));
            AndroidFactory.resetApp();
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = statuses.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        LogUtil.info("Test result summary for " + context.getDisplayName() + " " + summary);
    }
}
