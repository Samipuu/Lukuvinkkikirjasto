#!/bin/bash

if ! [[ $(pwd) == *ReadingTips ]]
then
    echo "this script should be ran from the main directory. e.g.: bash src/test/java/system/test_play_exit_and_continue.sh"
    exit 1
fi

media_path="downloaded_media_files/files"

#media_file="Indochine - Un été français (Clip officiel).mp4"

media_file='Joe Rogan Experience #1531 - Miley Cyrus.mp4'

seconds=$(bash command/get_media_length.sh "$media_path/$media_file")

halftime=$(( $seconds / 2 ))

sekunnit=$halftime

sekunnit=$(bash command/play_video_with_mpv.sh "$media_path"/"$media_file" $sekunnit)

echo "jaahas. jatketaanko?" && read -n1

sekunnit=$(bash command/play_video_with_mpv.sh "$media_path"/"$media_file" $sekunnit)

echo "nonni. mitäs nyt?" && read -n1

sekunnit=$(bash command/play_video_with_mpv.sh "$media_path"/"$media_file" $sekunnit)
