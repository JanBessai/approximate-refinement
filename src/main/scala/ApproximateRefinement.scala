
object ApproximateRefinement {
  sealed trait Type
  sealed trait Arrow extends Type {
    val src: Type
    val tgt: Type
  }

  type Source[A2 <: Arrow] = ({ val arrow: A2; type S = arrow.src.type })#S

  // Testing it
  case class Const(id: Int) extends Type
  val sigma = Const(0)
  val tau = Const(1)
  val sigmaArrowTau = new Arrow {
    val src: sigma.type = sigma
    val tgt: tau.type = tau
  }
  def test(x: Arrow)(src: Source[x.type]): x.src.type = src
  val sigmaCopy: sigma.type = test(sigmaArrowTau)(sigmaArrowTau.src)
  val sigmaCopy2: sigma.type = test(sigmaArrowTau)(sigma)
  val sigmaCopy3: sigma.type = test(sigmaArrowTau)(sigmaCopy)
  val sigmaCopyInferred = test(sigmaArrowTau)(sigmaArrowTau.src)
  val correctTypeInferenceTest = test(sigmaArrowTau)(sigmaCopyInferred)
  val correctTypeInferenceTest2: sigma.type = test(sigmaArrowTau)(sigmaCopyInferred)
}



