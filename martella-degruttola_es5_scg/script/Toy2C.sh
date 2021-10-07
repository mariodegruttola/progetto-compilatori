#!/bin/bash
java -cp martella-degruttola_es5_scg.jar "MainTester" $1.toy
clang -pthread -lm -o $1 $1.c