package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // WEB-INF 아래에 있으므로 외부에서는 서블릿을 거쳐야 호출이 가능
        String viewPath = "/WEB-INF/vies/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        // 다른 서블릿이나 JSP 로 이동할 수 있는 기능
        // 서버 내부에서 다시 호출이 발생한
        dispatcher.forward(req, resp);
    }
}
