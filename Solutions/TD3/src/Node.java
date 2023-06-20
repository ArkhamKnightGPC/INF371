public class Node {
    String head;
    Node next;

    Node(String head, Node next){
        this.head = head;
        this.next = next;
    }

    static int lengthRec(Node l){
        if(l == null)return 0;
        if(l.next == null)return 1;
        return 1 + lengthRec(l.next);
    }

    static int length(Node l){
        int ans = 0;
        for(Node cur = l; cur != null; cur = cur.next){
            ans++;
        }
        return ans;
    }

    static String makeString(Node l){
        String ans = "[";
        for(Node cur = l; cur != null; cur = cur.next){
            if(cur != l)ans+=", ";
            ans += cur.head;
        }
        ans += "]";
        return ans;
    }

    static void addLast(String s, Node l){
        Node newNode = new Node(s, null);
        if(l == null)l = newNode;
        else{
            Node copyl = l;
            while(l.next != null){
                l = l.next;
            }
            l.next = newNode;
            l = copyl;
        }
    }

    static Node copy(Node the){
        Node ans = null;
        Node copyCur = null;
        Node copyPrev = null;

        for(Node cur = the; cur != null; cur = cur.next){
            copyCur = new Node(cur.head, null);
            if(cur == the)
                ans = copyCur;//save beginning of copied list
            if(copyPrev != null)
                copyPrev.next = copyCur;
            //prepare for next iteration
            copyPrev = copyCur;
        }

        return ans;
    }

    static Node insert(String s, Node l){
        Node newNode = new Node(s, null);
        if(l == null)return l = newNode;

        boolean everyoneSmaller = true;
        boolean everyoneBigger = true;
        for(Node cur = l; cur != null; cur = cur.next){
            int compareStrings = cur.head.compareTo(s);
            if(compareStrings < 0)everyoneBigger = false;
            if(compareStrings > 0)everyoneSmaller = false;
        }
        if(everyoneSmaller){
            addLast(s, l);
            return l;
        }else if(everyoneBigger){
            newNode.next = l;
            return l = newNode;
        }else{
            int compareStrings = -1;
            Node cur = l;
            while(compareStrings <= 0){
                Node nextNode = cur.next;
                compareStrings = nextNode.head.compareTo(s);
                if(compareStrings > 0){
                    newNode.next = nextNode;
                    cur.next = newNode;
                }
                cur = nextNode;
            }
            return l;
        }
    }

    static Node insertionSort(Node l){
        Node sortedChain = new Node(l.head, null);
        for(Node cur = l.next; cur != null; cur = cur.next){
            sortedChain = insert(cur.head, sortedChain);
            //System.out.println(makeString(sortedChain));
        }
        return sortedChain;
    }

    static Node merge(Node l1, Node l2){
        Node mergedList = null;
        while(l1 != null && l2 != null){
            int compareStrings = l1.head.compareTo(l2.head);
            if(compareStrings < 0){
                if(mergedList != null){
                    Node.addLast(l1.head, mergedList);
                }else{
                    mergedList = new Node(l1.head, null);
                }
                l1 = l1.next;
            }else{
                if(mergedList != null){
                    Node.addLast(l2.head, mergedList);
                }else{
                    mergedList = new Node(l2.head, null);
                }
                l2 = l2.next;
            }
        }
        while(l1 != null){
            if(mergedList != null){
                Node.addLast(l1.head, mergedList);
            }else{
                mergedList = new Node(l1.head, null);
            }
            l1 = l1.next;
        }
        while(l2 != null){
            if(mergedList != null){
                Node.addLast(l2.head, mergedList);
            }else{
                mergedList = new Node(l2.head, null);
            }
            l2 = l2.next;
        }
        return mergedList;
    }

    Node recursiveMergeSort(Node l){
        int arrayLength = Node.length(l);
        if(arrayLength <= 1)return l; //array of size 1 is sorted

        Node rightSubArray = l;
        for(int i=0; i<arrayLength/2; i++){
            rightSubArray = rightSubArray.next;
        }

        Node aux = l;
        while(aux.next != rightSubArray)aux = aux.next;
        aux.next = null;//erase pointer between subarrays

        l = recursiveMergeSort(l);
        rightSubArray = recursiveMergeSort(rightSubArray);
        return merge(l, rightSubArray);
    }

    void mergeSort(){
        Node sortedNode = recursiveMergeSort(this);
        this.head = sortedNode.head;
        this.next = sortedNode.next;
    }
    
}
