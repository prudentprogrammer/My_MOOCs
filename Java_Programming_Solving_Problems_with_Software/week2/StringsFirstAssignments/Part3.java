import java.util.Arrays;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public String lastPart(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1) {
            return stringb;
        }
        return stringb.substring(firstIndex + stringa.length());
    }
    
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1) {
            return false;
        }
        int secondIndex = stringb.indexOf(stringa, firstIndex + stringa.length());
        if (secondIndex == -1) {
            return false;
        }
        return true;
    }
    
    public void testing() {
        System.out.println("--------------------------");
        String[][] pairs = new String[][] {
            {"by", "A story by Abby Long"},
            {"a", "banana"},
            {"atg", "ctgtatgta"},
            {"zoo", "forest"}
        };
        for(String[] pair: pairs) {
            System.out.println("findTwoOccurences( " + Arrays.toString(pair) + ") = " + twoOccurrences(pair[0], pair[1]));
        }
        
        for(String[] pair: pairs) {
            System.out.println("lastPart( " + Arrays.toString(pair) + ") = " + lastPart(pair[0], pair[1]));
        }
        System.out.println("--------------------------");
    }
    
    public static void main (String[] args) {
        Part3 p = new Part3();
        p.testing();
    } // End of main

}
