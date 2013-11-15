//
//  main.cpp
//  Graphs
//
//  Created by Mihai Costea on 6/10/13.
//
//

#include <iostream>

#include "Graph.h"
#include "Utils.h"

using namespace std;

int main(int argc, const char * argv[]) {
    
    
    // Test cases
    Graph<int> *graph = Utils::readGraphFromFile<int>("graph.txt");
    
    cout << "Number of vertices: " << graph->numberOfVertices() << endl;
    
    cout << "Number of edges: " << graph->numberOfEdges() << endl;
    
    cout << "Edge cost between 0 and 0 is " << graph->getEdgeBetweenVertices(0, 0)->cost << endl;
    cout << "Edge cost between 2 and 1 is " << graph->getEdgeBetweenVertices(2, 1)->cost << endl;
    
    cout << "Inbound degree of 2 is " << graph->getInDegree(2) << endl;
    
    cout << "Outbound degree of 2 is " << graph->getOutDegree(2) << endl;
    
    for (set<GraphEdge<int> *>::iterator it=graph->beginEdgeIterator(1, kOutboundEdge); it != graph->endEdgeIterator(1, kOutboundEdge); ++it) {
        GraphEdge<int> *edge = *it;
        cout << "Edge cost from 1: " << edge->cost << endl;
    }
    
    for (set<GraphEdge<int> *>::iterator it=graph->beginEdgeIterator(1, kInboundEdge); it != graph->endEdgeIterator(1, kInboundEdge); ++it) {
        GraphEdge<int> *edge = *it;
        cout << "Edge cost to 1: " << edge->cost << endl;
    }
    
    delete graph;
    
    return 0;
}

