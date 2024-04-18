package SAT

implicit class StringToLiteral(a: Atom) {
    def unary_! = Literal(false, a)
    def unary_+ = Literal(true, a)
}

val L = List