package hello.servlet.web.frontcontoller.v4;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.V3.ControllerV3;
import hello.servlet.web.frontcontoller.V3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontoller.V3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontoller.V3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontoller.v4.controllerV4.MemberFormControllerV4;
import hello.servlet.web.frontcontoller.v4.controllerV4.MemberListControllerV4;
import hello.servlet.web.frontcontoller.v4.controllerV4.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form",new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save",new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members/list",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 적절한 컨트롤러 호출 .
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerV4Map.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }


        // request에 있는 정보만 추출하는 것.
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap,model);
        MyView view = viewResolver(viewName);
        view.render(model,request,response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName , request.getParameter(paramName)));
        return paramMap;
    }
}
