package com.nahorniak.controller.servlets.outOfControl.forgotPassword;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "confirmCode", value = "/confirmCode")
public class ConfirmCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String inputCode = request.getParameter("inputCode");
        String codeFromEmail = request.getParameter("code");

        System.out.println(inputCode);
        System.out.println(codeFromEmail);

        if(notNull(inputCode,codeFromEmail)){
            if(!inputCode.equals(codeFromEmail)){
                session.setAttribute("code",codeFromEmail);
                session.setAttribute("recoveryCodeError","Code is incorrect ! Please input one more time !");
            }else {
                session.setAttribute("changePassword",true);
            }
            response.sendRedirect("/application");
        }

    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
        }
        return true;
    }
}
