# Define the Java compiler
JAVAC=javac

# Define the Java runtime
JAVA=java

# Define the source directory and files
SRC_DIR=src
SRC_FILES=$(SRC_DIR)/InstanceGenerator.java $(SRC_DIR)/BlossomAlgorithm.java

# Define the output directory for compiled classes
BIN_DIR=bin

# Default target to compile and run the Java programs
run: $(BIN_DIR)/InstanceGenerator.class $(BIN_DIR)/BlossomAlgorithm.class
	$(JAVA) -cp $(BIN_DIR) InstanceGenerator
	$(JAVA) -cp $(BIN_DIR) BlossomAlgorithm

# Target to compile the Java source files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(BIN_DIR) $<

# Rule to clean up compiled files
clean:
	rm -f $(BIN_DIR)/*.class graph.txt

.PHONY: run clean
