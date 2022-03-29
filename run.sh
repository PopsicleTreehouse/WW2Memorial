#!/bin/sh

clear
javac -cp ./Jars/javax.json-1.1.4.jar: Main.java
java -cp ./Jars/javax.json-1.1.4.jar: Main
rm -rf *.class