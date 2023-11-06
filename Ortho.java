package gsortho;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import gsortho.Vector;

public class Ortho {

    public static List<Vector> random_gs_ortho(int size) {

        List<Vector> ortho_basis = new ArrayList<>();

        Vector initial = Vector.random(size);
        ortho_basis.add(initial.scalar_mul(new BigDecimal(1).divide(initial.frob_norm(), MathContext.DECIMAL128)));

        int total = 1;

        while (total < size) {

            Vector v = Vector.random(size);

            for (int i = 0; i < ortho_basis.size(); i++) {
                v = v.sub(v.project_onto(ortho_basis.get(i)));

            }

            if (v.frob_norm().compareTo(new BigDecimal(0.001)) < 0) {
                continue;
            }

            ortho_basis.add(v.scalar_mul(new BigDecimal(1).divide(v.frob_norm(), MathContext.DECIMAL128)));
            total++;
        }

        return ortho_basis;

    }

    public static void main(String[] args) {

        List<Vector> basis = random_gs_ortho(10);

        for (int i = 0; i < basis.size(); i++) {

            for (int j = 0; j < basis.size(); j++) {
                
                BigDecimal p = basis.get(i).dot(basis.get(j));

                System.out.println(p.round(new MathContext(2)));

            }

        }

    }

}