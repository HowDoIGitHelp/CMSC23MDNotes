INPUTDIR ?= $(dir $(SOURCE))
OUTPUTDIR ?= $(dir $(OUTPUT))

$(OUTPUT): $(SOURCE) slidesPre.html slidesPost.html Makefile
	> $(OUTPUT)
	cat slidesPre.html >> $(OUTPUT)
	pandoc -t json $(SOURCE) | \
		md-slides -s $(INPUTDIR) -o $(OUTPUTDIR) -l 6 -w 60 | \
		remarkjs | \
		pandoc -f json \
		-t markdown-simple_tables-multiline_tables-grid_tables \
		>> $(OUTPUT)
	cat slidesPost.html >> $(OUTPUT)
