#!/bin/bash

# get youtube-dl
function lataa_youtube_dl() {
    mkdir bin/
    if [[ "$OS" == "Windows_NT" ]]; then
        # Windows 10 with Msys2
        echo "info: downloading Windows youtube-dl."
        curl -L https://yt-dl.org/latest/youtube-dl.exe -o bin/youtube-dl.exe
        lataa='bin/youtube-dl.exe'
    else
        # Ubuntu 20.04 and such
        echo "info: downloading Linux youtube-dl."
        curl -L https://yt-dl.org/downloads/latest/youtube-dl -o bin/youtube-dl
        chmod a+rx bin/youtube-dl
        lataa='bin/youtube-dl'
    fi
}
function lataile_videoita() {
    mkdir videos/
    while IFS= read -r line
    do
        echo "info: downloading: $line"
        #$lataa -F "$line"   # list available formats
        $lataa -f best -o videos/'%(title)s.%(ext)s' "$line" # the best (and probably only one with video and audio)
    done < "some_videos.txt"
}

function lataile_audioita() {
    mkdir audios/
    while IFS= read -r line
    do
        echo "info: downloading: $line"
        #$lataa -F "$line"   # list available formats
        $lataa -f bestaudio -o audios/'%(title)s.%(ext)s' "$line" # the best audio
    done < "some_audios.txt"
}

lataa_youtube_dl
lataile_videoita
lataile_audioita

