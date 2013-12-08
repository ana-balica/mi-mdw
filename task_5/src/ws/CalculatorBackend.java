package ws;
 
import javax.jws.*;
 
@WebService(name="CalculatorBackend")
public class CalculatorBackend {
 
    @WebMethod(operationName="add")
    @WebResult(name="result")
    public double add(@WebParam(name="left") double left, @WebParam(name="right") double right){
        return left+right;
    }
 
    @WebMethod(operationName="sub")
    @WebResult(name="result")
    public double sub(@WebParam(name="left") double left, @WebParam(name="right") double right){
        return left-right;
    }
 
}