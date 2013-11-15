#!/bin/bash
#Write a shell that begining with a given folder displays a list of all
#names: files and folders from it and its subdirectories.
#For each text file from list, name prints the maximum number
#of identical lines from the that file, and the content of those line.
#For each folder names from the list, the number of contained files will be
#printed.
DIRNAME=$1
ls -AR $DIRNAME # print all the files and folders recursively
find $DIRNAME -name "*.txt" -print0 | while read -d $'\0' TEXTFILE
do
sort $TEXTFILE | uniq -c | sort -r | head -n 1 
done

find $DIRNAME -type d -print -exec sh -c 'ls '{}' | wc -l' \;
