/*
 * Test Case - 1
 * Final Number = 2500
 * Operations = {"X + 10", "X - 5", "X * 5", "X ^ 2"}
 * Expected Output: 5
 *
 * Test Case - 2
 * Final Number = 1000
 * Operations = {"X * 5", "X / 0", "X ^ 3"}
 * Expected Output: -1
 *
 * Test Case - 3
 * Final Number: 10
 * Operations: {"X * 5", "X * 0", "X + 10"}
 * Expected Output : -2
 *
 * Test Case - 4
 * Final Number: 617283948
 * Operations: {"X + 5”, “X - 0”, “X + 1”, “X / 2”, “X ^ 1”}
 * Expected Output: 1234567890
 */

package practiceSet;

public class GuessTheNumber {
    public static void main(String[] args) {

        int finalNumber = 617283948;
        String[] operations = {"X + 5", "X - 0", "X + 1", "X / 2", "X ^ 1"};

        int actualNumber = GuessTheNumber.getActualNumber(finalNumber, operations, operations.length);

        System.out.println("The actual number will be " + actualNumber + " after performing given operations.");

    }

    public static int getActualNumber(int finalNumber, String[] operations, int totalOperations) {

        // go through all operations in reverse order

        int ans = finalNumber;

        for(int i = totalOperations - 1 ; i>=0 ; i--) {

            // get operation & second operand for conditional checking

            char operation = operations[i].charAt(2);
            int op2 = Integer.parseInt(operations[i].substring(4));

            /**
             *  cases to be handled
             *
             *  =========== return -2
             *  1. x * 0
             *  2. x ^ 0
             *  3. x % any
             *
             *  =========== return -1
             *  4. x / 0
              */

            if(operation == '/' && op2 == 0) return -1;
            if(operation == '%' ||  (operation == '^' && op2 == 0)  || (operation == '*' && op2 == 0)) return -2;

            switch (operation) {
                case '+' -> ans -= op2;
                case '-' -> ans += op2;
                case '*' -> ans /= op2;
                case '/' -> ans *= op2;
                case '^' -> ans = (int) Math.pow(ans, 1f / op2); // 2 ^ 3 = 8 ==> 8^(1/3) = 2
            }
        }

        return ans;
    }
}
