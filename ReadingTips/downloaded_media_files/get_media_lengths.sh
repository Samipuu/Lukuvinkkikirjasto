#!/bin/bash

#mediainfo
# sudo snap install mediainfo

komento='mediainfo --Output="General;%Duration/String3%" '

tiedostot=audios/*.*
for tiedosto in $tiedostot
do
  echo "tiedosto: $tiedosto"
  $komento "$tiedosto"
done

tiedostot=videos/*.*
for tiedosto in $tiedostot
do
  echo "tiedosto: $tiedosto"
  $komento "$tiedosto"
done
