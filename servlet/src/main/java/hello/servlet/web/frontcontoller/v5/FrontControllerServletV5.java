package hello.servlet.web.frontcontoller.v5;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.V3.ControllerV3;
import hello.servlet.web.frontcontoller.V3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontoller.V3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontoller.V3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontoller.v4.controllerV4.MemberFormControllerV4;
import hello.servlet.web.frontcontoller.v4.controllerV4.MemberListControllerV4;
import hello.servlet.web.frontcontoller.v4.controllerV4.MemberSaveControllerV4;
import hello.servlet.web.frontcontoller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontoller.v5.adapter.ControllerV4HadnlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.OnClose;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.*;

@WebServlet(name ="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapterList.add(new ControllerV3HandlerAdapter());
        handlerAdapterList.add(new ControllerV4HadnlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/list",new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form",new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save",new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/list",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        MyHandlerAdapter adapter = getHandlerAdater(handler);

        ModelView mv = adapter.handle(request, response,handler);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);
    }

    private MyHandlerAdapter getHandlerAdater(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapterList) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("hadnler adapter를 찾을 수 없습니다. "+handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
