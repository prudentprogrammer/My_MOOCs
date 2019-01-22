import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna) {
        int c = 0;
        int g = 0;
        dna = dna.toUpperCase();
        for (int i = 0; i < dna.length(); i++) {
            char ch = dna.charAt(i);
            if (ch == 'C')
                c += 1;
            else if (ch == 'G')
                g += 1;
        }
        return (double) (c + g) / dna.length();
    }
    
    public void testCGRatio() {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public int countCTG(String dna) {
        int count = 0;
        int startIndex = -3;
        
        while (true) {
            startIndex = dna.indexOf("CTG", startIndex + 3);
            if (startIndex == -1)
                break;
            count += 1;
        }
        return count;
    }   
}
