import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

    static int[] cost;
    static int[] value;

    static ArrayList<Integer>[] edges;//edges[i]是一个arraylist，存放与i 相邻的节点
    static int verticeCount;
    static int edgeCount;
    static int max;
    static int inDegree[];
    static void dfs(int x){
        if (cost[x]>max){//如果当前这个点的最大值比记录的最大值还大，就更新
            max = cost[x];
        }
        for (int i = 0;i<edges[x].size();i++){
            int y = edges[x].get(i);
            if (cost[x]+value[y]>cost[y]){
                cost[y] = cost[x]+value[y];
                dfs(y);
            }
        }

    }
    public static void main(String[] args) throws  Exception{
        File inputFile = new File("shortest.in");
        Scanner input = new Scanner(inputFile);

        verticeCount = input.nextInt();
        edgeCount = input.nextInt();


        //申请数组空间
        value = new int[verticeCount*2];
        edges = new ArrayList[verticeCount*2];
        cost = new int[verticeCount*2];
        inDegree = new int[verticeCount*2];
        for (int i = 1;i<=verticeCount;i++){
            edges[i] = new ArrayList<>();
        }


        //读入边和点的值
        for (int i = 1;i<=edgeCount;i++){
            int x = input.nextInt();
            int y = input.nextInt();
            edges[x].add(y);
            inDegree[y]++;
        }
        for (int i = 1;i<=verticeCount;i++){
            value[i] = input.nextInt();
            cost[i] = value[i];
        }

        //开始深搜遍历,从入度为0的点开始
        for (int i = 1;i<=verticeCount;i++){
            if (inDegree[i]==0 ) dfs(i);
        }
        System.out.println("the cost is "+max);
        input.close();

    }

}