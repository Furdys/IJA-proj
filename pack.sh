#!/bin/sh
rm -r xfurda00
rm -r -f build/* doc/* dest-client/* dest-client/*

mkdir xfurda00
cp -r src xfurda00/src
cp -r examples xfurda00/examples
mkdir build xfurda00/build
mkdir xfurda00/doc
mkdir xfurda00/dest-client
mkdir xfurda00/dest-server
mkdir xfurda00/lib
cp lib/get-libs.sh xfurda00/lib/get-libs.sh
cp readme.txt xfurda00/readme.txt
cp rozdeleni.txt xfurda00/rozdeleni.txt
cp build.xml xfurda00/build.xml

zip -r xfurda00.zip xfurda00
rm -r xfurda00


	
