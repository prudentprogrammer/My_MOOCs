
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany (String a, String b) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            startIndex = b.indexOf(a, startIndex);
            if (startIndex == -1)
                break;
            count += 1;
            startIndex += a.length();
        }
        return count;
    }
    
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // Returns 3
        System.out.println(howMany("AA", "ATAAAA")); // Returns 2
    }
}
