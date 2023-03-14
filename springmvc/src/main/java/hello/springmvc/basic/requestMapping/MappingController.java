package hello.springmvc.basic.requestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

// 문자를 HTTP 바디에 넣어서 반환
@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    // HTTP 메서드 지정하지 않으면 종류 상관없이 호출 가능

    /**
     * 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     */
    @RequestMapping(value="/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }
}
