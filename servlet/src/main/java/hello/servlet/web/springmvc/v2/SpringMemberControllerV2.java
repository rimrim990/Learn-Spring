package hello.servlet.web.springmvc.v2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    // v2: 컨트롤러에서 ModelAndView 를 반환하는 것이 귀찮
    @RequestMapping("/save")
    public ModelAndView save() {
        return new ModelAndView("save-result");
    }
}
