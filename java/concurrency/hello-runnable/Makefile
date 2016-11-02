NAME=threads
SRCDIR=src/main/java/com/benjaminran/practice/concurrency
SRC=${SRCDIR}/HelloThread.java ${SRCDIR}/HelloRunnable.java

build/${NAME}.jar:
	- mkdir build
	javac -d build `find . -name '*.java' | xargs printf "%s "` && jar -cmf src/main/resources/manifest.txt build/${NAME}.jar -C build com

clean:
	- rm -r build
