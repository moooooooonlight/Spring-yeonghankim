package hello.servlet.web.frontcontoller.V3;

import hello.servlet.web.frontcontoller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
