//
//  Graph.h
//  Graphs
//
//  Created by Mihai Costea on 6/10/13.
//
//

#ifndef __Graphs__Graph__
#define __Graphs__Graph__

#include <iostream>
#include <set>

#include "GraphVertex.h"
#include "GraphEdge.h"

using namespace std;

typedef enum {
    kInboundEdge    = 1,
    kOutboundEdge   = 1 << 1
} EdgeDirection;

template <typename T>
class Graph {
private:
    set<GraphVertex<T> *>                   vertices;
    size_t                                  getDegree(T vertex,EdgeDirection direction);
    GraphVertex<T>                      	*vertex(T data);
    
public: // methods
    ~Graph();
    /*
     *  Get the number of vertices from the graph
     */
    size_t                                  numberOfVertices();
    
    /*
     *  Get the number of edges from the graph
     */
    size_t                                  numberOfEdges();
    
    /*
     *  Add a vertex to the graph
     */
    void                                    addVertex(T data);
    
    /*
     *  Add an edge to the graph
     */
    void                                    addEdge(T source, T destination, int cost, EdgeDirection direction);
    
    /*
     *  Return a pointer to the edge between two given vertices
     *
     *  Returns nullptr if no such edge exists
     */
    GraphEdge<T>*                           getEdgeBetweenVertices(T source, T destination);
    
    /*
     *  Returns the in degree of a given vertex
     *  
     *  Returns -1 if vertex does not exist
     */
    size_t                                  getInDegree(T vertex);
    
    /*
     *  Returns the out degree of a given vertex
     *
     *  Returns -1 if vertex does not exist
     */
    size_t                                  getOutDegree(T vertex);
    
    /*
     *  Returns the begin() iterator of the in or out edge set of a given vertex
     *
     *  Returns an empty set iterator if the vertex does not exist
     */
    typename set<GraphEdge<T> *>::iterator  beginEdgeIterator(T vertex, EdgeDirection direction);
    
    /*
     *  Returns the end() iterator of the in or out edge set of a given vertex
     *
     *  Returns an empty set iterator if the vertex does not exist
     */
    typename set<GraphEdge<T> *>::iterator  endEdgeIterator(T vertex, EdgeDirection direction);
};

#pragma mark - Implementation

template <typename T>
Graph<T>::~Graph() {
}

template <typename T>
size_t Graph<T>::numberOfVertices() {
    return this->vertices.size();
}

template <typename T>
size_t Graph<T>::numberOfEdges() {
    size_t totalNumberOfEdges = 0;
    for (typename set<GraphVertex<T> *>::iterator it = this->vertices.begin(); it != this->vertices.end(); ++it){
        GraphVertex<T> *vertex = *it;
        totalNumberOfEdges += vertex->inEdges.size();
        totalNumberOfEdges += vertex->outEdges.size();
    }
    
    return totalNumberOfEdges;
}

template <typename T>
void Graph<T>::addVertex(T data) {
    GraphVertex<T> *vertex = new GraphVertex<T>(data);
    this->vertices.insert(vertex);
}

template <typename T>
void Graph<T>::addEdge(T source, T destination, int cost, EdgeDirection direction){
    GraphVertex<T> *sourceVertex = this->vertex(source);
    GraphVertex<T> *destinationVertex = this->vertex(destination);
    
    GraphEdge<T> *edge = new GraphEdge<T>(destinationVertex, cost);
    if (direction == kInboundEdge) {
        sourceVertex->inEdges.insert(edge);
    } else {
        sourceVertex->outEdges.insert(edge);
    }
}

template <typename T>
GraphEdge<T>* Graph<T>::getEdgeBetweenVertices(T source, T destination) {
    GraphEdge<T> *graphEdge = nullptr;
    GraphVertex<T> *sourceVertex = this->vertex(source);
    
    if (sourceVertex != nullptr) {
        for (typename set<GraphEdge<T> *>::iterator it = sourceVertex->outEdges.begin(); it != sourceVertex->outEdges.end(); ++it) {
            GraphEdge<T> *edge = *it;
            if (edge->destination->data == destination) {
                graphEdge = edge;
            }
        }
    }
    
    return graphEdge;
}

template <typename T>
size_t Graph<T>::getInDegree(T vertex) {
    return this->getDegree(vertex, kInboundEdge);
}

template <typename T>
size_t Graph<T>::getOutDegree(T vertex) {
    return this->getDegree(vertex, kOutboundEdge);
}

template <typename T>
typename set<GraphEdge<T> *>::iterator Graph<T>::beginEdgeIterator(T vertex, EdgeDirection direction) {
    GraphVertex<T> *graphVertex = this->vertex(vertex);
    if (graphVertex != nullptr) {
        if (direction == kInboundEdge) {
            return graphVertex->inEdges.begin();
        } else {
            return graphVertex->outEdges.begin();
        }
    }
    return typename set<GraphEdge<T> *>::iterator();
}

template <typename T>
typename set<GraphEdge<T> *>::iterator Graph<T>::endEdgeIterator(T vertex, EdgeDirection direction) {
    GraphVertex<T> *graphVertex = this->vertex(vertex);
    if (graphVertex != nullptr) {
        if (direction == kInboundEdge) {
            return graphVertex->inEdges.end();
        } else {
            return graphVertex->outEdges.end();
        }
    }
    return typename set<GraphEdge<T> *>::iterator();
}

template <typename T>
size_t Graph<T>::getDegree(T vertex, EdgeDirection direction) {
    GraphVertex<T> *graphVertex = this->vertex(vertex);
    if (graphVertex != nullptr) {
        return direction == kInboundEdge ? graphVertex->inEdges.size() : graphVertex->outEdges.size();
    }
    
    return -1;
}

template <typename T>
GraphVertex<T>* Graph<T>::vertex(T data) {
    GraphVertex<T> *result = nullptr;
    for (typename set<GraphVertex<T> *>::iterator it = this->vertices.begin(); it != this->vertices.end(); ++it) {
        GraphVertex<T> *graphVertex = *it;
        if (graphVertex->data == data) {
            result = graphVertex;
            break;
        }
    }
    return result;
}

#endif /* defined(__Graphs__Graph__) */
