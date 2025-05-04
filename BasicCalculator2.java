// Time Complexity : O(n) where n = len of s
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
//   - Traverse the input string character by character.
//   - Keep track of the last number, last operator, and a running total (`calc`).
//   - On encountering an operator or end of string, process the last number with the last operator.
//   - For * and /, adjust the last operation (tail) to handle operator precedence without a stack.
//   - Return final calculated result.


public class BasicCalculator2 {
    //push all ans to stack and at the end take sum
    // public int calculate(String s) {
    //     Stack<Integer> st = new Stack<>();
    //     char sign = '+';    //default sign is +
    //     int n = s.length();
    //     for(int i = 0; i < n; i++) {
    //         char ch = s.charAt(i);
    //         if(Character.isDigit(ch)) { //if ch is a digit
    //             int val = 0;
    //             while(i < n && Character.isDigit(s.charAt(i))) {
    //                 val = val * 10 + (s.charAt(i) - '0');   //handling case of no. of with multiple digits
    //                 i++;
    //             }
    //             i--;    //move i back 1 space after loop
    //             //compare sign value, for +,- push +ve,-ve val. for *,/ calculate ans and push
    //             if(sign == '+') {
    //                 st.push(val);
    //             } else if(sign == '-') {
    //                 st.push(-val);
    //             } else if(sign == '*') {
    //                 int a = st.pop();
    //                 int ans = a * val;
    //                 st.push(ans);
    //             } else if(sign == '/') {
    //                 int a = st.pop();
    //                 int ans = a / val;
    //                 st.push(ans);
    //             }
    //         } else if(ch != ' ') {  //if not whitespace
    //             sign = ch;  //assign sign
    //         }
    //     }

    //     int sum = 0;
    //     while(!st.isEmpty()) {  //calulate sum of all terms on stack
    //         sum += st.pop();
    //     }
    //     return sum;
    // }

    // public int calculate(String s) {
    //     Stack<Integer> st = new Stack<>();
    //     int currNum = 0;
    //     char lastSign = '+';
    //     int result = 0;

    //     for(int i = 0; i < s.length(); i++) {
    //         char c = s.charAt(i);
    //         if(Character.isDigit(c)) {
    //             currNum = currNum * 10 + c - '0';
    //         }
    //         if((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {  //any operator or last char in str
    //             if(lastSign == '+') {
    //                 st.push(currNum);
    //             } else if(lastSign == '-') {
    //                 st.push(-currNum);
    //             } else if(lastSign == '*') {
    //                 st.push(st.pop() * currNum);
    //             } else if(lastSign == '/') {
    //                 st.push(st.pop() / currNum);
    //             }

    //             currNum = 0;    //reinitialize last no and last sign
    //             lastSign = c;
    //         }
    //     }

    //     while(!st.isEmpty()) {
    //         result += st.pop();
    //     }

    //     return result;
    // }

    public int calculate(String s) {
        int calc = 0;
        int tail = 0;
        int currNum = 0;
        char lastSign = '+';

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                currNum = currNum * 10 + c - '0';
            }

            if((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if(lastSign == '+') {
                    calc = calc + currNum;
                    tail = currNum;
                } else if(lastSign == '-') {
                    calc = calc - currNum;
                    tail = -currNum;
                } else if(lastSign == '*') {
                    calc = (calc - tail) + (tail * currNum);
                    tail = tail * currNum;
                } else if(lastSign == '/') {
                    calc = (calc - tail) + (tail / currNum);
                    tail = tail / currNum;
                }

                currNum = 0;
                lastSign = c;
            }
        }

        return calc;
    }

    public static void main(String[] args) {
        BasicCalculator2 calculator = new BasicCalculator2();

        String expr1 = "3+2*2";
        String expr2 = " 3/2 ";
        String expr3 = " 3+5 / 2 ";

        System.out.println("Expression: " + expr1 + " = " + calculator.calculate(expr1));  // Expected: 7
        System.out.println("Expression: " + expr2 + " = " + calculator.calculate(expr2));  // Expected: 1
        System.out.println("Expression: " + expr3 + " = " + calculator.calculate(expr3));  // Expected: 5
    }
}
