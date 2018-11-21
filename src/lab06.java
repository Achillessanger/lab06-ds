import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class lab06{
    public static int NEGATIVE_INFINITY = -100000000;
    public static ArrayList<graph2.vertex> longestAL;
    public static int bigest = 0;

    public static void DFS(graph2 G){
        for (graph2.vertex vertex : G.vertexs){
           if(vertex.color == 0){
               ArrayList<graph2.vertex> topTmp = new ArrayList<graph2.vertex>();
               DFS_VISIT(vertex,G,topTmp);
               G.top.add(topTmp);
           }
        }
    }
    public static void DFS_VISIT(graph2.vertex vertex,graph2 G,ArrayList<graph2.vertex> topTmp){
        vertex.color = 1; //grey
        for(graph2.vertex vertexadj : vertex.outVertexs){
            if(vertexadj.color == 0){
                DFS_VISIT(vertexadj,G,topTmp);
            }
        }
        vertex.color = 2; //black
        topTmp.add(vertex);  //反向存的
    }
    public static void DAG_SHORTEST_PATH(graph2 G){
        DFS(G);
        for (ArrayList<graph2.vertex> top : G.top){
            if(top.get(0).d == NEGATIVE_INFINITY){
                top.get(0).d = top.get(0).weight;
            }
            for(graph2.vertex u : top){
                for(graph2.vertex v : u.inVertexs){
                    if(v.d < u.d + v.weight){  //v是u前面那个  v--u
                        v.d = u.d + v.weight;
                        v.pipi = u;
                    }
                }
            }
            if(top.get(0).d > bigest){
                bigest = top.get(0).d;
                longestAL = top;
            }
        }
    }
    public static void print(graph2 G){
        String path = " ";
        graph2.vertex vertexTmp = longestAL.get(longestAL.size()-1);
        while (vertexTmp != null){

                path = path + (G.vertexs.indexOf(vertexTmp)+1)+"/"+vertexTmp.weight + " ";
               // path = path + (-vertexTmp.weight) + " ";
                vertexTmp = vertexTmp.pipi;
        }
        System.out.println("路径(第__个/weight): "+ path);
        System.out.println("总值： " + bigest);
    }
    public static void main(String[] args) throws IOException {
        java.io.File file = new java.io.File("shortest.in");
        Scanner sc = new Scanner(file,"GBK");
       // graph2 G2 = new graph2(7);
        int N = sc.nextInt();
        int M = sc.nextInt();
        graph2 G = new graph2(N);
        for(int i = 0; i < M; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            G.vertexs.get(y-1).addInV(G.vertexs.get(x-1));
            G.vertexs.get(x-1).addOutV(G.vertexs.get(y-1));
        }
        for(int i = 0; i < N; i++){
            G.vertexs.get(i).setWeight(sc.nextInt());
        }
        long startTime=System.currentTimeMillis();
        DAG_SHORTEST_PATH(G);
        long endTime=System.currentTimeMillis();
        print(G);
        System.out.println("用时（ms): " + (endTime - startTime));
    }
}
