import edu.duke.*;

/**
 * Part 2 of the assignment.
 * 
 * @author Arjun Bharadwaj
 * @version 1.0
 */
public class Part2 {
   
    public String findSimpleGene(String dna, String startCodon, String endCodon) {
        
        dna = dna.toUpperCase();
        startCodon = startCodon.toUpperCase();
        endCodon = endCodon.toUpperCase();
        
        int startCodonIndex = dna.indexOf(startCodon);
        if (startCodonIndex == -1) {
            return "";
        }

        int endCodonIndex = dna.indexOf(endCodon, startCodonIndex + 3);
        if (endCodonIndex == -1) {
            return "";
        }
        if ((endCodonIndex - startCodonIndex) % 3 == 0) {
            return dna.substring(startCodonIndex, endCodonIndex + 1);
        }
        return "";
    }
    
    public void testSimpleGene() {
        System.out.println("--------------------------");
        String [] dnas = new String[] {
         "AAAATAA"
        ,"ATGATGATGATG"
        ,"GGGGGGGGGGGG"
        ,"AGGATGGAATAGTAGTAA"};
        for(String dna: dnas) {
            System.out.println(dna + " DNA has --> " + 
                                findSimpleGene(dna, "ATG", "TAA") + " gene");
        }
        System.out.println("--------------------------");
    }
        
    public static void main (String[] args) {
        Part2 p = new Part2();
        p.testSimpleGene();
    } // End of main
}
