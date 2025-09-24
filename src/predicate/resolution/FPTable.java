package predicate.resolution;

import java.util.Vector;
import predicate.common.Term;
import predicate.common.Token;
import predicate.common.Variable;

public class FPTable {
    private Vector list = new Vector();

    public FPTable() {
        super();
    }

    public void addParameter(Term term) {
        list.add(term);
    }

    public Term getParameter() {
        Term var = (Term) list.remove(0);
        list.trimToSize();
        if (list.size() < 3) {
            moreParameters();
        }
        return var;
    }

    private void moreParameters() {
        Variable v1 = (Variable) list.get(0);
        Variable v2 = (Variable) list.get(1);
        String s1 = v1.getToken().getData();
        String s2 = v2.getToken().getData();
        for (int i = 1; i <= 10; i++) {
            s1 += i;
            Token t = new Token(s1, 2);
            Variable v = new Variable(t, null, true);
            list.add(v);
            s2 += i;
            t = new Token(s2, 2);
            v = new Variable(t, null, true);
            list.add(v);
        }
    }
}
