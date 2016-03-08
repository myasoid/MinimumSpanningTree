package ua.in.telephone.mst;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TelephoneNetworkTest {

    private String fileName;
    private boolean expected;

    public TelephoneNetworkTest(String fileName, boolean expected) {
        this.fileName = fileName;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        File dir = new File("src/test/java/ua/in/telephone/mst");
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        Object[][] arr = new Object[files.length][2];

        for (int i = 0; i < files.length; i++) {
            arr[i][0] = files[i].getPath();
            arr[i][1] = true;
        }

        return Arrays.asList(arr);
    }

    @Test(timeout = 300000)
    public void test() {
        System.out.println(fileName);
        In in = new In(fileName);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        Long start = System.nanoTime();
        edu.princeton.cs.algs4.KruskalMST algs4KruskalMST = new edu.princeton.cs.algs4.KruskalMST(G);
        System.out.println("algs4KruskalMST: " + (System.nanoTime() - start) / 1e9);



        in = new In(fileName);
        int V = in.readInt();
        int E = in.readInt();
        List<Edge> list = new ArrayList<Edge>(E);
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            list.add(e);
        }
        TelephoneNetwork telephoneNetwork = new TelephoneNetwork();
        start = System.nanoTime();
        double minCost = telephoneNetwork.minCost(V, list);
        System.out.println("telephoneNetwork: " + (System.nanoTime() - start) / 1e9);

        Assert.assertEquals(algs4KruskalMST.weight(), minCost, 0.0000001d);
    }
}

