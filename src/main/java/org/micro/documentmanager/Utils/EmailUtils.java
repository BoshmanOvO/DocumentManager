package org.micro.documentmanager.Utils;



public class EmailUtils {

    public static String getNewAccountEmailBody(String name, String host, String tokenSend) {
        return "Hello " + name + ",\n\n" +
                "Welcome to our platform. Please verify your email address by clicking the link below.\n\n" +
                newAccountApi(host, tokenSend) + "\n\n" +
                "Thank you,\n" +
                "Team";
    }

    public static String getAccountResetEmailBody(String name, String host, String tokenSend) {
        return "Hello " + name + ",\n\n" +
                "You have requested to reset your password. Please click the link below to reset your password.\n\n" +
                resetPasswordApi(host, tokenSend) + "\n\n" +
                "Thank you,\n" +
                "Team";
    }

    private static String newAccountApi(String host, String tokenSend) {
        return host + "/auth/account?token=" + tokenSend;
    }

    private static String resetPasswordApi(String host, String tokenSend) {
        return host + "/auth/password?token=" + tokenSend;
    }
}
