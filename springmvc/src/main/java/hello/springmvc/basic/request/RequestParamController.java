package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        log.info("username={}, age={}", username, age);

        res.getWriter().write("ok");
    }

    // view 를 조회하지 않고 문자열을 바로 HTTP 에 포함시킴
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            // 파라미터 필수 여부
            // default: true
            @RequestParam(required=false) String username,
            @RequestParam(required=true) int age) {

        // null, "";
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            // 파라미터 필수 여부
            // default: true
            // "" 도 default 로 처리해준다
            @RequestParam(required=true, defaultValue="guest") String username,
            @RequestParam(required=false, defaultValue="-1") int age) {

        // null, "";
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    // ModelAttribute 사용으로 인해, 스프링 MVC 에서는 해당 프로퍼티의 setter 를 호출하여 파라미터의 값을 입력 (바인딩) 한다
    // getUsername(), setUsername() 메서드가 있으면 이 객체는 username 프로퍼티를 가지고 있다고 한다
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    // ModelAttribute 사용으로 인해, 스프링 MVC 에서는 해당 프로퍼티의 setter 를 호출하여 파라미터의 값을 입력 (바인딩) 한다
    // getUsername(), setUsername() 메서드가 있으면 이 객체는 username 프로퍼티를 가지고 있다고 한다
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }
}
