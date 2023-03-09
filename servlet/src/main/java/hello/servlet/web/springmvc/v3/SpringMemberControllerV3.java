package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV3 {

    @GetMapping("/new-form")
    // @RequestMapping(method = RequestMethod.GET)
    public String newForm() {
        // DispatcherServlet 은 viewResolver 를 호출하여 view 의 논리 이름을 풀 네임으로 변환
        // viewResolver 는 View 반환
        // DispatcherServlet 은 View.render() 메서드 호출, 사용자에게 HTML 반환
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username,
                       @RequestParam("age") int age,
                       Model model) {

        Member member = new Member(username, age);

        model.addAttribute("member", member);
        return"save-result";
    }
}
