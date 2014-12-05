
object ApproximatedRefinementType {
  sealed trait Type
  sealed trait Arrow extends Type {
    val src: Type
    val tgt: Type
  }

  type Source[A2 <: Arrow] = ({ val arrow: A2; type S = arrow.src.type })#S
}



