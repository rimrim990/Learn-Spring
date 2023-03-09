package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // url 을 처리할 수 있는 handler 매핑
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // handler 처리를 위한 adapter 리스트
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        // handler 처리를 위한 adapter 등록
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        // urlPattern -> handler 매핑

        // v3 핸들러 등록
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // v4 핸들러 등록
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 URI 에 대응되는 handler (controller) 조회
        Object handler = getHandler(req);

        // 해당 URI 를 처리할 수 있는 handler 가 존재하지 않음
        if (handler == null){
            // 404 에러
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // adapter 리스트 탐색하여 handler 를 실행할 수 있는 adapter 조회
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // handler 시리행
        ModelView mv = adapter.handle(req, resp, handler);

        // 논리적 view 이름을 완전한 이름으로 변환
        String viewName = mv.getViewName();
        MyView view = viewResolver("WEB-INF/views/" + viewName + ".jsp");

        // JSP 서블릿 호출
        view.render(req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView(viewName);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다.");
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
}
