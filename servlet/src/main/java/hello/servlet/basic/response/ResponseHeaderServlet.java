package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // [status-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        // [Header 편의 메서드]
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        // resp.setHeader("Set-Cookie", "myCookie=good; Max-age=600");
        Cookie cookie = new Cookie("myCookie", "good");

        // redirect
        // resp.setStatus(HttpServletResponse.SC_FOUND);
        // resp.setHeader("Location", "/basic/hello-form.html");
        resp.sendRedirect("/basic/hello-form.html");

        // [response-header]
        //resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");

        // [message body]
        resp.getWriter().println("안녕하세요.");
    }
}
