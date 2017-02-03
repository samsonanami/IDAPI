#!/bin/bash
cd ../../
rm -rf release
rm -rf installer
rm -rf hermese.tar

mkdir -vp release/lib
mkdir -vp release/config

cp -rf HermeseAgent/build/libs/* release/

cp -rf HermeseAgent/build/output/lib/* release/lib
cp -rf HermeseAgent/build/output/config/* release/config
cp -rf Deployment/Hermese/Hermese.sh release/

cd release

tar cf ../hermese.tar ./*

cd ..

mkdir installer
cp -rf Deployment/build installer
cp hermese.tar installer/build/payload
cd installer/build
./packager.sh
cd ../../