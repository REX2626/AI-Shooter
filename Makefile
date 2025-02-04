all: build run

build:
	javac *.java -d .classes

run:
	java Main
