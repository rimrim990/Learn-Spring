package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Tomcat servlet container 에서 singleton 으로 관리
@WebServlet(name ="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("req = " + req);
        System.out.println("res = " + res);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        // response header
        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");

        // response body
        res.getWriter().write("hello " + username);
    }
}
