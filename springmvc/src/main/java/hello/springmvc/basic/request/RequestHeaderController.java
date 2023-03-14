package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletResponse request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Local locale,
                          // MultiValueMap - 하나의 키에 여러 값을 받을 수 있음
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                         ) {
        log.info(httpMethod.toString());
        log.info(locale.toString());
        log.info(cookie);
        return "ok";
    }
}
