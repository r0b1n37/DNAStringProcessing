import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if(diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
        if(minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public void testGetAllGenes(String dna) {
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public void testPrintAllGenes() {
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAGA";
        System.out.println("testing: "+dna);
        testGetAllGenes(dna);
        
        dna = "";
        System.out.println("testing: "+dna);
        testGetAllGenes(dna);
        
        dna = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        System.out.println("testing: "+dna);
        testGetAllGenes(dna);
    }
    
    public static void main (String[] args) {
        Part1 p1 = new Part1();
        p1.testPrintAllGenes();
    }
}
