include mdSources.txt
include mdSourcesSlides.txt

book.pdf: $(mdSources) template.typ references.bib Makefile slides
	pandoc $(mdSources) \
		-o book.pdf \
		-V monofont="JetBrainsMonoNL NF" \
		-V papersize=a5 \
		--toc \
		--toc-depth=2 \
		--citeproc \
		--resource-path=".:resources:mdNotes/uml:mdNotes/copyright_free_drawings:mdNotes/mermaid_diagrams:" \
		--bibliography="references.bib" \
		--template="template.typ" \
		--pdf-engine=typst

slides:
	$(foreach file, $(mdSourcesSlides), make -C generatedSlides SOURCE=$(file) OUTPUT=$(notdir $(basename $(file))).html;)

clean:
	rm book.pdf
