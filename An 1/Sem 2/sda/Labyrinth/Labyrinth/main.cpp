//
//  main.cpp
//  Labyrinth
//
//  Created by Mihai Costea on 26/5/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <iostream>
#include "PQDV.h"
#include "BinarySearchTree.h"
#include "UI.h"

using namespace std;

int main(int argc, const char * argv[])
{
    Repository *repo = new Repository();
    Controller *controller = new Controller(repo);
    UI *ui = new UI(controller);

    
    ui->run();
    
    delete ui;
    delete controller;
    delete repo;
    
    
//    Node<int> *root = new Node<int>(10000, 0, NULL, NULL);
//    BinarySearchTree<int> *tree = new BinarySearchTree<int>(root);
//    
//    tree->insert(1, 1);
//    tree->insert(2, 2);
//    
//    cout << tree->find(1, root)->getData() << endl;
//    tree->remove(1);
//    
//    cout << tree->getRoot()->getData() << endl;
//    //cout << tree->getRoot()->getLeft()->getData() << endl;
//    cout << tree->getRoot()->getRight()->getData() << endl;
//    cout << tree->getRoot()->getRight()->getRight()->getData() << endl;
    
   // delete tree;
    
    return 0;
}

