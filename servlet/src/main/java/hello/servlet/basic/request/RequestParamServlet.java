package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param/
 */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("start");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println( paramName + " = " + request.getParameter(paramName)));

        System.out.println("end");
        System.out.println("조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = "+username);
        System.out.println("age = "+ age);
    }
}
