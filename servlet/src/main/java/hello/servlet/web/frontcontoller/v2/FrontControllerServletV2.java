package hello.servlet.web.frontcontoller.v2;

import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v1.ControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontoller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontoller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontoller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form",new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save",new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members/list",new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 적절한 컨트롤러 호출 .
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerV2Map.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        MyView view = controller.process(request,response);
        view.render(request,response);
    }
}
