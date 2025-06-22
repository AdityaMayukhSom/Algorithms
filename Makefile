.PHONY: build
build:
	javac -g -d ./build/ -cp ".:*" *.java

run:
	java -cp ./build/ Solution