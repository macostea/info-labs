//
//  GraphEdge.h
//  Graphs
//
//  Created by Mihai Costea on 6/10/13.
//
//

#ifndef __Graphs__GraphEdge__
#define __Graphs__GraphEdge__

#include <iostream>

template <typename T>
class GraphVertex;

template <typename T>
class GraphEdge {
public:
    /*
     * Constructor
     */
    GraphEdge(GraphVertex<T> *destination, int cost);
    ~GraphEdge();
    
    /*
     * Destination vertex
     */
    GraphVertex<T>     *destination;
    
    /*
     * Edge cost
     */
    int             cost;
};

#pragma mark - Implementation

template <typename T>
GraphEdge<T>::GraphEdge(GraphVertex<T> *destination, int cost) {
    this->destination = destination;
    this->cost = cost;
}

#endif /* defined(__Graphs__GraphEdge__) */
