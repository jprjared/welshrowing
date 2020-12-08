package com.team1.welshrowing.web;

import com.team1.welshrowing.domain.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Adapted from code examples at https://gist.github.com/jonikarppinen/662c38fb57a23de61c8b [Accessed: 7 December 2020]
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Value("${debug}")
    private boolean debug;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public String error(WebRequest webRequest, HttpServletResponse response, Model model) {
        model.addAttribute("error", new CustomError(response.getStatus(), getErrorAttributes(webRequest, debug)));
        return PATH;
    }

    @Override
    public String getErrorPath(){
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        return errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

}