#!/bin/bash

url="$1"


if [[ "$OSTYPE" == "msys" ]]; then
    # Windows 10 with Msys2
    avaaja=explorer
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    # Ubuntu 20.04 and such
    avaaja=firefox
elif [[ "$OSTYPE" == "darwin"* ]]; then
    echo "jyy. osx."
    avaaja='open' # ??
fi

$avaaja "$url"
