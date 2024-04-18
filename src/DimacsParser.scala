package SAT

object DimacsParser {
    def parse(lines: List[String]): CNF = {
        val (l :: ls) = lines.dropWhile(_.head == 'c')
        val (p :: cnf :: vars :: clauses) = l.split(" ").toList
        if (p != "p" || cnf != "cnf") throw new IllegalArgumentException("Expected file to start with p cnf")
        ls.map(parseLine)
    }

    private def parseLine(line: String) = line.split(" ").toList.dropRight(1).map(parseLiteral) // ignore the 0 at the end of the line
    private def parseLiteral(literal: String) = literal match {
        case s"-$name"  => Literal(false, name)
        case name       => Literal(true, name)
    }
}
