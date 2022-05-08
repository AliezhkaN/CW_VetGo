package com.nahorniak.controller.servlets.outOfControl;

import com.nahorniak.DAO.entity.User;
import com.nahorniak.util.mail.MailSender;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "contact", value = "/contact")
public class ContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String inputMessage = request.getParameter("message");

            if(notNull(fullName,email,inputMessage)){
                User user = new User.Builder().withEmail("vet.go.ua@gmail.com").build();
                String subject = "Questions";

                String message = inputMessage + "\n\nfrom: " +fullName +"\nemail: "+ email;
                new MailSender(user, message, subject).start();
                session.setAttribute("message","We will contact with you soon!");
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
