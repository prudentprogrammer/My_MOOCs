import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ( (currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 1); // Search for the next occurence codon.
        }
        return dna.length();
    }

    public void testFindStopCodon () {
        String dna = "AAATGAAAAAATAA";
        int startIndex = dna.indexOf("ATG");
        System.out.println(findStopCodon(dna, startIndex, "TAA"));
        
        dna = "AAATGATAA";
        System.out.println(findStopCodon(dna, startIndex, "TAA"));
    }
    
    public String findGene (String dna, int s) {
        int startIndex = dna.indexOf("ATG", s);
        if (startIndex == -1)
            return "";

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TAA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (minIndex == dna.length()) return "";
        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        // Test code goes here
    }

    public void printAllGenes (String dna) {
        dna = dna.toUpperCase();
        int startIndex = 0;
        int totalGenes = 0;
        
        while (true) {
           String tempGene = findGene(dna, startIndex);
           if (tempGene.isEmpty()) { // No genes found!
               break;
           }
           
           totalGenes += 1;
           System.out.println("Gene: " + tempGene);
           startIndex += dna.indexOf(tempGene) + tempGene.length();
        }
    }
}
