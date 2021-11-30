package com.nixsolution.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @RequestMapping(value = "/errors")
    public String getErrorPage(HttpServletRequest request) {
        int httpErrorCode = getErrorCode(request);
        String errorPage = null;
        switch (httpErrorCode) {
        case 403:
            errorPage = "403";
            break;
        case 404:
            errorPage = "404";
            break;
        }
        return errorPage;
    }

    @RequestMapping(value = "/403")
    public String getDeniedErrorPage(HttpServletRequest request) {
        return "403";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute(
                "javax.servlet.error.status_code");
    }
}
