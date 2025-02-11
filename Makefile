all: build run

build:
	javac *.java -d classes

run:
	java -cp classes Main
