import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author Arjun Bharadwaj
 * @version 1.0
 */
public class Part1 {
   
    public String findSimpleGene(String dna) {
        int startCodonIndex = dna.indexOf("ATG");
        if (startCodonIndex == -1) {
            return "";
        }

        int endCodonIndex = dna.indexOf("TAA", startCodonIndex + 3);
        if (endCodonIndex == -1) {
            return "";
        }
        if ((endCodonIndex - startCodonIndex) % 3 == 0) {
            return dna.substring(startCodonIndex, endCodonIndex + 1);
        }
        return "";
    }
    
    public void testSimpleGene() {
        String [] dnas = new String[] {"AAAATAA"
        ,"ATGATGATGATG"
        ,"GGGGGGGGGGGG"
        ,"AGGATGGTAGTAGTAA"};
        for(String dna: dnas) {
            System.out.println(dna + " DNA has " + 
                                findSimpleGene(dna) + "gene");
        }
    }
        
    public static void main (String[] args) {
        Part1 p = new Part1();
        p.testSimpleGene();
        
    } // End of main

}
