#!/bin/bash

videofile=$1

seconds="$2"
if ! [[ -z "$seconds" ]];
then
    optional_seekposition="-ss $seconds"
fi

if [[ "$OS" == "Windows_NT" ]]; then
    # Windows 10 with Msys2
    soita="/mingw64/bin/mpv"    # Msys2: pacman -S mingw64/mingw-w64-x86_64-mpv
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Ubuntu 20.04 and such
    soita='/usr/bin/mpv'     # Ubuntu 20.04: apt install mpv
elif [[ "$OSTYPE" == "darwin"* ]]; then
    echo "jyy"
fi

# toimii
# bash command/play_video_with_mpv.sh 'downloaded_media_files/videos/Indochine - Nos célébrations (Clip officiel).mp4'
# bash command/play_video_with_mpv.sh 'downloaded_media_files/videos/Joe Rogan Experience #1531 - Miley Cyrus.mp4'
# bash command/play_video_with_mpv.sh 'downloaded_media_files/videos/Indochine - Un été français (Clip officiel).mp4' 165

faketty () {
    script -qefc "$(printf "%q " "$@")"
}

# take row with positon information and parse seconds from it
rivi=$(faketty $soita -osdlevel 3 -fs $optional_seekposition "$videofile" |  tr [:cntrl:] '\n' | grep AV: | tail -n1 )

# echo "raakarivi: $rivi"

rivi=${rivi/*AV: /}   # remove shit from beginning
rivi=${rivi/ \/*/}       # remove shit from the end

# echo "rivi: $rivi"

seconds=$(echo $rivi | awk -F":" '{ print ($1 * 3600) + ($2 * 60) + $3 }') # calculate seconds from xx:yy:zz

echo $seconds
