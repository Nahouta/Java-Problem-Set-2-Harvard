//Barcode.java
/**
 * This class will set the blueprint for BarCode objects.
 * The class will take a 5-digit zip code as input, and print the barcode
 * using the symbol | for a full bar and : for a half bar.
 * The class will also take a bar code as input and print the corresponding 5-digit zip code.
 * 
 * @author Tresor Habib Nahouta
 * @version Last modified, Feb 25 2023
 **/


public class BarCode {
    
    /*The primary fields of the BarCode class */
    private String myZipCode;
    private String myBarCode;


    /**
     * This method is a constructor that will take a bar code or a zip code as input,
     * and initialize automatically the rest of the fields
     * 
     * @param input The zip code or bar code.
     */

    public BarCode (String input) {
        /*Checking if the input is a valid bar code,
         * and setting the fields if applicable
        */
        if (isValidBarCode(input)) {
            this.myBarCode = input;
            this.myZipCode = this.decode(input);
        }

        /*Checking if the input is a valid Zip code,
         * and setting the fields if applicable
         */
        else if (isValidZipCode(input)) {
            this.myZipCode = input;
            this.myBarCode = this.encode(input);
        }
        /*Throwing the appropriate exception in case the input is neither a valid zip code
         * nor valid bar code
         */
        else if (!isValidBarCode(input) && input.length() == 32) {
            throw new IllegalArgumentException("Illegal Bar Code values: " + input);
        }
        else if (!isValidZipCode(input) && input.length() == 5) {
            throw new IllegalArgumentException("Illegal Zip Code values: " + input);
        }
        else {
            throw new IllegalArgumentException("Illegal input: " + input);
        }
    }


    /**
     * Accessor for the myZipCode field
     * @return a String object representing the zip code
     */
    public String getZipCode() {
        return this.myZipCode;
    }

    /**
     * Accessor for the myBarCode field
     * @return a String object representing the bar code
     */
    public String getBarCode() {
        return this.myBarCode;
    }


    /**
     * This method will receive a String input, and determines whether or not it is a valid Bar Code
     * @param input a String representing the bar code
     * @return a boolean which is true if the input is a valid bar code, or false otherwise
     */

     
    private boolean isValidBarCode (String input) {
        /*Checking the length */
        if (input.length() != 32) {
            return false;
        }

        /*Checking the if the first and last characters are a frame bar */
        else if (input.charAt(0) != '|' || input.charAt(input.length()-1) != '|') {
            return false;
        }
        else {
            /*Checking if all the characters are either '|' or ':' */
            for (int i=1; i < input.length() - 1; i++) {
                if (input.charAt(i) != '|' && input.charAt(i) != ':') {
                    return false;
                }
            }

            /*Separating and decoding the input into its digits, and 
             * checking if the encoding is correct
             */
            String [] segments = new String [6];
            segments [0] = new String (input.substring(1,6));
            segments [1] = new String (input.substring(6,11));
            segments [2] = new String (input.substring(11,16));
            segments [3] = new String (input.substring(16,21));
            segments [4] = new String (input.substring(21,26));
            segments [5] = new String (input.substring(26,31));

            for (int i = 0; i < 6; i++) {
                /*The codeToDigit has been designed to return the empty char if the encoding is not valid, 
                 * so we use that as a checking mechanism
                 */
                if (codeToDigit (segments [i]) == ' ') {
                    return false;
                }
            }


            /*Checking if the summ of all digits plus the last check digit is a multiple of 10 */
            int sum = 0;

            for (int i = 0; i < 6; i++ ) {
                sum += getCheckDigit( segments [i]);
            }

            if (sum % 10 != 0) {
                return false;
            }
        }

        /*If the bar code passes all the tests, then it is valid */
        return true;
    }
    
    /**
     * This method will receive a String input, and determines whether or not it is a valid zip code
     * @param input a String representing the zip code
     * @return a boolean which is true if the input is a valid zip code, or false otherwise
     */


    private boolean isValidZipCode (String input) {
        /*Checking the validity of the length */
        if (input.length() != 5) {
            return false;
        }
        else {
            for (int i = 0; i < 5; i++) {
                /*Checking if there is any illegal character */
                if (! Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * This method will receive a String portion of a bar code sequence,
     * and return the corresponding digit as a Char
     * @param segment a String representing a portion of a barcode
     * @return the corresponding digit as Char
     */

    private char codeToDigit (String segment) {
        /* For simplicity, We directly match each String to the corresponding digit, instead of doing the
         * weighted sum as presented in the exercise
         */

        if (segment.equals(":::||")) return '1';
        if (segment.equals("::|:|")) return '2';
        if (segment.equals("::||:")) return '3';
        if (segment.equals(":|::|")) return '4';
        if (segment.equals(":|:|:")) return '5';
        if (segment.equals(":||::")) return '6';
        if (segment.equals("|:::|")) return '7';
        if (segment.equals("|::|:")) return '8';
        if (segment.equals("|:|::")) return '9';
        if (segment.equals("||:::")) return '0';

        /*If the input doesn't match any known digit, we return the empty Char,
         * which will serve as a trigger of invalidity in other methods
         */

        return ' ';
    }


    /**
     * This method will receive a Char digit, and return the corresponding encoded barcode as String
     * @param digit The Char input digit
     * @return a String representing the encoded digit
     */

    private String digitToCode (char digit) {
        switch (digit) {
        /* For simplicity, We directly match each digit to the corresponding String, instead of doing the
         * weighted sum as presented in the exercise
         */
            case '1': return ":::||";
            case '2': return "::|:|";
            case '3': return "::||:";
            case '4': return ":|::|";
            case '5': return ":|:|:";
            case '6': return ":||::";
            case '7': return "|:::|";
            case '8': return "|::|:";
            case '9': return "|:|::";
            case '0': return "||:::";
            default: return "";
        }
    }

    /**
     * This method will receive a String portion of a bar code sequence,
     * and return the corresponding digit as an int, to be used to compute the sum of digits.
     *  
     * @param segment a String representing a portion of a barcode
     * @return the corresponding digit as int
     */

    private int getCheckDigit (String segment) {
        /* For simplicity, We directly match each String to the corresponding digit, instead of doing the
         * weighted sum as presented in the exercise
         */
        if (segment.equals(":::||")) return 1;
        if (segment.equals("::|:|")) return 2;
        if (segment.equals("::||:")) return 3;
        if (segment.equals(":|::|")) return 4;
        if (segment.equals(":|:|:")) return 5;
        if (segment.equals(":||::")) return 6;
        if (segment.equals("|:::|")) return 7;
        if (segment.equals("|::|:")) return 8;
        if (segment.equals("|:|::")) return 9;
        if (segment.equals("||:::")) return 0;
        return 0;
    }

    /**
     * This method will receive a String representing a bar code, and will return the corresponding zip code
     * @param input a String representing the bar code
     * @return a String representing the corresponding zip code
     */

    private String decode (String input) {
        StringBuilder export = new StringBuilder("");
        String [] segments = new String [5];

        segments [0] = input.substring(1,6);
        segments [1] = input.substring(6,11);
        segments [2] = input.substring(11,16);
        segments [3] = input.substring(16,21);
        segments [4] = input.substring(21,26);

        for (int i=0; i < 5; i++) {
            export.append(codeToDigit(segments [i]));
        }

        return export.toString();
    }

    /**
     * This method will receive a String representing the zip code, and will return the corresponding bar code
     * @param input a String representing the zip code
     * @return a String representing the corresponding bar code
     */
    private String encode (String input) {
        StringBuilder export = new StringBuilder("|");

        for(int i = 0; i < 5; i++) {
            export.append(digitToCode(input.charAt(i)));
        }

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += (int) (input.charAt(i) - '0');
        }

        export.append(digitToCode( (char) ( (10 - sum % 10) + '0') ) + "|");
        return export.toString();
    }

}
