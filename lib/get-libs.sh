#!/bin/sh

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

wget -N -P "$DIR" "http://magie.maweb.eu/vut/ant_doxygen.jar"
