//
//  Utils.h
//  Graphs
//
//  Created by Mihai Costea on 18/10/13.
//
//

#ifndef __Graphs__Utils__
#define __Graphs__Utils__

#include <iostream>
#include <fstream>
#include <string>

#include "Graph.h"

using namespace std;

namespace Utils {
    template <typename T>
    static Graph<T>* readGraphFromFile(string filename);
};

#pragma mark - Implementation

template <typename T>
Graph<T>* Utils::readGraphFromFile(string filename) {
    fstream file;
    file.open(filename, ios::in);
    int numberOfVertices, numberOfEdges;
    Graph<T> *graph = new Graph<T>();
    if (file.is_open()) {
        file >> numberOfVertices >> numberOfEdges;
        for (int it=0; it<numberOfVertices; it++) {
            graph->addVertex(it);
        }
        while (file.good()) {
            T origin, destination;
            int cost;
            file >> origin >> destination >> cost;
            graph->addEdge(origin, destination, cost, kOutboundEdge);
            graph->addEdge(destination, origin, cost, kInboundEdge);
        }
    }
    file.close();
    
    return graph;
}

#endif /* defined(__Graphs__Utils__) */
