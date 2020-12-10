#!/bin/bash

url="$1"

if [[ "$OS" == "Windows_NT" ]]; then
    # Windows 10 with Msys2
    avaaja=explorer
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Ubuntu 20.04 and such
    avaaja=firefox
elif [[ "$OSTYPE" == "darwin"* ]]; then
    # macOs
    avaaja='open' # (?not tested..)
else
    echo "jaajaa: $OSTYPE"
fi

$avaaja "$url"
