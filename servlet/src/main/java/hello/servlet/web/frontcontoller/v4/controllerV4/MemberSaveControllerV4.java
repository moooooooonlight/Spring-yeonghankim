package hello.servlet.web.frontcontoller.v4.controllerV4;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.V3.ControllerV3;
import hello.servlet.web.frontcontoller.v4.ControllerV4;

import java.util.Map;
import java.util.Objects;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }
}
