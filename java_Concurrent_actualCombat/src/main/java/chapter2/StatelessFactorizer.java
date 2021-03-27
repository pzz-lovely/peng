package chapter2;

import annotaions.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(servletResponse,factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {

    }

    BigInteger extractFromRequest(ServletRequest request) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{i};
    }

}
