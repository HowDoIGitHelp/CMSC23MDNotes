SOURCE ?= "test.md"
OUTPUT ?= "slides.html"
INPUTDIR ?= "."
OUTPUTDIR ?= "."

convert-file:
	> $(OUTPUT)
	cat slidesPre.html >> $(OUTPUT)
	pandoc -t json $(SOURCE) | \
		md-slides | \
		remarkjs | \
		pandoc -f json \
		-t markdown-simple_tables-multiline_tables-grid_tables \
		>> $(OUTPUT)
	cat slidesPost.html >> $(OUTPUT)

convert-file-resolve:
	> $(OUTPUT)
	cat slidesPre.html >> $(OUTPUT)
	pandoc -t json $(SOURCE) | \
		md-slides $(INPUTDIR) $(OUTPUTDIR) | \
		remarkjs | \
		pandoc -f json \
		-t markdown-simple_tables-multiline_tables-grid_tables \
		>> $(OUTPUT)
	cat slidesPost.html >> $(OUTPUT)
