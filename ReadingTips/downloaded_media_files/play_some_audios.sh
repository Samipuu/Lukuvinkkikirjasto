#!/bin/bash

if [[ "$OS" == "Windows_NT" ]]; then
    # Windows 10 with Msys2
    soita="/mingw64/bin/mpv"    # Msys2: pacman -S mingw64/mingw-w64-x86_64-mpv
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Ubuntu 20.04 and such
    soita='/usr/bin/mpv'     # Ubuntu 20.04: apt install mpv
elif [[ "$OSTYPE" == "darwin"* ]]; then
    echo "jyy"
fi

echo 'audio files: '
ls -1 files/*.{webm,m4a}

$soita files/*.{webm,m4a}
