#!/usr/bin/env bash

STRENGTHS=()
read N
for (( i=0; i<N; i++ )); do
    read Pi
    STRENGTHS+=($Pi)
done

sorted_strengths=($(for strength in ${STRENGTHS[@]}; do echo $strength; done | sort -n))

prev=4294967295
minimumDifference=4294967295
currentDifference=0

for strength in "${sorted_strengths[@]}"
do
    if (( $strength < $prev )); then
        currentDifference=$((prev - strength))
    else
        currentDifference=$((strength - prev))
    fi

    if (( $currentDifference < $minimumDifference)); then
        minimumDifference=$currentDifference
    fi
    prev=$strength
done

echo $minimumDifference