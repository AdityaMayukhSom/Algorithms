ROOT = .
MAIN_CLASS = Main
SRC_DIR = $(ROOT)/source
SRC = $(SRC_DIR)/$(MAIN_CLASS).java
OUT_DIR = $(ROOT)/build

.PHONY: clean
clean:
	rm -rf $(OUT_DIR)/*

.PHONY: ensure_out_dir
ensure_out_dir:
	mkdir -p $(OUT_DIR)

.PHONY: build
build: ensure_out_dir
	javac -deprecation -Xlint:all -Werror -g -d $(OUT_DIR) -cp "$(SRC_DIR)" $(SRC)

.PHONY: run
run: build
	java -cp "$(OUT_DIR)" $(MAIN_CLASS)

.DEFAULT_GOAL := run