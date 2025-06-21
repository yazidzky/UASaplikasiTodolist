package com.example.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleTaskNotFoundException(TaskNotFoundException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorTitle", "Task Tidak Ditemukan");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("status", HttpStatus.NOT_FOUND.value());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGenericException(Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorTitle", "Kesalahan Server");
        mav.addObject("errorMessage", "Terjadi kesalahan: " + ex.getMessage());
        mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return mav;
    }
}