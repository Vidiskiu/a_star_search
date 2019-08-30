package a_star_search;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class A_star_search {

    //h scores is the stright-line distance from the current city to Bucharest
    public static void main(String[] args) {

        System.out.println("Enter the nodes informations : ");
        //initialize the graph base on the Romania map

        Node n1 = new Node("Arad", 366);
        Node n2 = new Node("Zerind", 374);
        Node n3 = new Node("Oradea", 380);
        Node n4 = new Node("Sibiu", 253);
        Node n5 = new Node("Fagaras", 176);
        Node n6 = new Node("Rimnicu Vilcea", 193);
        Node n7 = new Node("Pitesti", 98);
        Node n8 = new Node("Timisoara", 329);
        Node n9 = new Node("Lugoj", 244);
        Node n10 = new Node("Mehadia", 241);
        Node n11 = new Node("Drobeta", 242);
        Node n12 = new Node("Craiova", 160);
        Node n13 = new Node("Bucharest", 0);
        Node n14 = new Node("Giurgiu", 77);

        //initialize the edges
        //Arad
        n1.adjacencies = new Edge[]{
            new Edge(n2, 75),
            new Edge(n4, 140),
            new Edge(n8, 118)
        };

        //Zerind
        n2.adjacencies = new Edge[]{
            new Edge(n1, 75),
            new Edge(n3, 71)
        };

        //Oradea
        n3.adjacencies = new Edge[]{
            new Edge(n2, 71),
            new Edge(n4, 151)
        };

        //Sibiu
        n4.adjacencies = new Edge[]{
            new Edge(n1, 140),
            new Edge(n5, 99),
            new Edge(n3, 151),
            new Edge(n6, 80),};

        //Fagaras
        n5.adjacencies = new Edge[]{
            new Edge(n4, 99),
            //178
            new Edge(n13, 211)
        };

        //Rimnicu Vilcea
        n6.adjacencies = new Edge[]{
            new Edge(n4, 80),
            new Edge(n7, 97),
            new Edge(n12, 146)
        };

        //Pitesti
        n7.adjacencies = new Edge[]{
            new Edge(n6, 97),
            new Edge(n13, 101),
            new Edge(n12, 138)
        };

        //Timisoara
        n8.adjacencies = new Edge[]{
            new Edge(n1, 118),
            new Edge(n9, 111)
        };

        //Lugoj
        n9.adjacencies = new Edge[]{
            new Edge(n8, 111),
            new Edge(n10, 70)
        };

        //Mehadia
        n10.adjacencies = new Edge[]{
            new Edge(n9, 70),
            new Edge(n11, 75)
        };

        //Drobeta
        n11.adjacencies = new Edge[]{
            new Edge(n10, 75),
            new Edge(n12, 120)
        };

        //Craiova
        n12.adjacencies = new Edge[]{
            new Edge(n11, 120),
            new Edge(n6, 146),
            new Edge(n7, 138)
        };

        //Bucharest
        n13.adjacencies = new Edge[]{
            new Edge(n7, 101),
            new Edge(n14, 90),
            new Edge(n5, 211)
        };

        //Giurgiu
        n14.adjacencies = new Edge[]{
            new Edge(n13, 90)
        };

        AstarSearch(n9, n13);

        List<Node> path = printSolutionPath(n13);

        System.out.println("Path Solution : " + path);
    }

    public static List<Node> printSolutionPath(Node target) {
        List<Node> path = new ArrayList<Node>();

        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
        }

        Collections.reverse(path);

        return path;
    }

    public static void AstarSearch(Node source, Node goal) {

        float totalCost = 0;

        Set<Node> bucketB = new HashSet<Node>();

        PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
                new Comparator<Node>() {
            //override compare method
            public int compare(Node i, Node j) {
                if (i.tCost > j.tCost) {
                    return 1;
                } else if (i.tCost < j.tCost) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }
        );

        //cost from start
        source.realCost = 0;

        queue.add(source);

        boolean found = false;
        double costResult = 0;

        List expansionOrder = new ArrayList();
        int counter = 1;
        while ((!queue.isEmpty()) && (!found)) {

            //the node in having the lowest f_score name
            Node current = queue.poll();
            expansionOrder.add(current.name);

            bucketB.add(current);

            //goal found
            if (current.name.equals(goal.name)) {
                found = true;
            }

            System.out.print("Iterations " + counter + " : ");
            //check every child of current node
            for (Edge e : current.adjacencies) {
                Node child = e.next;
                double cost = e.cost;
                double temp_realCost = current.realCost + cost;
                double temp_tCost = temp_realCost + child.hCost;
                System.out.print("(" + current.name + "," + child.name + "," + temp_tCost + ") || ");
                /*if child node has been evaluated and 
                                the newer f_score is higher, skip*/
                if ((bucketB.contains(child))
                        && (temp_tCost >= child.tCost)) {
                    continue;
                } /*else if child node is not in queue or 
                                newer f_score is lower*/ else if ((!queue.contains(child))
                        || (temp_tCost < child.tCost)) {

                    child.parent = current;
                    child.realCost = temp_realCost;
                    child.tCost = temp_tCost;

                    if (queue.contains(child)) {
                        queue.remove(child);

                    }

                    queue.add(child);

                }
                costResult = current.realCost;
            }
            System.out.println();
            counter++;
        }

        System.out.println("Expansion Order : " + expansionOrder);

        System.out.println("Path Cost : " + costResult);
    }

}
