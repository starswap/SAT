package SAT

object DPLL extends SATSolver {
    def solve(f: CNF): Option[Assignments] = ???

    def bcp(f: CNF, a: Assignments): (CNF, Assignments) =
        f.filter(_.length == 1).headOption match {
            case None                    => (f, a)
            case Some(literal :: List()) => {
                val f_ = f.filterNot(clause => clause.contains(literal))
                          .map(clause => clause.filterNot(_ == !literal))
                bcp(f_, literal :: a)
            }
        }

    def plp(f: CNF, a: Assignments): (CNF, Assignments) = {
        val pure_literals = f.flatten.sortBy(literal => literal.a)
                             .groupBy(literal => literal.a)
                             .filter((_, occurs) => occurs.forall(_.polarity) || occurs.forall(!_.polarity))
                             .map((_, occurs) => occurs.head).toList
        (f.filter(clause => clause.intersect(pure_literals).isEmpty), a ++ pure_literals)
    }

    def dpll(f: CNF, a: Assignments): Option[Assignments] = {
        // bcp(F)
        // val (a_, f_) = plp()

        None 
    }
}