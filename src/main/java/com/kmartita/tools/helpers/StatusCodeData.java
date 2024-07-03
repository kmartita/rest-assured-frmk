package com.kmartita.tools.helpers;

public interface StatusCodeData {
    String OK = "HTTP/1.1 200 OK";
    String BAD_REQUEST = "HTTP/1.1 400 Bad Request";
    String INTERNET_SERVER_ERROR = "HTTP/1.1 500 Internal Server Error";

    int STATUS_CODE_200 = 200;
    int STATUS_CODE_400 = 400;
    int STATUS_CODE_500 = 500;
}
