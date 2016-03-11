ff: ff.o
	ld -m elf_i386 -o ff ff.o /lib/libdl.so.2 --dynamic-linker /lib/ld-linux.so.2 -s

ff.o: ff.asm fflin.asm fflinio.asm ff.boot fflin.boot
	fasm fflin.asm ff.o

clean:
	rm -f ff ff.o

.PHONY: clean
