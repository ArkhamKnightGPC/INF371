public class EntryList {
    Entry head;
    EntryList next;

    static EntryList addLast(Entry entry, EntryList l){
        EntryList newEntryList = new EntryList(entry, null);
        if(l == null)return l = newEntryList;
        else{
            EntryList copyl = l;
            while(l.next != null){
                l = l.next;
            }
            l.next = newEntryList;
            return l = copyl;
        }
    }

    EntryList(Entry head, EntryList next){
        this.head = head;
        this.next = next;
    }
}
