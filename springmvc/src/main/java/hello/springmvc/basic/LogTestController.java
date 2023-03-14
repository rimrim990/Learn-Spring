package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // 클래스 지정
    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name); // 무조건 출력됨

        // 자바는 문자열을 치환한 후 더하는 연산을 수행하여 메모리에 저장
        // 메모리와 CPU 가 소비됨
        // info 레벨 로그를 출력하지 않는다면 메모리 낭비이다
        // log.info(" info log="+name);

        // 메소드 호출이므로 파라미터만 넘김
        log.trace("trace log={}", name); // 서버에 설정된 로깅 레벨에 따라 출력음
        log.debug("debug log={}", name);
        log.info(" info log ={}", name); // 기본 설정
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        // @RestController 설정으로 인해 view 가 아닌 String 반환
        // HTTP 메세지 바디에 응답을 바로 입력
        return "ok";
    }
}

