package hello.servlet.web.frontcontoller.v4;

import java.util.Map;
import java.util.Objects;

public interface ControllerV4 {
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
