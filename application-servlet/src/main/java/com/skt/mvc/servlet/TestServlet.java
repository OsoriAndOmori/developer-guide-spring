package com.skt.mvc.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/test-servlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //db 조회하고 뭐 그런거 할 수 있지.
        String queryString = req.getQueryString();
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your query string is: " + queryString + "</h2>";
        htmlRespone += "</html>";

        System.out.println("htmlRespone");
        resp.getWriter().println(htmlRespone);
    }
}
