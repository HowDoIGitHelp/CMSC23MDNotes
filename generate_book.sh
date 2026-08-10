#!/bin/bash

pandoc \
    mdNotes/Programming_Paradigms_Introduction.md \
    mdNotes/imperative_programming/imperative_programming.md \
    mdNotes/functional_programming/functional_formalism.md \
    mdNotes/functional_programming/functional_programming_basics.md \
    mdNotes/functional_programming/stateless_paradigm.md \
    mdNotes/functional_programming/advantages_and_disadvantages_fp.md \
    mdNotes/logic_programming/logic_formalism.md \
    mdNotes/logic_programming/logic_programming_basics.md \
    mdNotes/logic_programming/unification_sld_resolution.md \
    mdNotes/logic_programming/advanced_constructs.md \
    mdNotes/logic_programming/advantages_and_disadvantages_lp.md \
    mdNotes/object_oriented_programming/object_oriented_programming.md \
    mdNotes/object_oriented_programming/fundamental_concepts_of_oop.md \
    mdNotes/object_oriented_programming/SOLID_objects.md \
    mdNotes/object_oriented_programming/class_relationships.md \
    -o book.pdf \
    -V monofont="JetBrainsMonoNL NF" \
    --citeproc \
    --resource-path=".:resources:mdNotes/uml:mdNotes/copyright_free_drawings:" \
    --bibliography="references.bib" \
    --pdf-engine=typst \
