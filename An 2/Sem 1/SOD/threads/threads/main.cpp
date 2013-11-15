//
//  main.cpp
//  threads
//
//  Created by Mihai Costea on 28/10/13.
//  Copyright (c) 2013 Mihai Costea. All rights reserved.
//

#include <iostream>
#include <pthread.h>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;

pthread_t myThreads[100];
pthread_mutex_t threadsMutex;

typedef struct{
    int     i;
    string  arg;
} Args;

string reverse(string const& line) {
    stringstream inStream(line);
    stringstream outStream;
    
    vector<string> words;
    copy(istream_iterator<string>(inStream), istream_iterator<string>(), back_inserter(words));
    copy(words.rbegin(), words.rend(), ostream_iterator<string>(outStream, " "));
    
    return outStream.str();
}


void *threadFunction(void *args) {
    Args *arguments = (Args *)args;
    
    ifstream file;
    file.open(arguments->arg);
    
    if (pthread_mutex_lock(&threadsMutex) != 0) exit(-1);
    while (file.is_open()) {
        string line;
        while (getline(file, line)) {
            if (arguments->i % 2 == 0) {
                cout << line << endl;
            } else {
                cout << reverse(line) << endl;
            }
        }
        file.close();
    }
    
    if (pthread_mutex_unlock(&threadsMutex) != 0) exit(-1);
    pthread_exit(nullptr);
    return nullptr;
}

int main(int argc, const char * argv[])
{
    pthread_mutex_init(&threadsMutex, nullptr);
    for (int it=1; it<argc; it++) {
        Args *args = new Args;
        args->i = it;
        args->arg = string(argv[it]);
        
        pthread_create(&myThreads[it-1], nullptr, threadFunction, args);
    }
    
    for (int it=1; it<argc; it++) {
        pthread_join(myThreads[it-1], nullptr);
    }
    
    pthread_mutex_destroy(&threadsMutex);
    
    return 0;
}

