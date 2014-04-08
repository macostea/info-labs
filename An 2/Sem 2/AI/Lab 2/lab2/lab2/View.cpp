//
//  View.cpp
//  lab2
//
//  Created by Mihai Costea on 08/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "View.h"

#include <fstream>

using namespace std;

View::View(Controller *controller) {
    this->controller = controller;
}

void View::readGraphFromFile(std::string fileName) {
    std::ifstream file;
    file.open(fileName);
    int numberOfVertices, numberOfEdges;
    
    Graph *graph = new Graph();
    
    if (file.is_open()) {
        file >> numberOfVertices >> numberOfEdges;
        std::set<int> vertices;
        for (int it=1; it<=numberOfVertices; it++) {
            vertices.insert(it);
        }
        
        graph->nodes = vertices;
        std::vector<Edge *> edges;
        
        for (int it=0; it<numberOfEdges; it++) {
            int source, destination;
            file >> source >> destination;
            Edge *edge = new Edge(source, destination);
            edges.push_back(edge);
        }
        
        graph->edges = edges;
        
        file.close();
    }
    
    this->controller->graph = graph;
}

void View::getSolution() {
    SearchResult searchResult = this->controller->findSolution();
    
    cout << "Solution:" << endl;
    for (set<Edge *>::iterator it=searchResult.e1.begin(); it!=searchResult.e1.end(); ++it) {
        Edge *edge = *it;
        cout << edge->source << "," << edge->destination << " ; ";
    }
    
    cout << endl;
    
    for (set<Edge *>::iterator it=searchResult.e2.begin(); it!=searchResult.e2.end(); ++it) {
        Edge *edge = *it;
        cout << edge->source << "," << edge->destination << " ; ";
    }
    
    cout << endl;
}