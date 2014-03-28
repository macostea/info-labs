//
//  main.cpp
//  Client
//
//  Created by Mihai Costea on 13/03/14.
//  Copyright (c) 2014 info. All rights reserved.
//

#include <iostream>

#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <vector>

int _client;

void timeOut(int signal) {
    printf("Time out!\n");
    close(_client);
    
    exit(1);
}

int main(int argc, const char * argv[])
{
    struct sockaddr_in server;
    _client = socket(AF_INET, SOCK_STREAM, 0);
    if (_client < 0) {
        perror("Error creating socket");
        exit(errno);
    }
    
    memset(&server, 0, sizeof(struct sockaddr_in));
    server.sin_family = AF_INET;
    server.sin_port = htons(5555);
    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    
    std::vector<int32_t> string1, string2;
    uint32_t count1, count2;
    
    std::cout << "count1:";
    std::cin >> count1;
    std::cout << "count2:";
    std::cin >> count2;
    
    for (int it=0; it<count1; it++) {
        int32_t no;
        std::cin >> no;
        string1.push_back(no);
    }
    
    for (int it=0; it<count2; it++) {
        int32_t no;
        std::cin >> no;
        string2.push_back(no);
    }
    
    if (connect(_client, (struct sockaddr *)&server, sizeof(struct sockaddr_in)) < 0) {
        perror("Error connecting to server");
        exit(errno);
    }
    
    send(_client, &count1, sizeof(uint32_t), 0);
    sleep(11);
    send(_client, &count2, sizeof(uint32_t), 0);
    
    for (int it=0; it<count1; it++) {
        int32_t no = string1[it];
        send(_client, &no, sizeof(int32_t), 0);
    }
    
    for (int it=0; it<count2; it++) {
        int32_t no = string2[it];
        send(_client, &no, sizeof(int32_t), 0);
    }
    
    int32_t count;
    std::vector<int32_t> common;
    signal(SIGALRM, timeOut);
    alarm(10);
    recv(_client, &count, sizeof(int32_t), 0);
    
    if (count == -1) {
        std::cout << "Server returned an error code " << count << std::endl;
        return -1;
    } else {
        for (int it=0; it<count; it++) {
            int32_t no = 0;
            alarm(10);
            recv(_client, &no, sizeof(int32_t), 0);
            common.push_back(no);
        }
        
        for (std::vector<int32_t>::iterator it=common.begin(); it!=common.end(); ++it) {
            std::cout << *it << std::endl;
        }
    }
    
    return 0;
}

