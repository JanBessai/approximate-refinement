
object ApproximatedRefinementType {
  sealed trait Type
  sealed trait Arrow extends Type {
    val src: Type
    val tgt: Type
  }

  type Arrow2[S1 <: Type, S2 <: Type, T <: Type] = Arrow {
    val src: S1
    val tgt: Arrow { val src: S2; val tgt: T }
  }

  type Source[A2 <: Arrow2[_ <: Type, _ <: Type, _ <: Type]] =
    ({ val arrow: A2; type S = arrow.src.type })#S
}



