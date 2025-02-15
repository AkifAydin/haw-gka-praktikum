package com.company;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.DefaultGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.company.A3.*;

public class A3Test {

    private static final Integer NUMBEROFGRAPHS = 20;

    private static final Graph gClauck = new DefaultGraph("gClauck");
    private static final Map<Edge, Float> cCklauck = new HashMap<>();

    private static final Graph gClauck2 = new DefaultGraph("gClauck2");
    private static final Map<Edge, Float> cCklauck2 = new HashMap<>();

    private static Graph k4Manual = new DefaultGraph("k4Manual");
    private static final Map<Edge, Float> k4ManualWeight = new HashMap<>();

    private static Graph autoTSP1;
    private static Map<Edge, Float> mapAutoTSP1;

    @BeforeAll
    public static void generate() {
        gClauck.addNode("A");
        gClauck.addNode("B");
        gClauck.addNode("C");
        gClauck.addNode("D");
        gClauck.addNode("E");

        gClauck.addEdge("AB", "A", "B");
        gClauck.addEdge("AC", "A", "C");
        gClauck.addEdge("AD", "A", "D");
        gClauck.addEdge("AE", "A", "E");
        gClauck.addEdge("BE", "B", "E");
        gClauck.addEdge("BC", "B", "C");
        gClauck.addEdge("BD", "B", "D");
        gClauck.addEdge("CD", "C", "D");
        gClauck.addEdge("CE", "C", "E");
        gClauck.addEdge("DE", "D", "E");


        cCklauck.put(gClauck.getEdge("AB"), 5f);
        cCklauck.put(gClauck.getEdge("AE"), 20f);
        cCklauck.put(gClauck.getEdge("AD"), 15f);
        cCklauck.put(gClauck.getEdge("AC"), 10f);
        cCklauck.put(gClauck.getEdge("BC"), 35f);
        cCklauck.put(gClauck.getEdge("BD"), 40f);
        cCklauck.put(gClauck.getEdge("BE"), 45f);
        cCklauck.put(gClauck.getEdge("CD"), 25f);
        cCklauck.put(gClauck.getEdge("CE"), 30f);
        cCklauck.put(gClauck.getEdge("DE"), 50f);


        //GRAPH 2
        gClauck2.addNode("A");
        gClauck2.addNode("B");
        gClauck2.addNode("C");
        gClauck2.addNode("D");
        gClauck2.addNode("E");
        gClauck2.addNode("F");

        gClauck2.addEdge("AB", "A", "B");
        gClauck2.addEdge("AD", "A", "D");
        gClauck2.addEdge("AC", "A", "C");
        gClauck2.addEdge("BE", "B", "E");
        gClauck2.addEdge("BD", "B", "D");
        gClauck2.addEdge("CD", "C", "D");
        gClauck2.addEdge("DE", "D", "E");
        gClauck2.addEdge("EF", "E", "F");
        gClauck2.addEdge("CF", "C", "F");


        cCklauck2.put(gClauck2.getEdge("AB"), 1f);
        cCklauck2.put(gClauck2.getEdge("AD"), 7f);
        cCklauck2.put(gClauck2.getEdge("AC"), 2f);
        cCklauck2.put(gClauck2.getEdge("BD"), 6f);
        cCklauck2.put(gClauck2.getEdge("BE"), 4f);
        cCklauck2.put(gClauck2.getEdge("CD"), 3f);
        cCklauck2.put(gClauck2.getEdge("DE"), 4f);
        cCklauck2.put(gClauck2.getEdge("EF"), 2f);
        cCklauck2.put(gClauck2.getEdge("CF"), 5f);


        //GRAPH 3
        k4Manual.addNode("A");
        k4Manual.addNode("B");
        k4Manual.addNode("C");
        k4Manual.addNode("D");

        k4Manual.addEdge("AB", "A", "B");
        k4Manual.addEdge("AC", "A", "C");
        k4Manual.addEdge("AD", "A", "D");

        k4Manual.addEdge("BC", "B", "C");
        k4Manual.addEdge("BD", "B", "D");

        k4Manual.addEdge("CD", "C", "D");

        k4ManualWeight.put(k4Manual.getEdge("AB"), 10f);
        k4ManualWeight.put(k4Manual.getEdge("AC"), 9f);
        k4ManualWeight.put(k4Manual.getEdge("AD"), 8f);
        k4ManualWeight.put(k4Manual.getEdge("BC"), 7f);
        k4ManualWeight.put(k4Manual.getEdge("BD"), 6f);
        k4ManualWeight.put(k4Manual.getEdge("CD"), 5f);


        //Auto TSP graphen
        Map<Graph, Map<Edge, Float>> graphWeightMapAutoTSP1= generateCompleteGraphWithTSP(10);
        autoTSP1 = graphWeightMapAutoTSP1.keySet().stream().toList().get(0);
        mapAutoTSP1 = graphWeightMapAutoTSP1.get(autoTSP1);


    }

    @Test
    public void testMinimalSpanningTree() {
        List<Edge> minSpannBaum = minimalSpanningTree(gClauck, cCklauck);
        List<Edge> minSpannBaum2 = minimalSpanningTree(gClauck2, cCklauck2);

        System.out.println("Graph 1");
        System.out.println("Expected: AB AC AD AE");
        System.out.println("Spannbaum: " + minSpannBaum);
        System.out.println("\nGraph2");
        System.out.println("Expected: AC DC AB BE EF");
        System.out.println("Spannbaum: " + minSpannBaum2);
    }

    @Test
    public void testNearestInsertion() {
        List<Node> rundreise = nearestInsertion(gClauck, cCklauck);
        List<Edge> minSpanningTree = minimalSpanningTree(gClauck, cCklauck);
        System.out.println("\n\tGraph 1\nRundreise: " + rundreise + "\nRundreise: " + getWeightOfCircle(gClauck, rundreise, cCklauck)
                + "\tSpanning tree: " + minSpanningTree + "\n" +
                "Spannbaum: " + getEdgesum(minSpanningTree, cCklauck) + "\n");


        Assert.assertThrows(IllegalArgumentException.class, () -> nearestInsertion(gClauck2, cCklauck2));

        List<Node> rundreise3 = nearestInsertion(k4Manual, k4ManualWeight);
        List<Edge> minSpanningTree3 = minimalSpanningTree(k4Manual, k4ManualWeight);
        System.out.println("\n\tGraph 3\nRundreise: " + rundreise3 + "\nRundreise: " + getWeightOfCircle(k4Manual, rundreise3, k4ManualWeight)
                + " \tSpanning tree: " + minSpanningTree3 + "\n" +
                "Spannbaum: " + getEdgesum(minSpanningTree3, k4ManualWeight) + "\n");
    }

    @Test
    public void testMinimumSpanningTreeHeuristic() {
        List<Node> rundreise = minimumSpanningTreeHeuristic(gClauck, cCklauck);
        List<Edge> minSpanningTree = minimalSpanningTree(gClauck, cCklauck);
        //System.out.println("Rundreise: " + rundreise);
        System.out.println("\n\tGraph 1\nRundreise: " + rundreise + "\nRundreise: " + getWeightOfCircle(gClauck, rundreise, cCklauck)
                + "\tSpanning tree: " + minSpanningTree + "\n" +
                "Spannbaum: " + getEdgesum(minSpanningTree, cCklauck) + "\n");


        List<Node> rundreise2 = minimumSpanningTreeHeuristic(k4Manual, k4ManualWeight);
        List<Edge> minSpanningTree2 = minimalSpanningTree(k4Manual, k4ManualWeight);
        //System.out.println("Rundreise: " + rundreise);
        System.out.println("\n\tGraph 2\nRundreise: " + rundreise2 + "\nRundreise: " + getWeightOfCircle(k4Manual, rundreise2, k4ManualWeight)
                + "\tSpanning tree: " + minSpanningTree2 + "\n" +
                "Spannbaum: " + getEdgesum(minSpanningTree2, k4ManualWeight) + "\n");
    }


    @Test
    public void testMinimalSpanningTreeAutoTSP(){
        System.out.println(autoTSP1.getNodeCount());
    }


    @Test
    public void testNearestInsertionAutoTSP(){
        List<Node> rundreise = nearestInsertion(autoTSP1, mapAutoTSP1);
        List<Edge> minSpanningTree = minimalSpanningTree(autoTSP1, mapAutoTSP1);

        for (Node n : rundreise) {
            Assertions.assertNotNull(autoTSP1.getNode(n.getId()));
        }
        assert(getWeightOfCircle(autoTSP1,rundreise,mapAutoTSP1) < 2 * getEdgesum(minSpanningTree,mapAutoTSP1));

    }

    @Test
    public void testMinimumSpanningTreeHeuristicAutoTSP(){
        List<Node> rundreise = minimumSpanningTreeHeuristic(autoTSP1, mapAutoTSP1);
        List<Edge> minSpanningTree = minimalSpanningTree(autoTSP1, mapAutoTSP1);

        for (Node n : rundreise) {
            Assertions.assertNotNull(autoTSP1.getNode(n.getId()));
        }
        System.out.println("rundreise weight: " + getWeightOfCircle(autoTSP1,rundreise,mapAutoTSP1) + "   weight tree: "
                + getEdgesum(minSpanningTree,mapAutoTSP1));
        assert(getWeightOfCircle(autoTSP1,rundreise,mapAutoTSP1) < 2 * getEdgesum(minSpanningTree,mapAutoTSP1));

    }

    @Test
    public void testAlgorithmsWithGeneratedTSP() {
        Float overallEuler = 0f;
        Float overallNearest = 0f;

        Float overallweightEuler = 0f;
        Float overallweightNearest = 0f;
        for (int i = 0; i < NUMBEROFGRAPHS; i++) {
            long currentTimeMl;
            Map<Graph, Map<Edge, Float>> graphAndMap = generateCompleteGraphWithTSP(100);
            Graph tspGraph = graphAndMap.keySet().stream().toList().get(0);
            Map<Edge, Float> weightmap = graphAndMap.get(tspGraph);

            List<Edge> minSpannBaum = minimalSpanningTree(tspGraph,weightmap);
            System.out.println("Weight von min spanning tree: " + getEdgesum(minSpannBaum,weightmap));
            currentTimeMl = System.currentTimeMillis();


            List<Node> rundreiseEuler = minimumSpanningTreeHeuristic(tspGraph,weightmap);
            System.out.println("Berechnung für eulerTour: " + (System.currentTimeMillis() - currentTimeMl) + "\n");
            overallEuler += System.currentTimeMillis() - currentTimeMl;


            currentTimeMl = System.currentTimeMillis();
            List<Node> rundreiseNearestInsertion = nearestInsertion(tspGraph,weightmap);
            System.out.println("Berechnung für nearestInsertion: " + (System.currentTimeMillis() - currentTimeMl) + "\n");
            overallNearest += System.currentTimeMillis() - currentTimeMl;


            System.out.println("Testlauf Nummer: " + (i+1) + "\nRundreise Euler Tour:\n Länge: " + getWeightOfCircle(tspGraph,rundreiseEuler,weightmap)+ "\t" + rundreiseEuler +
                    "\nRundreise nearest Insertion:\n Länge: " + getWeightOfCircle(tspGraph,rundreiseNearestInsertion,weightmap)+ "\t" + rundreiseNearestInsertion + "\n\n");

            overallweightEuler+=getWeightOfCircle(tspGraph, rundreiseEuler,weightmap);
            overallweightNearest+=getWeightOfCircle(tspGraph, rundreiseNearestInsertion,weightmap);

        }

        System.out.println("\n\n\nAverage eulerTour: " + overallEuler/NUMBEROFGRAPHS);
        System.out.println("Average weight of euler: " + overallweightEuler/NUMBEROFGRAPHS);
        System.out.println();

        System.out.println("Average nearestInsertion: " + overallNearest/NUMBEROFGRAPHS);
        System.out.println("Average weight of nearestInsertion: " + overallweightNearest/NUMBEROFGRAPHS);
        System.out.println();
        System.out.println("Time used for generating graphs from edge list in euler tour: " + usedTimeForGeneratingGraphFromEdge);

    }
}