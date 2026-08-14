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

$body$
