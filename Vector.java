package gsortho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class Vector {
    List<BigDecimal> vec;

    public Vector(List<BigDecimal> v) {
        vec = v;
    }

    public int size() {
        return vec.size();
    }

    public static Vector random(int N) {

        List<BigDecimal> rand = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            rand.add(new BigDecimal(Math.random()));
        }

        return new Vector(rand);
    }

    public BigDecimal dot(Vector v) {
        BigDecimal product = new BigDecimal(0);

        for (int i = 0; i < v.size(); i++) {

            product = product.add(v.vec.get(i).multiply(this.vec.get(i)));
        }
        return product;
    }

    public Vector project_onto(Vector v) {

        return v.scalar_mul(this.dot(v).divide(v.dot(v), MathContext.DECIMAL128));

    }

    public Vector add(Vector v) {
        List<BigDecimal> vec = new ArrayList<>();

        for (int i = 0; i < v.size(); i++) {

            vec.add(v.vec.get(i).add(this.vec.get(i)));
        }

        return new Vector(vec);
    }

    public Vector neg() {

        List<BigDecimal> vec = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {

            vec.add(this.vec.get(i).multiply(new BigDecimal(-1.0)));
        }

        return new Vector(vec);
    }

    public Vector sub(Vector v) {

        v = v.neg();

        return this.add(v);
    }

    public Vector scalar_mul(BigDecimal r) {

        List<BigDecimal> vec = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {

            vec.add(this.vec.get(i).multiply(r));
        }

        return new Vector(vec);
    }

    public BigDecimal frob_norm() {

        BigDecimal squared_sum = new BigDecimal(0);

        for (int i = 0; i < this.size(); i++) {
            squared_sum = squared_sum.add(this.vec.get(i).pow(2));

        }
        MathContext ctx = new MathContext(10);
        return squared_sum.sqrt(ctx);
    }

}