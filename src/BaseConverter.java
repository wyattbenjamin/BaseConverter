import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Convert a String num in fromBase to base-10 int.
 * @author 23benjamin
 * @version 12/2/22
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase) {
        int value = 0, exp = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }

        return value;
    }

    /**
     * @param num
     * @param toBase
     * @return the converted number
     */
    public String intToStr (int num, int toBase) {
        String toNum = new String();
        int index = -1;
        while(num>0) {
            index = num % toBase;
            toNum = DIGITS.charAt(index) + toNum;
            num /= toBase;
        }
        return (toNum.equals("")) ? "0" : toNum;
    }

    /**
     *It takes an input number then it converts it to a new base. Prints the number and then writes it to a new file
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles/values30.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            String output;
            while(in.hasNext()) {
                line = in.nextLine().split("\t");
                /*System.out.println(line[1]);
                System.out.println(line[2]);
                System.out.println(line[3]);*/
                if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line [1])>16)
                    System.out.println("Invalid input base " +line[1]);
                else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line [2])>16)
                    System.out.println("Invalid output base " +line[1]);
                else {
                    output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]));
                    out.println(line[0] + "\t" + line[1] + "\t" + output + "\t" + line[2]);
                    System.out.println(line[0] + " base " + line[1] + " = " + output + " base " + line[2]);

                }

               /** System.out.println(line [0]);
                System.out.println(line [1]);
                System.out.println(line [2]);
                **/
            }

            if (out!=null)
                out.close();
            if (in!=null)
                in.close();
            //System.out.println("the revolution will not be televised");

        }
        catch (Exception e) {
            System.out.println("Something bad happened. Details here:" + e.toString());
        }
    }


    /**
     * Main method of class BaseConverter
     * @param args command line arguments if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}