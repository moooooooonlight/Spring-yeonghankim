package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnCheckedTest {

    @Test
    void checked_catch(){
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callCatch())
                .isInstanceOf(MyUncheckedException.class);
    }

    /**
     * RuntimeException을 상속받으 예외는 언체크 예외가 된다.
     */
    static class MyUncheckedException extends RuntimeException{
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    /**
     * 언체크 예외는
     * 예외를 잡거나 던지지 않아도 된다.
     * 예외를 잡지 않으면 자동으로 던져진다.
     */
    static class Service{
        Repository repository = new Repository();

        public void callCatch(){
            repository.call();
        }
    }


    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }
}
