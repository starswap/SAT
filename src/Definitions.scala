package SAT

case class Literal(not: Boolean, a: Atom) {
    def unary_! = Literal(!not, a)
}

type Atom = String
type CNF = List[List[Literal]]
type Assignments = List[Literal]
