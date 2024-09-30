package com.example.employee_service.utils;

public final class ResponseUtil {

    public static int httpStatusCode(String message) {
         if (message.contains("invalid")) return 400;
         else if (message.contains("not found")) return 404;
         else return 403;
    }

}
