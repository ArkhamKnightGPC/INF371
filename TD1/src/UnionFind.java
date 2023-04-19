public class UnionFind {
    static int[] equiv;
    static int[] height;

    /* void init()
     * initialize tableau equiv
     */
    public static void init(int len){
        equiv = new int[len];
        height = new int[len];

        for(int i=0; i<len; i++){
            equiv[i] = i;
            height[i] = 1;
        }
    }

    /* int naiveFind(int x)
     * retourne le representant canonique associe a x
     */
    public static int naiveFind(int x){
        if(x == equiv[x])return equiv[x];
        return naiveFind(equiv[x]);
    }

    /* int naiveFind(int x)
     * realise union des classes de x,y
     */
    public static int naiveUnion(int x, int y){
        x = naiveFind(x);
        y = naiveFind(y);
        equiv[x] = y;
        return y;
    }

    /* int fastFind(int x)
     */
    public static int fastFind(int x){
        if(x == equiv[x])return equiv[x];
        return equiv[x] = fastFind(equiv[x]);
    }

    /* int fastUnion(int x)
     */
    public static int fastUnion(int x, int y){
        x = fastFind(x);
        y = fastFind(y);
        equiv[x] = y;
        return y;
    }

    /* int logFind(int x)
     */
    public static int logFind(int x){
        if(x == equiv[x])return equiv[x];
        return equiv[x] = logFind(equiv[x]);
    }

    /* int logUnion(int x)
     */
    public static int logUnion(int x, int y){
        x = logFind(x);
        y = logFind(y);
        if(height[x] < height[y]){
            equiv[x] = y;
        }else if(height[y] < height[x]){
            equiv[y] = x;
        }else{
            equiv[x] = y;
            height[y]++;
        }
        return equiv[x];
    }

    public static int find(int x){
        return logFind(x);
    }

    public static int union(int x, int y){
        return logUnion(x, y);
    }
}
