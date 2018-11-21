import java.util.ArrayList;

public class graph2 {
    public static int NEGATIVE_INFINITY = -100000000;
    ArrayList<vertex> vertexs = new ArrayList<vertex>();
    ArrayList<ArrayList<vertex>> top = new ArrayList<ArrayList<vertex>>();

    graph2(int N){
        for(int i = 0; i < N; i++){
            vertex v = new vertex();
            vertexs.add(v);
        }
    }
    public class vertex{
        int color = 0; //white
        int weight;
        vertex pipi = null;
        int d = NEGATIVE_INFINITY;
        ArrayList<vertex> outVertexs = new ArrayList<vertex>();
        ArrayList<vertex> inVertexs = new ArrayList<vertex>();
        void setWeight(int _days){
            weight = _days;
        }
        void addInV(vertex v){
            inVertexs.add(v);
        }
        void addOutV(vertex v){
            outVertexs.add(v);
        }
    }

}
