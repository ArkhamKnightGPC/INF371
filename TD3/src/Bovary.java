public class Bovary {
    static HMap buildTable(String[] files, int n){
        HMap hMap = new HMap();

        for(int i=0; i<files.length; i++){
            String curFile = files[i];
            final WordReader wr = new WordReader(curFile);
            Prefix curPrefix = new Prefix(n);

            for (String w = wr.read(); w != null; w = wr.read()) {
                hMap.add(curPrefix, w);
                curPrefix.addShift(w);
            }
        }

        return hMap;
    }

    static void generate(HMap t, int n){

        Prefix curPrefix = new Prefix(n);
        String nextWord = new String();
        boolean needSpace = false;

        for(int i=0; i<(int)(Math.random()*1000); i++){
            WordList candidates = t.find(curPrefix);
            String[] candidateStrings = candidates.toArray();

            int nbCandidates = candidateStrings.length;
            int randInt = (int)(Math.random()*nbCandidates);
            nextWord = candidateStrings[randInt];

            if(nextWord.equals(Prefix.par)){
                System.out.println();
                needSpace = false;
            }else{
                if(needSpace)System.out.print(" ");
                System.out.print(nextWord);
                needSpace = true;
            }

            curPrefix.addShift(nextWord);
        }
        if(needSpace)System.out.println();
    }

    public static void main(String[] args) {
        int prefixLength = 3;
        String[] files = new String[35];
        
        for(int i=1; i<=35; i++){
            files[i-1] = "bovary/" + ((i>9) ? ""+i : "0"+i) + ".txt";
        }

        HMap t = buildTable(files, prefixLength);
        generate(t, prefixLength);
    }
}
