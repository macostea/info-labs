//
//  UI.cpp
//  Labyrinth
//
//  Created by Mihai Costea on 30/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include "UI.h"

using namespace std;


UI::UI(Controller* controller){
    this->controller = controller;
}

char UI::menu(){
    char selection;
    cout << "(1)Define the labyrinth" << endl;
    cout << "(2)Get the shortest path for the selected labyrinth" << endl;
    cout << "-----------------------" << endl;
    cout << "(x)Exit" << endl;
    cin >> selection;
    return selection;
}

void UI::defineLabyrinth(){
    int h,w;
    cout << "Labyrinth vertical size:";
    cin >> h;
    cout << "Labyrinth horizontal size:";
    cin >> w;
    matrix labyrinth(h, vector<Position>(w));
    cout << "Each element will be a single character like so: 0 (open), 1 (closed), S (capital S - source), G (capital G - goal)." << endl;
    cout << "The labyrinth is defined from the top left corner (i.e. (0,0) is the top left corner)." << endl;
    for (int i=0; i<h; i++){
        for (int j=0; j<w; j++){
            cout << "(" << i << ", " << j << "):";
            char type;
            cin >> type;
            labyrinth[i][j] = Position(j, i, type, false);
        }
    }
    
    this->controller->storeLabyrinth(labyrinth);
    this->prettyPrintLabyrinth();
}

void UI::getShortestPath(){
    this->prettyPrintLabyrinth();

    vector<Position> result = this->controller->getShortestPath();
    if (result.size() == 0){
        cout << "No path found!" << endl;
        return;
    }
    for (int i=(int)result.size()-1; i>=0; i--){
        cout << "(" << result[i].y << ", " << result[i].x << ")";
        if (i != 0){
            cout << " -> ";
        } else {
            cout << endl;
        }

    }
}

void UI::prettyPrintLabyrinth(){
    matrix labyrinth = this->controller->getLabyrinth();
    for (int i=0; i<labyrinth.size(); i++){
        cout << "| ";
        for (int j=0; j<labyrinth[0].size(); j++){
            cout << labyrinth[i][j].type  << "," << "(" << labyrinth[i][j].y << "," << labyrinth[i][j].x << ")" << " | ";
        }
        cout << endl;
    }
}

void UI::run(){
    cout << "Welcome!" << endl;
    char selection = '0';
    while (selection != 'x'){
        selection = this->menu();
        switch (selection) {
            case '1':
                this->defineLabyrinth();
                break;
                
            case '2':
                this->getShortestPath();
                break;
                
            default:
                break;
        }
    }
}