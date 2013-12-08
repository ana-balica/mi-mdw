package ws;
 
import javax.jws.*;
 
@WebService(name="Calculator")
public class Calculator {
 
    @WebMethod(operationName="compute")
    @WebResult(name="result")
    public double compute(@WebParam(name="left") double left, @WebParam(name="operation") String operation, @WebParam(name="right") double right){
        return 0;
    }
}