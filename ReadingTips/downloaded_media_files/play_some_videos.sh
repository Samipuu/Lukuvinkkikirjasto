#!/bin/bash

if [[ "$OS" == "Windows_NT" ]]; then
    # Windows 10 with Msys2
    soita="/mingw64/bin/mpv"    # Msys2: pacman -S mingw64/mingw-w64-x86_64-mpv
else
    # Ubuntu 20.04 and such
    soita='/usr/bin/mplayer'     # Ubuntu 20.04: apt install mplayer
fi

echo 'video files: videos/'
ls -1 videos/

$soita -fs videos/*.*
