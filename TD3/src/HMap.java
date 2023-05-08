public class HMap {
    EntryList[] t;
    int nbEntries;

    public WordList find(Prefix key){
        int hash = key.hashCode(t.length);
        for(EntryList it = t[hash]; it != null; it = it.next){
            Entry cur = it.head;
            if(cur.key == key){
                return cur.value;
            }
        }
        return null;
    }

    public void addSimple(Prefix key, String w){
        int hash = key.hashCode(t.length);
        for(EntryList it = t[hash]; it != null; it = it.next){
            Entry cur = it.head;
            if(cur.key == key){
                cur.value.addLast(w);
                return;
            }
        }
        //key not found => must create new entry
        WordList newWordList = new WordList();
        newWordList.addLast(w);
        Entry newEntry = new Entry(key, newWordList);
        EntryList.addLast(newEntry, t[hash]);
        nbEntries++;
    }

    public void add(Prefix key, String w){
        int curLength = t.length;
        if(nbEntries > (3*curLength)/4){//must redimension before adding
            int newLength = 2*curLength;
            EntryList[] newT = new EntryList[newLength];
            for(int i=0; i<curLength; i++){
                for(EntryList it = t[i]; it != null; it = it.next){
                    Entry entry = it.head;
                    int newHash = entry.key.hashCode(newLength);
                    EntryList.addLast(entry, newT[newHash]);
                }
            }
        }
        addSimple(key, w);
    }

    public HMap(int n){
        t = new EntryList[n];
        nbEntries = 0;
    }

    public HMap(){
        t = new EntryList[20];
        nbEntries = 0;
    }
}
