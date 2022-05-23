package com.nahorniak.controller.filters;

import com.nahorniak.DAO.entity.Role;
import com.nahorniak.DAO.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

/**
 * Security filter.
 *
 * @author Oleh Nahorniak
 */
@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {

    private static final Logger log = Logger.getLogger(SecurityFilter.class);


    private static Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private static List<String> commons = new ArrayList<String>();
    private static List<String> outOfControl = new ArrayList<String>();


    /**
     * Security filter initialization to get rights for all user roles from web.xml to access pages.
     */
    public void init(FilterConfig config) throws ServletException {

        log.debug("Filter initialization starts");

        accessMap.put(Role.ADMIN, asList(config.getInitParameter("admin")));
        accessMap.put(Role.CUSTOMER, asList(config.getInitParameter("client")));
        accessMap.put(Role.DOCTOR, asList(config.getInitParameter("doctor")));
        log.trace("Access map --> " + accessMap);

        commons = asList(config.getInitParameter("common"));
        log.trace("Common commands --> " + commons);

        outOfControl = asList(config.getInitParameter("out-of-control"));
        log.trace("Out of control commands --> " + outOfControl);

        log.debug("Filter initialization finished");
    }

    /**
     * Destroy method.
     */

    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    /**
     * doFilter method checks request and user role to get it.
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.debug("Filter starts");

        if (accessAllowed(request)) {
            log.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(404);
        }
    }

    /**
     * accessAllowed method answer a question is it possible to get specific page
     *
     * @param request
     * @return access to page true / false
     */
    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String pageName = httpRequest.getHttpServletMapping().getServletName();
        if (pageName == null || pageName.isEmpty())
            return false;

        if (outOfControl.contains(pageName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        User user = (User) session.getAttribute("user");

        if (user == null)
            return false;

        Role userRole = user.getRole();

        return accessMap.get(userRole).contains(pageName)
                || commons.contains(pageName);
    }

    /**
     * asList method split param string from web.xml into a list
     *
     * @param str
     * @return
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
