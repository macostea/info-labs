BEGIN {
    count = 0;


}

{
    if (FNR == 1){
        count++;
    }
    wordcount[count] += NF;
    filename[count] = FILENAME;
}

END{
    i=1;
    max = 0;
    position = 0;
    for (i=1; i <= count; i++){
        if (wordcount[i] > max) {
            max = wordcount[i];
            position = i;
        }
    }
    print max;
    print filename[position];
}
