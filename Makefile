ROOT = .
SRC_DIR = $(ROOT)/source
SRC = $(SRC_DIR)/Main.java
OUT_DIR = $(ROOT)/build
MAIN = Main

build:
	mkdir -p $(OUT_DIR)
	javac -deprecation -Xlint:all -Werror -g -d $(OUT_DIR) -cp "$(SRC_DIR)" $(SRC)

.PHONY: build

clean:
	rm -rf $(OUT_DIR)/*

.PHONY: clean

run: build
	java -cp "$(OUT_DIR)" $(MAIN)

diff: run
	diff ./data/output.txt ./data/correct.txt

.PHONY: diff
