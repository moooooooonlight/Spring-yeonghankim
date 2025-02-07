package hello.servlet.web.frontcontoller.v4.controllerV4;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.V3.ControllerV3;
import hello.servlet.web.frontcontoller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String,Object> model) {

        List<Member> members = memberRepository.findAll();

        model.put("members",members);
        return "members";
    }
}
