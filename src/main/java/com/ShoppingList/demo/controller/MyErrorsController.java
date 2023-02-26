package com.ShoppingList.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MyErrorsController implements ErrorController {

    @GetMapping("/error")
    public String showPageError404(Exception e, HttpServletResponse req) {
        String view = "";

        switch(req.getStatus()) {
        
            case HttpServletResponse.SC_NOT_FOUND:{
            	view="Error/404error";
            }
                
            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:{
            	 view = "Error/500error";
            	 return view;
            }
               
              
            default:
                view = "404";
           
        }
        return view;
    }

    @ExceptionHandler(Exception.class)
    public String showPageErrorGeneric(Exception e) {
        return "ExceptionPage";
    }

    public String getErrorPath() {
        return "/error";
    }
}
