package SAT


trait SATSolver {
    def solve(f: CNF): Option[Assignments]
}
