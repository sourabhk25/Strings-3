// Time Complexity : O(1) because max no of ierations = max no of digits = upto 9/10 based on Integer.MAX_VALUE
// Space Complexity : O(1)  because space for arrays will be constant
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
//   - Divide the input number into groups of 3 digits (thousands).
//   - For each 3-digit group, convert it to English using helper().
//   - Attach the correct scale (Thousand, Million, Billion) for each group.
//   - Concatenate the groups in correct order to form the final string.


public class IntegerToEnglishWords {
    String[] below_20 = new String[]{"", "One", "Two", "Three", "Four", "Five","Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = new String[]{"", " Thousand ", " Million ", " Billion "};

    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }

        int i = 0;
        StringBuilder result = new StringBuilder();

        while(num > 0) {
            int triplet = num % 1000;   //get last 3 digits

            if(triplet > 0) {   //we dont want 100 000 hence checking 3 digits greater than 0
                result.insert(0, (helper(triplet).trim() + thousands[i])); //we need to prepend newly formed tuple string each time since we are going unit place to thousand place etc.    //here trim is added for cases like fifty thousand etc.
            }

            num = num / 1000;
            i++;
        }

        return result.toString().trim();    //trim added to remove extra spaces in some testcases
    }

    private String helper(int num) {
        StringBuilder result = new StringBuilder();
        if(num < 20) {
            result.append(below_20[num]);
        } else if(num < 100) {
            result.append(tens[num / 10]).append(" ").append(below_20[num % 10]);
        } else {
            result.append(below_20[num / 100]).append(" Hundred ").append(helper(num % 100));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        IntegerToEnglishWords converter = new IntegerToEnglishWords();

        int num1 = 123;
        int num2 = 12345;
        int num3 = 1234567;
        int num4 = 1234567891;
        int num5 = 0;

        System.out.println(num1 + " → " + converter.numberToWords(num1));
        System.out.println(num2 + " → " + converter.numberToWords(num2));
        System.out.println(num3 + " → " + converter.numberToWords(num3));
        System.out.println(num4 + " → " + converter.numberToWords(num4));
        System.out.println(num5 + " → " + converter.numberToWords(num5));
    }
}
