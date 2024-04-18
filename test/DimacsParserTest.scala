package SAT
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class DimacsParserTest extends AnyFlatSpec {
    "DIMACS parser" should "parse a sample case correctly" in {
        val dimacsFmt = List("c", "c start with comments", "c", "c", "p cnf 5 3", "1 -5 4 0", "-1 5 3 4 0", "-3 -4 0")
        DimacsParser.parse(dimacsFmt) shouldBe L(L(+"1", !"5", +"4"), L(!"1", +"5", +"3", +"4"), L(!"3", !"4"))
    }

}