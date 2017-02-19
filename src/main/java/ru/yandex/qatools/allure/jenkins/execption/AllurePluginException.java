package ru.yandex.qatools.allure.jenkins.execption;

/**
 * @author charlie (Dmitry Baev).
 */
public class AllurePluginException extends RuntimeException {

    public AllurePluginException(String message) {
        super(message);
    }
}
