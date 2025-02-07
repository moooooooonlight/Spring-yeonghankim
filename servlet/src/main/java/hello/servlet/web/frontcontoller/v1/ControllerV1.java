package hello.servlet.web.frontcontoller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 { // 왜 만드나? -> 회원 입력, 저장, 리스트

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
