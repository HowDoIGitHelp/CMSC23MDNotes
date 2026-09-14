INPUTDIR ?= $(dir $(SOURCE))
OUTPUTDIR ?= $(dir $(OUTPUT))

# $(OUTPUT): $(SOURCE) slidesPre.html slidesPost.html
# 	> $(OUTPUT)
# 	cat slidesPre.html >> $(OUTPUT)
# 	pandoc -t json $(SOURCE) | \
# 		md-slides | \
# 		remarkjs | \
# 		pandoc -f json \
# 		-t markdown-simple_tables-multiline_tables-grid_tables \
# 		>> $(OUTPUT)
# 	cat slidesPost.html >> $(OUTPUT)

$(OUTPUT): $(SOURCE) slidesPre.html slidesPost.html
	> $(OUTPUT)
	cat slidesPre.html >> $(OUTPUT)
	pandoc -t json $(SOURCE) | \
		md-slides $(INPUTDIR) $(OUTPUTDIR) | \
		remarkjs | \
		pandoc -f json \
		-t markdown-simple_tables-multiline_tables-grid_tables \
		>> $(OUTPUT)
	cat slidesPost.html >> $(OUTPUT)
