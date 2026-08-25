include mdSources.txt

book.pdf: $(mdSources) template.typ references.bib Makefile
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

clean:
	rm book.pdf
