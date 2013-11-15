#define KEY 11390
#define EXIT_STRING "0"

#define REQ 1
#define RES 2

struct m_msg{
    long msgType;
    char filename[20];
    int count[26];
};
