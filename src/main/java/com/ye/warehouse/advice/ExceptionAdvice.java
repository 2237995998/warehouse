package com.ye.warehouse.advice;

import com.ye.warehouse.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    public void handlerException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String xRequestedWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)){//异步请求
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(StringUtil.getJSONString(4, "操作失败,服务器异常,请稍后重试!!!"));
        }else {
            response.sendRedirect(request.getContextPath() + "/exception");
        }
//        ModelAndView view = new ModelAndView();
//        view.setViewName("error.html");
//        view.addObject("msg","操作失败,请重试!!!");
//        return view;
    }
}
