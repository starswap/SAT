package SAT
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers._

class DPLLTest extends AnyFlatSpec {
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
}
