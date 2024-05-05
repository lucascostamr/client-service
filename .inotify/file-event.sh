#!/bin/sh

DIRECTORY_TO_MONITOR="./src/main"
FLAG=".inotify/flag.txt"

echo true > "$FLAG"
echo "Client rodou"
inotifywait -m -r -e close_write --format '%w%f %e' "$DIRECTORY_TO_MONITOR" | while read FILE EVENT; do
  STATUS=$(cat "$FLAG")
  if [ "$STATUS" = "true" ]; then
    mvn compile
    echo false > "$FLAG"
    (sleep 5 && echo true > "$FLAG") &
  fi
done
