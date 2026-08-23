#set page(
  paper: "a5",
  margin: (
    top: 0.75in,
    bottom: 0.75in,
    inside: 0.8in,
    outside: 0.5in
  )
)

#set par(justify: false)

#show heading.where(level: 1): it => {
  pagebreak(weak: true, to: "odd")
  v(14em)
  set text(size: 1.7em)
  it
}


#show outline.entry.where(
  level: 1
): set block(above: 1.2em)

#show title: set text(size: 1.5em)
#show title: set align(center)

#v(14em)

#title[
  CMSC 23 \
  Programming Paradigms
]

#grid(
  columns: (1fr),
  align(center)[
    Rubelito Abella \
    University of the Philippines Cebu \
    #link("mailto:rrabella@up.edu.ph")
  ],
)

$if(toc)$
  #outline(
    title: [Table of Contents], 
    indent: 1.2em,
    $if(toc-depth)$ depth: $toc-depth$ $endif$
  )
  #v(2em) // Add spacing after TOC
$endif$

#set page(
  footer: context {
    let page_num = counter(page).display()
    let current_page = counter(page).get().first()
    if calc.odd(current_page) {
      align(right, page_num)
    } else {
      align(left, page_num)
    }
  }
)

#counter(page).update(1)
#set page(numbering: "1")

$body$
