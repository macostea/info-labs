//
//  Utils.cpp
//  lab2
//
//  Created by Mihai Costea on 07/04/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include "Utils.h"

size_t numberOfEdgesOutsideTriangle(std::set<Edge *> &edges) {
    std::set<int> nodes;
    
    std::set<Edge *> edgesPartOfTriangle;
    
    for (std::set<Edge *>::iterator it=edges.begin(); it != edges.end(); ++it) {
        Edge *edge = *it;
        nodes.insert(edge->source);
        nodes.insert(edge->destination);
    }
    
    for (std::set<Edge *>::iterator it=edges.begin(); it != edges.end(); ++it){
        for (std::set<int>::iterator jt=nodes.begin(); jt != nodes.end(); ++jt) {
            Edge *edge1 = nullptr, *edge2 = nullptr;
            
            for (std::set<Edge *>::iterator kt=edges.begin(); kt != edges.end(); ++kt) {
                Edge *edge = *kt;
                Edge *initialEdge = *it;
                int node = *jt;
                if ((initialEdge->source == edge->source && node == edge->destination) || (initialEdge->source == edge->destination && node == edge->source)) {
                    edge1 = edge;
                    break;
                }
            }
            
            for (std::set<Edge *>::iterator kt=edges.begin(); kt != edges.end(); ++kt) {
                Edge *edge = *kt;
                Edge *initialEdge = *it;
                int node = *jt;
                if ((initialEdge->destination == edge->source && node == edge->destination) || (initialEdge->destination == edge->destination && node == edge->source)) {
                    edge2 = edge;
                    break;
                }
            }
            
            if (edge1 != nullptr && edge2 != nullptr) {
                edgesPartOfTriangle.insert(edge1);
                edgesPartOfTriangle.insert(edge2);
                Edge *edge3 = *it;
                edgesPartOfTriangle.insert(edge3);
            }
            
        }
    }
    
    return edges.size() - edgesPartOfTriangle.size();
}

size_t numberOfEdgesOutsideTriangle(std::vector<Edge *> &edges) {
    std::set<int> nodes;
    
    std::set<Edge *> edgesPartOfTriangle;
    
    for (std::vector<Edge *>::iterator it=edges.begin(); it != edges.end(); ++it) {
        Edge *edge = *it;
        nodes.insert(edge->source);
        nodes.insert(edge->destination);
    }
    
    for (std::vector<Edge *>::iterator it=edges.begin(); it != edges.end(); ++it){
        for (std::set<int>::iterator jt=nodes.begin(); jt != nodes.end(); ++jt) {
            Edge *edge1 = nullptr, *edge2 = nullptr;
            
            for (std::vector<Edge *>::iterator kt=edges.begin(); kt != edges.end(); ++kt) {
                Edge *edge = *kt;
                Edge *initialEdge = *it;
                int node = *jt;
                if ((initialEdge->source == edge->source && node == edge->destination) || (initialEdge->source == edge->destination && node == edge->source)) {
                    edge1 = edge;
                    break;
                }
            }
            
            for (std::vector<Edge *>::iterator kt=edges.begin(); kt != edges.end(); ++kt) {
                Edge *edge = *kt;
                Edge *initialEdge = *it;
                int node = *jt;
                if ((initialEdge->destination == edge->source && node == edge->destination) || (initialEdge->destination == edge->destination && node == edge->source)) {
                    edge2 = edge;
                    break;
                }
            }
            
            if (edge1 != nullptr && edge2 != nullptr) {
                edgesPartOfTriangle.insert(edge1);
                edgesPartOfTriangle.insert(edge2);
                Edge *edge3 = *it;
                edgesPartOfTriangle.insert(edge3);
            }
            
        }
    }
    
    return edges.size() - edgesPartOfTriangle.size();
}
