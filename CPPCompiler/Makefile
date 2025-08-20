# Compiler and flags
CXX = g++
CXXFLAGS = -std=c++17 -Wall -I./interface

# Executable name
TARGET = main.exe

# Object directory
OBJDIR = obj
OBJS = $(OBJDIR)/main.o $(OBJDIR)/lexer.o

# Build target
$(TARGET): $(OBJDIR) $(OBJS)
	$(CXX) $(OBJS) -o $(TARGET)

# Ensure obj directory exists (Windows-friendly)
$(OBJDIR):
	if not exist $(OBJDIR) mkdir $(OBJDIR)

# Compile main.cpp -> main.o
$(OBJDIR)/main.o: main.cpp interface/lexer.h
	$(CXX) $(CXXFLAGS) -c main.cpp -o $(OBJDIR)/main.o

# Compile lexer.cpp -> lexer.o
$(OBJDIR)/lexer.o: interface/lexer.cpp interface/lexer.h
	$(CXX) $(CXXFLAGS) -c interface/lexer.cpp -o $(OBJDIR)/lexer.o

# Clean build (Windows)
clean:
	del /Q $(OBJDIR)\*.o
	del /Q $(TARGET)
