public class Percolation{
    static int size = 10;
    static int length = size*size;
    static boolean[] grid = new boolean[length];
    static int[] fisherYatesOrder = new int[length];
    static int nextRandomCase = 0;
    static boolean test = true;

    /* void FisherYatesAlgorithm()
     * computer une ordre aléatoire avec l'algorithme Fisher Yates
     */
    public static void FisherYatesAlgorithm(){
        for(int i=0; i<length; i++){
            fisherYatesOrder[i] = i;
        }
        for(int i=length-1; i>=1; i--){
            int j = (int)(Math.random()*i);//j aléatoire tel que 0<=j<=i
            fisherYatesOrder[i] = (fisherYatesOrder[i]^fisherYatesOrder[j]); //swap i,j
            fisherYatesOrder[j] = (fisherYatesOrder[i]^fisherYatesOrder[j]);
            fisherYatesOrder[i] = (fisherYatesOrder[i]^fisherYatesOrder[j]);
        }
        nextRandomCase = 0;
    }

    /* void init()
     * initialize tout les cases de grid comme blanches et l'ordre Fisher Yates
     * initialize structure UnionFind
     */
    public static void init(){
        for(int i=0; i<length; i++){
            grid[i] = false;
        }
        UnionFind.init(length + 2);//avec les deux cases speciaux pour optimisation
    }

    /* void print()
     * imprimer grid en forme de matrice
     */
    public static void print(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                char c = grid[size*i + j] ? '*' : '-'; //convert bool to char
                System.out.print(c);
            }
            System.out.println();
        }
    }

    
    /* int randomShadow()
     * prendre prochaine case aléatoire dans ordre Fisher Yates
     * appele propagateUnion dans nouvelle case
     */
    public static int randomShadow(){
        int randomCase = fisherYatesOrder[nextRandomCase];
        nextRandomCase = (nextRandomCase + 1)%length;
        grid[randomCase] = true;
        propagateUnion(randomCase);
        return randomCase;
    }

    /* int[] generateCellNeighbors(int n)
     * creer vecteur avec voisins
     */
    static int[] generateCellNeighbors(int n){
        int[] neighbors = new int[4];
        neighbors[0] = (n/size == (n-1)/size) ? n - 1 : -1; //gauche
        neighbors[1] = (n/size == (n+1)/size) ? n + 1 : -1; //droite
        neighbors[2] = n - size; //dessus
        neighbors[3] = n + size; //dessous
        return neighbors;
    }

    /* boolean detectPath(boolean[] seen, int n, boolean up)
     * si up==true => cherche chemin entre case n et ligne 0
     * si up==false => cherche chemin entre case n et ligne size-1
     */
    public static boolean detectPath(boolean[] seen, int n, boolean up){
        
        seen[n] = true; //marque case comme visite

        if((up && n<size) || (!up && n+size>=length))return true;

        int[] neighbors = generateCellNeighbors(n);
        boolean resp = false;
        for(int neighbor : neighbors){
            if(!validCase(neighbor))continue;
            if(grid[neighbor] && !seen[neighbor]){
                resp |= detectPath(seen, neighbor, up);
            }
        }
        return resp;
    }

    /* boolean validCase(int n)
     * verifie si la case est dans la matrice
     */
    public static boolean validCase(int n){
        return (n>=0 && n<length);
    }

    /* boolean isNaivePercolation(int n)
     * approche utilisant DFS
     */
    public static boolean isNaivePercolation(int n){

        boolean[] seen = new boolean[length];

        for(int i=0; i<length; i++)seen[i] = false;
        boolean upperSemiPath = detectPath(seen, n, true);

        for(int i=0; i<length; i++)seen[i] = false;
        boolean lowerSemiPath = detectPath(seen, n, false);

        return (upperSemiPath && lowerSemiPath);
    }

    /* boolean isPercolation(int n)
     * indique chemin entre la ligne 0 et la ligne size-1 passant par case n
     */
    public static boolean isPercolation(int n){
        return isLogPercolation();
    }

    /* double percolation()
     * noirci des cases au hasard jusqu'a ce qu'on trouve percolation
     */
    public static double percolation(){

        FisherYatesAlgorithm();

        double blackCases = 0;
        do{
            blackCases++;
        }while(!isPercolation(randomShadow()));
        return (blackCases/length);

    }

    /* double monteCarlo(int n)
     * estimation du seuil de percolation en effectuant n simulations de percolation
     */
    public static double monteCarlo(int n){
        double resp = 0;
        for(int i=0; i<n; i++){
            Percolation.init();
            double nthSimulation = Percolation.percolation();
            resp = (i*resp + nthSimulation)/(i+1);//nouvelle moyenne
        }
        return resp;
    }

    /* void propagateUnion(int x)
     * uni la classe de x a tous ces voisins noires
     */
    public static void propagateUnion(int x){
        if(x < size)UnionFind.union(x, length);
        if(x+size >= length)UnionFind.union(x, length+1);

        int[] neighbors = generateCellNeighbors(x);
        for(int neighbor : neighbors){
            if(!validCase(neighbor))continue;
            if(grid[neighbor]){
                UnionFind.union(neighbor, x);
            }
        }

    }

    /* boolean isFastPercolation(int n)
     * percolation avec classe UnionFind
     */
    public static boolean isFastPercolation(int n){
        int nEquivClass = UnionFind.find(n);

        boolean upperSemiPath = false;
        for(int i=0; i<size; i++){
            upperSemiPath |= (UnionFind.find(i) == nEquivClass);
        }

        boolean lowerSemiPath = false;
        for(int i=length-size; i<length; i++){
            lowerSemiPath |= (UnionFind.find(i) == nEquivClass);
        }

        return (upperSemiPath && lowerSemiPath);
    }

    /* boolean isLogPercolation()
     * percolation avec classe UnionFind et deux cases speciaux
     */
    public static boolean isLogPercolation(){
        return (UnionFind.find(length) == UnionFind.find(length + 1));
    }

    public static void main(String[] args) throws Exception {
        double startTime = System.currentTimeMillis();
        int n = Integer.parseInt(args[0]);
        double endTime = System.currentTimeMillis();
        System.out.println("Estimated rate of black cells: " + monteCarlo(n));
        System.out.println("Run time: " + (endTime - startTime));
    }

}