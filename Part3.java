import edu.duke.*;

public class Part3 {
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
    
    public int howMany(String stringa,String stringb) {
        int startIndex = 0;
        int counter = 0;
        while(true) {
            startIndex = stringb.indexOf(stringa, startIndex);
            if(startIndex == -1) {
                break;
            }
            counter++;
            startIndex = stringb.indexOf(stringa, startIndex) + stringa.length();
        }
        return counter;
    }
    
    public float cgRatio(String dna) {
        float result = 0;
        float sum = howMany("C", dna) + howMany("G", dna);
        result = sum/dna.length();
        return result;
    }
    
    public int countCTG(String dna) {
        int result = howMany("CTG", dna);
        return result;
    }
    
    public void processGenes(StorageResource sr) {
        int count = 0;
        for (String item : sr.data()) {
            if(item.length()>9) {
                System.out.println(item);
                count++;
            }
        }
        System.out.println("long string total number: "+count);
        
        count = 0;
        for (String item : sr.data()) {
            if(cgRatio(item)>0.35) {
                System.out.println(item);
                count++;
            }
        }
        System.out.println("cgRatio total number: "+count);
        
        int maxLength = 0;
        for (String item : sr.data()) {
            if(item.length()>maxLength) {
                maxLength = item.length();
            }
        }

        System.out.println("max length string: "+ maxLength);
    }
    
    public void processGenes2(StorageResource sr) {
        int count = 0;
        for (String item : sr.data()) {
            if(item.length()>60) {
                System.out.println(item);
                count++;
            }
        }
        System.out.println("long string total number > 60: "+count);
        
        count = 0;
        for (String item : sr.data()) {
            if(cgRatio(item)>0.35) {
                System.out.println(item);
                count++;
            }
        }
        System.out.println("cgRatio total number: "+count);
        
        int maxLength = 0;
        for (String item : sr.data()) {
            if(item.length()>maxLength) {
                maxLength = item.length();
            }
        }
        System.out.println("max length string: "+ maxLength);
    }
    
    public void testGetAllGenes(String dna) {
        StorageResource genes = new StorageResource();
        genes = getAllGenes(dna);
        for (String g : genes.data()) {
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
    
    public void testCgRatio() {
        String dna = "ATGCCATAG";
        System.out.println("testing: "+dna);
        System.out.println(cgRatio(dna));
    }
    
    public void testCountCTG() {
        String dna = "CTAGCTGAACTG";
        System.out.println("testing: "+dna);
        System.out.println(countCTG(dna)); //2
    }
    
    public void testProcessGenes() {
        String dna = "ATGAAAACCACAACATCACCTAATTCAATGTAGCTATGCGGGCGGCGCGGTGAGCAACGGTGAGATGCAAGTACCAGCGGCCTGA";
        System.out.println("testing: "+dna);
        processGenes(getAllGenes(dna));
        
        dna = "ATGCTAAATCTATCAGTATAGCCATGTAAATGAAATTTTAG";
        System.out.println("testing: "+dna);
        processGenes(getAllGenes(dna));
        
        dna = "ATGCTATAGCCATGTAA";
        System.out.println("testing: "+dna);
        processGenes(getAllGenes(dna));
        
        dna = "ATATGGCGTAGCATTATGGGGCCCTAAC";
        System.out.println("testing: "+dna);
        processGenes(getAllGenes(dna));
        
        dna = "ATATGAAAAAATAGCATTATGTTAAAATAAC";
        System.out.println("testing: "+dna);
        processGenes(getAllGenes(dna));
    }
    
    public void testProcessGenes2() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        processGenes2(getAllGenes(dna));
    }
    
    public static void main (String[] args) {
        Part3 p3 = new Part3();
        p3.testProcessGenes2();
    }
}
