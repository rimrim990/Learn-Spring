package com.mysite.sbb.sbbproject.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// final 이 붙은 속성을 포함하는 생성자를 자동으로 생성
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        // Model 객체는 자바 클래스와 템플릿 간의 연결고리 역할을 한다
        // Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있다
        // 컨트롤러 메서드의 매개변수로 지정하면 스프링부트가 자동으로 Model 객체를 생성한다
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
