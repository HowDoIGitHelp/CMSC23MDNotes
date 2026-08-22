#!/bin/bash

pandoc \
    mdNotes/introduction/programming_paradigms_introduction.md mdNotes/n.md \
    mdNotes/imperative_programming/imperative_programming.md mdNotes/n.md \
    mdNotes/functional_programming/functional_formalism.md mdNotes/n.md \
    mdNotes/functional_programming/functional_programming_basics.md mdNotes/n.md \
    mdNotes/functional_programming/stateless_paradigm.md mdNotes/n.md \
    mdNotes/functional_programming/recursion.md mdNotes/n.md \
    mdNotes/functional_programming/higher_order_functions_for_lists.md \
    mdNotes/functional_programming/advantages_and_disadvantages_fp.md mdNotes/n.md \
    mdNotes/logic_programming/logic_formalism.md mdNotes/n.md \
    mdNotes/logic_programming/logic_programming_basics.md mdNotes/n.md \
    mdNotes/logic_programming/unification_sld_resolution.md mdNotes/n.md \
    mdNotes/logic_programming/advanced_constructs.md mdNotes/n.md \
    mdNotes/logic_programming/advantages_and_disadvantages_lp.md mdNotes/n.md \
    mdNotes/object_oriented_programming/object_oriented_programming.md mdNotes/n.md \
    mdNotes/object_oriented_programming/fundamental_concepts_of_oop.md mdNotes/n.md \
    mdNotes/object_oriented_programming/SOLID_objects.md mdNotes/n.md \
    mdNotes/object_oriented_programming/class_relationships.md mdNotes/n.md \
    mdNotes/object_oriented_programming/uml_for_class_diagrams.md mdNotes/n.md \
    mdNotes/design_patterns/design_patterns_introduction.md mdNotes/n.md \
    mdNotes/design_patterns/creational_patterns.md mdNotes/n.md \
    mdNotes/design_patterns/structural_patterns.md mdNotes/n.md \
    mdNotes/design_patterns/behavioral_patterns.md mdNotes/n.md \
    mdNotes/extra_stuff/haskell_introduction.md mdNotes/n.md \
    mdNotes/extra_stuff/recursion_and_fixed_point_combinators.md mdNotes/n.md \
    mdNotes/extra_stuff/prolog_introduction.md mdNotes/n.md \
    mdNotes/extra_stuff/resolution.md mdNotes/n.md \
    mdNotes/extra_stuff/kotlin_introduction.md mdNotes/n.md \
    mdNotes/extra_stuff/OOP_kotlin.md mdNotes/n.md \
    mdNotes/extra_stuff/exceptions_kotlin.md mdNotes/n.md \
    mdNotes/references_header.md \
    -o book.pdf \
    -V monofont="JetBrainsMonoNL NF" \
    -V papersize=a5 \
    --citeproc \
    --resource-path=".:resources:mdNotes/uml:mdNotes/copyright_free_drawings:mdNotes/mermaid_diagrams:" \
    --bibliography="references.bib" \
    --template=template.typ \
    --pdf-engine=typst \
