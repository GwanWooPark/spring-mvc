package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController 문자열 그대로 반환
@RestController
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log= {}", name);
        log.debug("debug log= {}", name);
        // 이렇게 사용하면 안됨, 문자 더하기에 의해 연산이 일어나서 로그 레벨에 맞지 않더라도 계산을 해서 가지고 있는다. 따라서 쓸모없는 리소스가 낭비됨
        // log.debug("debug log= " + name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
