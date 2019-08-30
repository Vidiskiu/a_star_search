/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a_star_search;

/**
 *
 * @author KMKATH
 */
public class Node {

    public final String name;
    public double realCost;
    public final double hCost;
    public double tCost = 0;
    public Edge[] adjacencies;
    public Node parent;

    public Node(String inputName, double inputhCost) {
        name = inputName;
        hCost = inputhCost;
    }

    public String toString() {
        return name;
    }

}
