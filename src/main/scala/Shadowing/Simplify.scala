package Shadowing

object Simplify extends App {



  /**
   * Dependent on Option[Boolean]
   */
  /*
                    ---------- OLD ----------

    maybePrePop match {
            case Some(prePopFlag) => Ok(view(incomeSources, prePopFlag))
            case None => Ok(view(incomeSources, isPrePopulated = false))
        }


                  ---------- SIMPLIFIED ----------

    Ok(view(incomeSources, maybePrePop.contains(true)))

                  ---------- Summary ----------
    Some(x) => x
    None    => false
   */


}
