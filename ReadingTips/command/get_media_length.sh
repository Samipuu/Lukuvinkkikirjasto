#!/bin/bash

#ffmpeg ubuntu 20.04
# sudo snap install ffmpeg 

mediafile="$1"

time_in_many_units=$(ffmpeg -i "$mediafile" 2>&1 | grep Duration | cut -d ' ' -f 4 | sed s/,//)

time_in_many_units=${time_in_many_units/\./:} # replace last . with : to make things easier.

seconds=$(echo $time_in_many_units | awk -F: '{ print ($1 * 3600) + ($2 * 60) + $3 }') # calculate seconds from xx:yy:zz:öö (milliseconds dropped)

echo $seconds
