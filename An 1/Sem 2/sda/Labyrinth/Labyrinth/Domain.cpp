//
//  Domain.cpp
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "Domain.h"


vector<Position> domain::getShortestPath(matrix labyrinth){
    vector<Position> result;
//    PriorityQueue<Position*> *q = new PQDV<Position*>;
    PriorityQueue<Position*> *q = new PQBST<Position*>;
    Position *start, *goal;
    
    for (int i=0; i<labyrinth.size(); i++){
        for (int j=0; j<labyrinth[0].size(); j++){
            if (labyrinth[i][j].type == 'S'){
                start = &labyrinth[i][j];
            }
            if (labyrinth[i][j].type == 'G'){
                goal = &labyrinth[i][j];
            }
        }
    }
    
    start->previous = NULL;
    q->insertElementWithPriority(start, 1);
    int priority = 1;
    
    while (!q->isEmpty()){
        Position *top = q->popMin();
        top->visited = true;
        
        if (top->x == goal->x && top->y == goal->y){
            Position *pos = goal;
            while (pos != NULL){
                result.push_back(*pos);
                pos = pos->previous;
            }
            
            return result;
        }
        
        for (int yDelta=-1; yDelta<=1; yDelta++){
            for (int xDelta=-1; xDelta<=1; xDelta++){
                if ((xDelta == 1 && yDelta == 1) || (xDelta == 1 && yDelta == -1) || (xDelta == -1 && yDelta == 1) || (xDelta == -1 && yDelta == -1) || (xDelta == 0 && yDelta == 0)) continue;
                int newx = top->x + xDelta;
                int newy = top->y + yDelta;

                if (newx >= labyrinth[0].size() || newx < 0 || newy >= labyrinth.size() || newy < 0) continue;
                if (labyrinth[newy][newx].type == '1') continue;
                
                if (!labyrinth[newy][newx].visited){
                    q->insertElementWithPriority(&labyrinth[newy][newx], ++priority);
                    labyrinth[newy][newx].previous = top;
                }
                
                
            }
        }
        
        
        
    }

    delete q;
    return result;
    
}