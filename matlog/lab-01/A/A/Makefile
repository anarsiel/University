SOURCE=Main.hs

.PHONY: all run

all: synt
run: synt
	./synt
synt: $(SOURCE)
	ghc Main.hs -o synt