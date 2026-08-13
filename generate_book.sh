#!/bin/bash

pandoc \
    mdNotes/introduction/programming_paradigms_introduction.md \
    mdNotes/imperative_programming/imperative_programming.md \
    mdNotes/functional_programming/functional_formalism.md \
    mdNotes/functional_programming/functional_programming_basics.md \
    mdNotes/functional_programming/stateless_paradigm.md \
    mdNotes/functional_programming/recursion.md \
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
    mdNotes/object_oriented_programming/uml_for_class_diagrams.md \
    mdNotes/design_patterns/design_patterns_introduction.md \
    mdNotes/design_patterns/creational_patterns.md \
    mdNotes/design_patterns/structural_patterns.md \
    mdNotes/design_patterns/behavioral_patterns.md \
    mdNotes/extra_stuff/haskell_introduction.md \
    mdNotes/extra_stuff/recursion_and_fixed_point_combinators.md \
    mdNotes/extra_stuff/prolog_introduction.md \
    mdNotes/extra_stuff/resolution.md \
    mdNotes/extra_stuff/kotlin_introduction.md \
    mdNotes/extra_stuff/OOP_kotlin.md \
    mdNotes/extra_stuff/exceptions_kotlin.md \
    -o book.pdf \
    -V monofont="JetBrainsMonoNL NF" \
    -V papersize=a5 \
    --citeproc \
    --resource-path=".:resources:mdNotes/uml:mdNotes/copyright_free_drawings:mdNotes/mermaid_diagrams:" \
    --bibliography="references.bib" \
    --template=template.typ \
    --pdf-engine=typst \
