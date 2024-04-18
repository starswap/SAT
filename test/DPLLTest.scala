package SAT
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class DPLLTest extends AnyFlatSpec {
    // Boolean Constraint Propagation Tests
    "BCP" should "infer proposition from unit clause" in {
        val (f, a) = DPLL.bcp(L(L(+"a")), L())
        a shouldBe L(+"a")
    }

    it should "remove unit clause from formula" in {
        val (f, a) = DPLL.bcp(L(L(+"a")), L())
        f shouldBe L()
    }

    it should "be able to process multiple unit clauses at a time" in {
        val (f, a) = DPLL.bcp(L(L(+"a"), L(!"b"), L(+"c")), L())
        a.toSet shouldBe L(+"a", !"b", +"c").toSet
    }
    
    it should "remove decided atoms from other clauses" in {
        val (f, a) = DPLL.bcp(L(L(+"a"), L(!"b"), L(!"a", +"b", +"c", +"d")), L())
        f shouldBe L(L(+"c", +"d"))
    }

    it should "remove other clauses containing the decided atom" in {
        val (f, a) = DPLL.bcp(L(L(+"a"), L(+"a", !"b", +"c", +"d"), L(+"a", !"b", +"c", +"d")), L())
        f shouldBe L()
    }

    // Pure Literal Propagation Tests
    val (f, a) = DPLL.plp(L(L(+"a", +"z", +"y"), L(!"a", +"z", !"y")), L())
    "PLP" should "remove all clauses containing a pure literal" in (f shouldBe L())
    it should "update assignments for pure literals" in (a shouldBe L(+"z"))

    it should "work when the pure literals are negative" in {
        val (f, a) = DPLL.plp(L(L(+"a", !"z", !"y"), L(!"a", +"z", !"y")), L())
        f shouldBe L()
        a shouldBe L(!"y")
    }
}
