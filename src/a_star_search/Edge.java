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
public class Edge {

    public final double cost;
    public final Node next;

    public Edge(Node nextNode, double inputCost) {
        next = nextNode;
        cost = inputCost;
    }
}
