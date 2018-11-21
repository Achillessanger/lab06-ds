import java.util.ArrayList;

public class graph {
    ArrayList<vertex> vertexs;
    ArrayList<edge> edges;

    graph(){

    }
    public class vertex{
        int days;
        ArrayList<edge> inEdge;
        ArrayList<edge> outEdge;
        vertex(int _days){
            days = _days;
            vertexs.add(this);
        }

    }
    public class edge{
        vertex vHead ;
        vertex vTail;
        edge(vertex _vHead, vertex _vTail){
            vHead = _vHead;
            vTail = _vTail;
            vHead.outEdge.add(this);
            vTail.inEdge.add(this);
            edges.add(this);
        }
    }
}
