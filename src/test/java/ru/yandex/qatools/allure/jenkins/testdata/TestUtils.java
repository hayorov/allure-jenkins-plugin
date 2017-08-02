package ru.yandex.qatools.allure.jenkins.testdata;

import hudson.FilePath;
import hudson.model.JDK;
import org.junit.rules.TemporaryFolder;
import org.jvnet.hudson.test.JenkinsRule;
import ru.yandex.qatools.allure.jenkins.tools.AllureCommandlineInstallation;

import java.nio.file.Path;

/**
 * @author charlie (Dmitry Baev).
 */
public final class TestUtils {

    TestUtils() {
    }

    public static JDK getJdk(JenkinsRule jRule) {
        return jRule.jenkins.getJDKs().get(0);
    }

    public static AllureCommandlineInstallation getAllureCommandline(JenkinsRule jRule, TemporaryFolder folder) throws Exception {
        Path allureHome = folder.newFolder("some spaces in here").toPath();
        FilePath allure = jRule.jenkins.getRootPath().createTempFile("allure", "zip");
        //noinspection ConstantConditions
        allure.copyFrom(TestUtils.class.getClassLoader().getResource("allure-commandline.zip"));
        allure.unzip(new FilePath(allureHome.toFile()));

        AllureCommandlineInstallation installation = new AllureCommandlineInstallation(
                "Default", allureHome.toAbsolutePath().toString(), JenkinsRule.NO_PROPERTIES);
        jRule.jenkins.getDescriptorByType(AllureCommandlineInstallation.DescriptorImpl.class)
                .setInstallations(installation);
        return installation;
    }
}
