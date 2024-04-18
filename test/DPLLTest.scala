package SAT
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class DPLLTest extends AnyFlatSpec {
    "BCP" should "infer proposition from unit clause" in {
        val (f, a) = DPLL.bcp(List(List(Literal(true, "a"))), List())
        a shouldBe List(Literal(true, "a"))
    }

    it should "remove unit clause from formula" in {
        val (f, a) = DPLL.bcp(List(List(Literal(true, "a"))), List())
        f shouldBe List()
    }

    it should "be able to process multiple unit clauses at a time" in {
        val (f, a) = DPLL.bcp(List(List(Literal(true, "a")), List(Literal(false, "b")), List(Literal(true, "c"))), List())
        a.toSet shouldBe List(Literal(true, "a"), Literal(false, "b"), Literal(true, "c")).toSet
    }
    
    it should "remove decided atoms from other clauses" in {
        val (f, a) = DPLL.bcp(List(List(Literal(true, "a")), List(Literal(false, "b")), List(Literal(false, "a"), Literal(true, "b"), Literal(true, "c"), Literal(true, "d"))), List())
        f shouldBe List(List(Literal(true, "c"), Literal(true, "d")))
    }

    it should "remove other clauses containing the decided atom" in {
        val (f, a) = DPLL.bcp(List(List(Literal(true, "a")), List(Literal(true, "a"), Literal(false, "b"), Literal(true, "c"), Literal(true, "d")), List(Literal(true, "a"), Literal(false, "b"), Literal(true, "c"), Literal(true, "d"))), List())
        f shouldBe List()
    }
}
