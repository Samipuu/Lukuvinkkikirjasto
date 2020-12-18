#!/bin/bash

videofile=$1

seconds="$2"
if ! [[ -z "$seconds" ]];
then
    optional_seekposition="-ss $seconds"
fi

faketty_linux_gnu () {
    script -qefc "$(printf "%q " "$@")"
    rm -f typescript
}

faketty_macos () {
    unbuffer -p "$@"  
}

if [[ "$OS" == "Windows_NT" ]]; then
    # Windows 10 with Msys2
    soita="/mingw64/bin/mpv"    # Msys2: pacman -S mingw64/mingw-w64-x86_64-mpv
    faketty () { faketty_linux_gnu "$@"; }
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Ubuntu 20.04 and such
    soita='/usr/bin/mpv'     # Ubuntu 20.04: apt install mpv
    faketty () { faketty_linux_gnu "$@"; }
elif [[ "$OSTYPE" == "darwin"* ]]; then
    # macOs # brew install mpv && brew install expect
    soita='mpv'
    faketty () { faketty_macos "$@"; }
fi

# take row with positon information and parse seconds from it
rivi=$(faketty $soita --loop-file=inf -osdlevel 3 -fs $optional_seekposition "$videofile" |  tr [:cntrl:] '\n' | grep AV: | tail -n1 )

rivi=${rivi/*AV: /}   # remove shit from beginning
rivi=${rivi/ \/*/}       # remove shit from the end

seconds=$(echo $rivi | awk -F":" '{ print ($1 * 3600) + ($2 * 60) + $3 }') # calculate seconds from xx:yy:zz

echo $seconds
