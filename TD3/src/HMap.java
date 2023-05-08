public class HMap {
    EntryList[] t;
    int nbEntries;

    public WordList find(Prefix key){
        int hash = key.hashCode(t.length);
        for(EntryList it = t[hash]; it != null; it = it.next){
            Entry cur = it.head;
            if(Prefix.eq(cur.key, key)){
                return cur.value;
            }
        }
        return null;
    }

    public void addSimple(Prefix key, String w){
        int hash = key.hashCode(t.length);
        for(EntryList it = t[hash]; it != null; it = it.next){
            Entry cur = it.head;
            if(Prefix.eq(cur.key, key)){
                cur.value.addLast(w);
                return;
            }
        }
        //key not found => must create new entry
        WordList newWordList = new WordList();
        newWordList.addLast(w);
        Entry newEntry = new Entry(key, newWordList);
        t[hash] = EntryList.addLast(newEntry, t[hash]);
        nbEntries++;
    }

    public void rehash(int n){
        int curLength = t.length;
        EntryList[] newT = new EntryList[n];
        for(int i=0; i<curLength; i++){
            for(EntryList it = t[i]; it != null; it = it.next){
                Entry entry = it.head;
                int newHash = entry.key.hashCode(n);
                newT[newHash] = EntryList.addLast(entry, newT[newHash]);
            }
        }
        t = newT;
    }

    public void add(Prefix key, String w){
        addSimple(key, w);

        int curLength = t.length;
        if(4*nbEntries > 3*curLength){//must redimension
            rehash(2*curLength);
        }
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
