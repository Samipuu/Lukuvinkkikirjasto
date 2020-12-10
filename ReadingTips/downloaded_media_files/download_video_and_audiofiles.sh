#!/bin/bash

# get youtube-dl
function lataa_youtube_dl() {
    mkdir bin/
    if [[ "$OS" == "Windows_NT" ]]; then # elif [[ "$OSTYPE" == "msys" ]]; then
        # Windows 10 with Msys2
        echo "info: downloading Windows youtube-dl."
        curl -L https://yt-dl.org/latest/youtube-dl.exe -o bin/youtube-dl.exe
        lataa='bin/youtube-dl.exe'
    elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Ubuntu 20.04 and such
        echo "info: downloading Linux youtube-dl."
        curl -L https://yt-dl.org/downloads/latest/youtube-dl -o bin/youtube-dl
        chmod a+rx bin/youtube-dl
        lataa='bin/youtube-dl'
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS (?not tested..)
        curl -L https://yt-dl.org/downloads/latest/youtube-dl -o bin/youtube-dl
        sudo chmod a+rx bin/youtube-dl    
        lataa='bin/youtube-dl'
    fi
}

function lataile_videoita() {
    mkdir -p files
    while IFS= read -r line
    do
        echo "info: downloading: $line"
        #$lataa -F "$line"   # list available formats
        $lataa -f best -o files/'%(title)s.%(ext)s' "$line" # the best (and probably only one with video and audio)
    done < "some_videos.txt"
}

function lataile_audioita() {
    mkdir -p files
    while IFS= read -r line
    do
        echo "info: downloading: $line"
        #$lataa -F "$line"   # list available formats
        $lataa -f bestaudio -o files/'%(title)s.%(ext)s' "$line" # the best audio
    done < "some_audios.txt"
}

lataa_youtube_dl
lataile_videoita
lataile_audioita

