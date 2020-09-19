JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
CP = -cp .:../../../Resources/lib/\*
DIR = -d ../out/
file =


.java.class:
	$(JC) $(JFLAGS) $(CP) $(DIR) $*.java

CLASSES = \
	$(file).java
	
default: classes
	
classes: $(CLASSES:.java=.class)
