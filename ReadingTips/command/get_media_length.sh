#!/bin/bash

#ffmpeg ubuntu 20.04
# sudo snap install ffmpeg 

## msys2
# pacman -S mingw64/mingw-w64-x86_64-ffmpeg

if [[ "$OS" == "Windows_NT" ]]; then
    # Windows 10 with Msys2
    fefe='/mingw64/bin/ffmpeg.exe'
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Ubuntu 20.04 and such
    fefe='ffmpeg'
elif [[ "$OSTYPE" == "darwin"* ]]; then
    # macOs (?not tested..)
    fefe='ffmpeg'
fi

mediafile="$1"

time_in_many_units=$($fefe -i "$mediafile" 2>&1 | grep Duration | cut -d ' ' -f 4 | sed s/,//)

time_in_many_units=${time_in_many_units/\./:} # replace last . with : to make things easier.

seconds=$(echo $time_in_many_units | awk -F: '{ print ($1 * 3600) + ($2 * 60) + $3 }') # calculate seconds from xx:yy:zz:öö (milliseconds dropped)

echo $seconds
