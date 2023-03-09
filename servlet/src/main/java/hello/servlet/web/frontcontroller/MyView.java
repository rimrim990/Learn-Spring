package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 호출할 JSP 경로 설정
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        // JSP 서블릿 호출
        dispatcher.forward(req, res);
    }

    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        modelToRequestAttributes(model, req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, res);

    }

    private void modelToRequestAttributes(Map<String, Object> model, HttpServletRequest req) {
        // JSP 로 모델 데이터 전달을 위해, req 에 속성 설정
        model.forEach((key, value) -> req.setAttribute(key, value));
    }
}
