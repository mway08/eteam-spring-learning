package com.eteam.frame.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExceptionResolver extends SimpleMappingExceptionResolver {

    static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    protected ModelAndView doResolveException(HttpServletRequest req, HttpServletResponse res, Object handler,
                                              Exception e) {
        ModelAndView view = new ModelAndView("/error");
        return view;
    }
}
