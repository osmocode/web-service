#!/bin/bash -e

# Authorize the execution of this script from anywhere
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$DIR/.."

# COLORS
Reset=$'\e[0m' # Text Reset
Red=$'\e[0;31m'    # Red
Green=$'\e[0;32m'  # Green
Yellow=$'\e[0;33m' # Yellow
Purple=$'\e[0;35m' # Purple
Cyan=$'\e[0;36m'   # Cyan

echo -e "$Purple\nChecking dependencies...\n$Reset"

# Check if java is installed
if ! hash java 2> /dev/null; then
    echo "java:$Red ERROR - Java must be installed (see: https://www.java.com/fr/download/help/download_options.html#mac).$Reset" >&2
    exit 1
fi
echo "java:$Green OK !$Reset"

# Check if maven is installed
if ! hash mvn 2> /dev/null; then
    echo "maven:$Red ERROR - Maven must be installed (see: https://maven.apache.org/download.cgi).$Reset" >&2
    exit 1
fi
echo "maven:$Green OK !$Reset"

# Check if docker is installed
if ! hash docker 2> /dev/null; then
    echo "docker:$Red ERROR - Docker must be installed (see: https://docs.docker.com/engine/install/).$Reset" >&2
    exit 1
fi
echo "docker:$Green OK !$Reset"

echo -e "$Purple\nBuilding java modules...\n$Reset"

mvn package
if [ $? -ne 0 ]; then
    echo -e "\njava-modules:$Red ERROR - Build failed please contact admin system...$Reset\n" >&2
    exit 1
fi
echo -e "\njava-modules:$Green SUCCED !$Reset"

echo -e "$Purple\nBuilding docker images...\n$Reset"

./bin/docker-build.sh
if [ $? -ne 0 ]; then
    echo -e "\ndocker-build:$Red ERROR - Build failed please contact admin system...$Reset\n" >&2
    exit 1
fi
echo -e "\ndocker-build:$Green SUCCED !$Reset\n"
