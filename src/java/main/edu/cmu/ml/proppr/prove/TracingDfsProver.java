package edu.cmu.ml.proppr.prove;

import edu.cmu.ml.proppr.prove.wam.LogicProgramException;
import edu.cmu.ml.proppr.prove.wam.ProofGraph;
import edu.cmu.ml.proppr.prove.wam.State;
import edu.cmu.ml.proppr.prove.wam.WamInterpreter;
import edu.cmu.ml.proppr.util.APROptions;
import edu.cmu.ml.proppr.util.Dictionary;

/**
 * A prover with scores based on simple depth-first-search, which
    additional prints out a detailed trace.
 * @author "William Cohen <wcohen@cs.cmu.edu>"
 * @author "Kathryn Mazaitis <krivard@cs.cmu.edu>"
 *
 */
public class TracingDfsProver extends DfsProver {
	public TracingDfsProver(APROptions apr) {
		super(apr);
	}
	@Override
	protected void beforeDfs(State state, ProofGraph pg, int depth) throws LogicProgramException {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<depth;i++) sb.append("| ");
		if (state.isCompleted()) {
			Dictionary.buildString(pg.asDict(state),sb,", ");
		} else {
			Dictionary.buildString(pg.getInterpreter().pendingGoals(state), sb, ", ");
		}
	}
}