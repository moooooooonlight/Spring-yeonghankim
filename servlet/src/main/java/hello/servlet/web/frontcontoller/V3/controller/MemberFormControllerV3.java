package hello.servlet.web.frontcontoller.V3.controller;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.V3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
