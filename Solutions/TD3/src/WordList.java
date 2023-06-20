public class WordList {
    Node content;

    static WordList foobar = new WordList(new Node("foo", new Node("bar", new Node("baz", null))));

    int length(){
        return Node.length(content);
    }

    public String toString(){
        return Node.makeString(content);
    }

    void addFirst(String w){
        Node newNode = new Node(w, content);
        content = newNode;
    }

    void addLast(String w){
        if(content == null)addFirst(w);
        else Node.addLast(w, content);
    }

    String removeFirst(){
        if(content == null){
            return null;
        }else{
            String s = content.head;
            content = content.next;
            return s;
        }
    }

    String removeLast(){
        if(content == null){
            return null;
        }else{
            Node nextNode = content.next;
            if(nextNode == null){//list of size one
                String s = content.head;
                content = null;
                return s;
            }else{
                Node nextNextNode = nextNode.next;
                if(nextNextNode == null){
                    String s = nextNode.head;
                    content.next = null;
                    return s;
                }else{
                    WordList nextWordList = new WordList(nextNode);
                    return nextWordList.removeLast();
                }
            }
        }
    }

    void insert(String s){
        content = Node.insert(s, content);
    }

    void insertionSort(){
        if(content == null)return;
        content = Node.insertionSort(content);
    }

    String[] toArray(){
        int contentLength = this.length();
        String[] ret = new String[contentLength];
        Node nodeIterator = this.content;
        for(int i=0; i<contentLength; i++){
            ret[i] = nodeIterator.head;
            nodeIterator = nodeIterator.next;
        }
        return ret;
    }

    WordList(){
        content = null;
    }

    WordList(Node content){
        this.content = content;
    }

    WordList(String[] t){
        if(t.length == 0){
            content = null;
        }else{
            this.content = new Node(t[0], null);
            for(int i=1; i<t.length; i++){
                this.addLast(t[i]);
            }
        }
    }

}
