ROOT = .
SRC_DIR = $(ROOT)/source
SRC = $(wildcard $(SRC_DIR)/*.java)
OUT = $(ROOT)/build
MAIN = Solution

build:
	mkdir -p $(OUT)
	javac -Xlint:all -Werror -g -d $(OUT) -cp "$(ROOT):$(SRC_DIR)" $(SRC)

.PHONY: build

clean:
	rm -rf $(OUT)/*

.PHONY: clean

run: build
	java -cp "$(OUT):$(SRC_DIR)" $(MAIN)

.PHONY: run
