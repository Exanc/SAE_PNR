package CORE;

public class Model {
    
    public static long calculate (long a, long b, String operator) {

        switch (operator) {
            
            case "+":
                return a + b;

            case "-":
                return a - b;
            
            case "*":
                return a * b;
            
            case "/":
                if (b == 0) {
                    System.out.println("Can't devide by 0.");
                    return -1;
                }
                return a / b;

            default:
                System.out.println("Unknown operator: " + operator);
                return -1;
        }
    }
    
}
