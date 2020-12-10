#!/bin/bash

kohde="testx_$(date +%s)"

if ! [[ -e downloaded_media_files/files ]]
then
	echo 'downloaded_media_files/files' ei olemassa.
	exit 1
fi

# kaikki komennot ketjussa, jatkuu voin jos edellinen onnistui.
# mediatiedostot pitää olla ladattuja jolloin mediatiedostot/files hakemisto on olemassa.
mkdir -p "$kohde" && \
	./gradlew distTar && \
	cp build/distributions/ReadingTips.tar "${kohde}/" && \
	cd $kohde && \
	tar -xvf ReadingTips.tar && \
	cd ReadingTips/downloaded_media_files/ && \
	ln -s ../../../downloaded_media_files/files files && \
	cd .. && \
	bin/ReadingTips examples

