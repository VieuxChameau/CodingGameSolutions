#!/usr/bin/env bash

while true; do
    # enemy1: name of enemy 1
    read enemy1
    # dist1: distance to enemy 1
    read dist1
    # enemy2: name of enemy 2
    read enemy2
    # dist2: distance to enemy 2
    read dist2

    if [ $dist2 -gt $dist1 ]; then
        echo "$enemy1"
    else
        echo "$enemy2"
    fi
done