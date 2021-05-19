package com.ye.warehouse.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @PackageName:com.ye.warehouse.advice
 * @ClassName:ExceptionAdvice
 * @Description:
 * @author:何进业
 * @date:2021/5/19 17:03
 **/
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(){
        ModelAndView view = new ModelAndView();
        view.setViewName("error.html");
        view.addObject("msg","操作失败,请重试!!!");
        return view;
    }
}
