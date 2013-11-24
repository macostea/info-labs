//
//  GraphVertex.h
//  Graphs
//
//  Created by Mihai Costea on 6/10/13.
//
//

#ifndef __Graphs__GraphVertex__
#define __Graphs__GraphVertex__

#include <iostream>
#include <set>

template <typename T>
class GraphEdge;

template <typename T>
class GraphVertex {
public:
    /*
     * Constructor
     */
    GraphVertex(T data);
    ~GraphVertex();
    
    /*
     * Vertex data
     */
    T                                   data;
    
    /*
     * Set of inbound edges
     */
    std::set<GraphEdge<T> *>            inEdges;
    
    /*
     * Set of outbound edges
     */
    std::set<GraphEdge<T> *>            outEdges;
};

#pragma mark - Implementation

template <typename T>
GraphVertex<T>::GraphVertex(T data) {
    this->data = data;
}

#endif /* defined(__Graphs__GraphVertex__) */
