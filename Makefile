BINARY=amt

VERSION=`git describe --tags || echo 'UNKNOWN'`
BUILD=`date +%FT%T%z`

LDFLAGS=-ldflags "-w -s -X constants.Version=${VERSION} -X constants.Build=${BUILD}"

build:
	go build ${LDFLAGS} -o ${BINARY}

.PHONY: clean install