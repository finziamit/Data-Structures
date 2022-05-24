package il.ac.telhai.ds.graph;

import il.ac.telhai.ds.misc.Friendship;
import org.junit.Assert;

import java.util.*;


public class Graph<V extends Comparable<V>,E > implements IGraph<V,E>{
    private class Edge{
        E label;
        double weight;
        V to;
        V from;
        public Edge(E w, V from, V to){
            this.label = w;
            this.to = to;
            this.from = from;
            this.weight = 0;
        }
        public void setWeight(double w){
            this.weight = w;
        }
    }

    private LinkedHashMap<V,LinkedList<Edge>> vertices;

    public Graph() {
        this.vertices = new LinkedHashMap<>();
    }

    private Edge getEdge(LinkedList<Edge> lst, V v){
        int i = 0;
        while (i < lst.size()){
            if (lst.get(i).to == v || lst.get(i).from == v){
                return lst.get(i);
            }
            i++;
        }
        return null;
    }

    private void setEdge(LinkedList<Edge> lst, V v, V u, E e){
        int i = 0;
        while (i < lst.size()){
            if ((lst.get(i).from == u && lst.get(i).to == v) || (lst.get(i).from == v && lst.get(i).to == u)){
                lst.get(i).label = e;
                break;
            }
            i++;
        }
    }


    @Override
    public void add(V v) {
        if (!this.vertices.containsKey(v)){
            this.vertices.put(v,new LinkedList<>());
        }
    }

    @Override
    public E getEdge(V u, V v) {
        LinkedList<Edge> lst = this.vertices.get(u);
        Edge e = getEdge(lst,v);
        if (e != null){
            return e.label;
        }
        else return null;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        LinkedList<Edge> lstU = this.vertices.get(u);
        LinkedList<Edge> lstV = this.vertices.get(v);
        if (lstU.contains(edgeLabel) && lstV.contains(edgeLabel)){
            setEdge(lstU,v,u,edgeLabel);
            setEdge(lstV,v,u,edgeLabel);
        }
        else{
            Edge e = new Edge(edgeLabel,u,v);
            if (edgeLabel instanceof Weighted){
                e.setWeight(((Weighted) edgeLabel).getWeight());
            }
            if (Number.class.isAssignableFrom(edgeLabel.getClass())){
                Number num = (Number)edgeLabel;
                e.setWeight(num.doubleValue());
            }
            lstU.add(e);
            lstV.add(e);
        }
        return edgeLabel;
    }

    @Override
    public boolean containsVertex(V v) {
        return this.vertices.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        this.vertices.remove(v);
    }

    @Override
    public E removeEdge(V u, V v) {
        if (!this.vertices.containsKey(u) || !this.vertices.containsKey(v)){
            throw new RuntimeException("Vertex does not exist");
        }
        if (this.vertices.containsKey(u) && this.vertices.containsKey(v)){
            LinkedList<Edge> lstU = this.vertices.get(u);
            LinkedList<Edge> lstV = this.vertices.get(v);
            Edge e = getEdge(lstU,v);
            if (e == null){
                return null;
            }
            lstV.remove(e);
            lstU.remove(e);
            return e.label;
        }
        return null;
    }

    @Override
    public double getWeight(V u, V v) {
        LinkedList<Edge> lst = this.vertices.get(u);
        Edge e = getEdge(lst,v);
        if (e == null){
            return 0;
        }
        if (e.label instanceof Weighted || Number.class.isAssignableFrom(e.label.getClass())){
            return e.weight;
        }
        throw new RuntimeException();
    }

    @Override
    public String toString(){
        String str = "";
        LinkedList<Edge> lst;
        Set<V> key = this.vertices.keySet();
        List<V> keyList = new ArrayList<>(key);
        for (int i=0; i<this.vertices.size();i++)
        {
            str = str+keyList.get(i).toString();
            if (i != this.vertices.size()-1)
                str+=(",");
        }
        return str;
    }

    @Override
    public String toStringExtended() {
        if (this.vertices.size() == 0){
            return "";
        }
        String str = "";
        LinkedList<Edge> lst;
        Set<V> key = this.vertices.keySet();
        List<V> keyList = new ArrayList<>(key);
        for (int i=0; i<this.vertices.size();i++)
        {
            str += keyList.get(i).toString();
            str += (":");
            lst = this.vertices.get(keyList.get(i));
            if (lst != null && lst.size() > 0){
                for (int j=0; j<lst.size(); j++)
                {
                    str += "{";
                    str += lst.get(j).from.toString();
                    str += ",";
                    str += lst.get(j).to.toString();
                    str += "}";
                    if (!(lst.get(j).label instanceof Friendship)){
                        str += "(";
                        str += lst.get(j).label.toString();
                        str += ")";
                    }
                    if (j != lst.size()-1){
                        str += ",";
                    }
                }
            }
            if(i!=this.vertices.size()-1)
            {
                str+="\n";
            }
        }
        return str;
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        Edge e = null;
        if (this.vertices.containsKey(u) && this.vertices.containsKey(v)) {
            LinkedList<Edge> lstU = this.vertices.get(u);
            e = getEdge(lstU, v);
        }
        return e != null;
    }

    public TreeMap<V, Double> distancesFrom(V v) {
        TreeMap<V, Double> tree = new TreeMap<>(); //2
        PriorityQueue<V> pq = new PriorityQueue<>(); //3
        Set<V> set = this.vertices.keySet();  //4
        ArrayList<V> vertices = new ArrayList<>(set); //5

        for(int i = 0; i < this.vertices.size(); ++i) {
            pq.add(vertices.get(i));
            if (vertices.get(i).compareTo(v) == 0) {
                tree.put(vertices.get(i), 0.0);
            } else {
                tree.put(vertices.get(i), Double.MAX_VALUE);
            }
        }

        while(!pq.isEmpty()) {
            V vertex = pq.remove();
            LinkedList<Edge> lst = this.vertices.get(vertex);

            for(int i = 0; i < lst.size(); ++i) {
                double weight = tree.get(vertex) + lst.get(i).weight;
                if (weight < tree.get(lst.get(i).to)) {
                    tree.put(lst.get(i).to, weight);
                }
                if (weight < tree.get(lst.get(i).from)) {
                    tree.put(lst.get(i).from, weight);
                }
            }
        }
        for(int i = 0; i < vertices.size(); ++i) {
            if (tree.get(vertices.get(i)) == Double.MAX_VALUE){
                tree.remove(vertices.get(i));
            }
        }
        return tree;
    }
}