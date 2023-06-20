public class Prefix {
    String[] t;

    final static String start = "<START>", end = "<END>", par = "<PAR>";

    public Prefix(int n){
        t = new String[n];
        for(int i=0; i<n; i++){
            t[i] = start;
        }
    }

    static boolean eq(Prefix p1, Prefix p2){
        String[] t1 = p1.t;
        String[] t2 = p2.t;
        boolean allEqual = (t1.length == t2.length);
        for(int i=0; i<t1.length && allEqual; i++){
            allEqual &= t1[i].equals(t2[i]);
        }
        return allEqual;
    }

    Prefix addShift(String w){
        int prefLength = t.length;
        Prefix newPrefix = new Prefix(prefLength);
        for(int i=0; i < prefLength - 1; i++){
            newPrefix.t[i] = t[i+1];
        }
        newPrefix.t[prefLength - 1] = w;
        return newPrefix;
    }

    public int hashCode(){
        int h = 0;
        for(int i=0; i<t.length; i++){
            h = 37*h + t[i].hashCode();
        }
        return h;
    }

    public int hashCode(int n){
        int ret = hashCode()%n;
        if(ret < 0)ret += n;
        return ret;
    }

}
