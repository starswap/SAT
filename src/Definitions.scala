package SAT

case class Literal(polarity: Boolean, a: Atom) {
    def unary_! = Literal(!polarity, a)
    override def toString(): String = if polarity then s"+\"${a}\"" else s"!\"${a}\""
}

type Atom = String
type CNF = List[List[Literal]]
type Assignments = List[Literal]
