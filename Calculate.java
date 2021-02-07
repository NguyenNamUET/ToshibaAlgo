import java.util.Scanner;

public class Calculate {
    private int n;
    private int k;
    private int[][] path = null;
    private int[] passngers1d = null;
    private boolean[] visited1d = null;
    private int minPath = Integer.MAX_VALUE;
    private String shortestRoute = "";
    public void doInput(){
        Scanner input = new Scanner(System.in);
        this.n = input.nextInt();
        this.k = input.nextInt();
        this.path = new int[2*n+1][2*n+1];
        this.passngers1d= new int[2*n];
        this.visited1d= new boolean[2*n];
        for(int i=0; i<2*n+1; i++){
            for(int j=0; j<2*n+1; j++){
                this.path[i][j] = input.nextInt();
            }
            if(i!=2*n) {
                this.passngers1d[i]=i+1;
                this.visited1d[i]=false;
            }
        }
    }
    private int findPos(int[] arr, int num, int start, int end){
        int result = 0;
        for(int i=start; i<=end; i++){
            if(arr[i]==num){
                result=i;
                break;
            }
        }
        return result;
    }
    private boolean exist(int[] arr, int num, int start, int end){
        boolean result = false;
        for(int i=start; i<=end; i++){
            if(arr[i]==num){
                result=true;
                break;
            }
        }
        return result;
    }
    private void checkSolution(){
        int seat = k;
        boolean valid = true;
        for(int i=0; i<2*n; i++){
            if(passngers1d[i]<=n) seat--;
            else if(passngers1d[i]>=n+1 && exist(passngers1d, passngers1d[i]-n, 0, i)) seat+=1;
            if(seat < 0) valid = false;
            if(passngers1d[i]<=n && findPos(passngers1d, passngers1d[i]+n, 0, 2*n-1)<i){
                valid=false;
                break;
            }
        }
        if(valid){
            int sum = path[0][passngers1d[0]];
            String shortestPath = "0->";
            for(int i=0; i<2*n-1; i++){
                sum+=path[passngers1d[i]][passngers1d[i+1]];
                shortestPath += passngers1d[i]+"->";
            }
            shortestPath += passngers1d[2*n-1]+"->0";
            sum+=path[passngers1d[2*n-1]][0];
            if(sum<minPath){
                minPath=sum;
                shortestRoute=shortestPath;
            }
            System.out.println(shortestPath+" with length="+sum);
        }
    }
    private boolean check(int v, int k){
        return visited1d[v];
    }
    public void doBacktrack(int k){
        for(int v=0; v<2*n; v++){
            if(!check(v,k)){
                passngers1d[k]=v+1;
                visited1d[v]=true;
                if(k==2*n-1) checkSolution();
                else doBacktrack(k+1);
                visited1d[v]=false;
            }
        }
    }
    public void printResult(){
        System.out.println("FINAL RESULT: "+shortestRoute+" with length="+minPath);
    }
}
