#!/bin/sh

clear
javac -cp ./jars/javax.json-1.1.4.jar: Main.java
java -cp ./jars/javax.json-1.1.4.jar: Main
rm -rf *.class